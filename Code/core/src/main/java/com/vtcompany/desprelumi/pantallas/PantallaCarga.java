package com.vtcompany.desprelumi.pantallas;

import com.badlogic.gdx.Screen;
import com.vtcompany.desprelumi.jugadores.Jugador;
import com.vtcompany.desprelumi.utiles.Imagen;
import com.vtcompany.desprelumi.utiles.Recursos;
import com.vtcompany.desprelumi.utiles.Utiles;

public final class PantallaCarga implements Screen {
	
	private Imagen imagen;
	private float a = 0f;
	private float tiempoTranscurrido = 0f;
	private float tiempoEspera = 2f;
	private boolean finTransicion = false;
	
	@Override
	public void show() {
		imagen = new Imagen(Recursos.pantallaCargaImagen);
		this.imagen.cambiarClaridad(0f);
	}

	@Override
	public void render(float delta) {
		Utiles.borrarPantalla();
		Utiles.batch.begin();
		this.cambiarVisibilidad(delta);
		this.imagen.dibujar(Utiles.batch);
		Utiles.batch.end();
	}
	
	private void cambiarVisibilidad(float delta) {
	    if (!finTransicion) {
	        a += 0.01f;
	        this.imagen.cambiarClaridad(Math.min(a, 1f)); // Asegura que no se pase de 1
	        if (a >= 1) {
	            a = 1;
	            finTransicion = true;
	        }
	    } else {
	        tiempoTranscurrido += delta;
	        if (tiempoTranscurrido >= tiempoEspera) {
	            a -= 0.01f;
	            this.imagen.cambiarClaridad(Math.max(a, 0f)); // Asegura que no se pase de 0
	            if (a <= 0) {
	                a = 0;
	                Utiles.cambiarPantalla(new PantallaNombreUsuario());
	            }
	        }
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
		imagen.dispose();
	}

}
