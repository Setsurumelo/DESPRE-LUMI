package com.vtcompany.desprelumiServer.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vtcompany.desprelumiServer.red.HiloServer;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class PantallaEspera implements Screen{

	private HiloServer hiloS;
	
	private BitmapFont fuente;
	
	public PantallaEspera() {
		hiloS = new HiloServer();
        hiloS.start();
        fuente = new BitmapFont();
        fuente.setColor(Color.WHITE);
        fuente.getData().setScale(2);
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Utiles.borrarPantalla();
		Utiles.batch.begin();
		
		if(Utiles.empieza) {
			fuente.draw(Utiles.batch, "¡Rivales encontrado!", 400, 360);
		}else {
			fuente.draw(Utiles.batch, "Esperando rival...", 400, 500);
		}
		fuente.draw(Utiles.batch, "Cantidad de Clientes: "+hiloS.getCantClientes(),400,200);
		
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
