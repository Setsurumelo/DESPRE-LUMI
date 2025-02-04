package com.vtcompany.desprelumi.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vtcompany.desprelumi.monstruos.*;
import com.vtcompany.desprelumi.utiles.Imagen;
import com.vtcompany.desprelumi.utiles.Recursos;
import com.vtcompany.desprelumi.utiles.Utiles;

public class PantallaEleccionMonstruos implements Screen{
	
	private Imagen fondo;
	private float a = 0f;
	private Imagen[] diseñoMonstruo;
	private Rectangle[] areaEleccion;
	private int seleccionActual = 0; // Contador de selecciones actuales
	private int[] op = new int[3];
	private Monstruo[] monstruos = {
		new Bob(),
		new Dolores(),
		new Muk()
	};

	@Override
	public void show() {
	    fondo = new Imagen(Recursos.pantallaMenu);
	    fondo.cambiarClaridad(0f);
	    
	    diseñoMonstruo = new Imagen[] {
	        monstruos[0].getSprites()[0],
	        monstruos[1].getSprites()[0],
	        monstruos[2].getSprites()[0],
	    };
	    
	    setearMonstruos();
	    areaSet();
	    
	    Recursos.reproducirMusica();
	}

	private void areaSet() {
	    areaEleccion = new Rectangle[diseñoMonstruo.length];
	    for (int i = 0; i < diseñoMonstruo.length; i++) {
	        areaEleccion[i] = new Rectangle(diseñoMonstruo[i].getX(), diseñoMonstruo[i].getY(), diseñoMonstruo[i].getAncho(), diseñoMonstruo[i].getAlto());
	    }
	}

	private void setearMonstruos() {
	    for (int i = 0; i < diseñoMonstruo.length; i++) {
	        diseñoMonstruo[i].setSize(128, 128);
	    }
	    diseñoMonstruo[0].setPosicion(226, 20);
	    diseñoMonstruo[1].setPosicion(374, 20);
	    diseñoMonstruo[2].setPosicion(522, 20);
	}

	@Override
	public void render(float delta) {
	    Utiles.borrarPantalla();

	    Utiles.batch.begin();
	    if (a < 1) {
	        cambiarClaridadFondo(delta);
	    }
	    fondo.dibujar(Utiles.batch);
	    dibujarMonstruos();
	    Utiles.batch.end();

	    detectarClic();
	}

	private void dibujarMonstruos() {
	    for (Imagen imagen : diseñoMonstruo) {
	        imagen.dibujar(Utiles.batch);
	    }
	}

	private void detectarClic() {
	    if (Gdx.input.justTouched()) {
	        Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

	        for (int i = 0; i < areaEleccion.length; i++) {
	            if (areaEleccion[i].contains(touch) && seleccionActual < monstruos.length) {
	                // Verifica si el monstruo ya fue seleccionado
	                boolean yaSeleccionado = false;
	                for (int j = 0; j < seleccionActual; j++) {
	                    if (op[j] == i) {
	                        yaSeleccionado = true;
	                        break;
	                    }
	                }

	                if (!yaSeleccionado) {
	                    op[seleccionActual] = i;
	                    seleccionActual++;

	                    if (seleccionActual == 3) {
	                        finalizarSeleccion();
	                    }
	                } 
	            }
	        }
	    }
	}


	private void finalizarSeleccion() {
	    Monstruo[] elegidos = new Monstruo[3];

	    for (int i = 0; i < op.length; i++) {
			elegidos[i] = monstruos[op[i]];
		}

	    Utiles.jugador.crearEquipo(elegidos);
	    // Cambia a la siguiente pantalla (PantallaLucha)
	    Utiles.miJuego.setScreen(new PantallaPrincipal());
	}

	private void cambiarClaridadFondo(float delta) {
	    a += 0.01f;
	    fondo.cambiarClaridad(Math.min(a, 1f));

	    if (a >= 1) {
	        a = 1;
	    }
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
	    fondo.dispose();
	    Recursos.detenerMusica();
	    for (int i = 0; i < diseñoMonstruo.length; i++) {
			diseñoMonstruo[i].dispose();
		}
	}
	
}