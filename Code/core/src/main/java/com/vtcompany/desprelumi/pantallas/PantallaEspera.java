package com.vtcompany.desprelumi.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vtcompany.desprelumi.red.HiloCliente;
import com.vtcompany.desprelumi.utiles.Recursos;
import com.vtcompany.desprelumi.utiles.Utiles;

public class PantallaEspera implements Screen{
	
	private BitmapFont fuente;
	
	public PantallaEspera() {
		Utiles.jugador.getEquipo().curarEquipo();
		Utiles.hiloC = new HiloCliente();
        Utiles.hiloC.start();
        Recursos.cargarFuente();
        Recursos.cargarTexturas();
        fuente = Recursos.fuente;
        if (Recursos.musicaActual == Recursos.MUSICA_MENU) {
            Recursos.reproducirMusica();
        } else {
            Recursos.cambiarMusica();
        }

	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Utiles.borrarPantalla();
		Utiles.batch.begin();
		if (Utiles.volver) {
			Utiles.hiloC.desconectar();
			Utiles.cambiarPantalla(new PantallaPrincipal());
		}else if(Utiles.empieza) {
			Utiles.cambiarPantalla(new PantallaLucha());
		}else {
			fuente.draw(Utiles.batch, "Esperando rival...", 400, 300);
		}
		
		Utiles.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		fuente.dispose();
	}

}
