package com.douyang.juego.screens;

import com.badlogic.gdx.Screen;
import com.douyang.juego.recursos.Recursos;
import com.douyang.juego.utilidades.Imagen;
import com.douyang.juego.utilidades.Render;

public class PantallaLogoInicial implements Screen{

	private Imagen logo;
	private float visivilidad = 0;
	private boolean finalizacion = false;
	private int tiempoFinalizacion = 50, contador = 0;
	@Override
	public void show() {
		this.logo = new Imagen(Recursos.PANTALLA_PRINCIPAL.getRuta());
	}

	private void renderTotal() {
		if(this.finalizacion) {
			if(this.contador > this.tiempoFinalizacion) {
				this.visivilidad -= 0.01f;
				if(this.visivilidad < 0) {
					this.visivilidad = 0;
					Render.app.setScreen(new PantallaPrincipal());
				}
			}else {
				this.contador ++;
			}
		}else{
			this.visivilidad += 0.01f;
			if(this.visivilidad > 1) {
				this.finalizacion = true;
				this.visivilidad = 1;
			}
		}
		logo.getSprite().setAlpha(this.visivilidad);
	}
	
	@Override
	public void render(float delta) {
		Render.cleanScreen(0, 0, 0);
		Render.BATCH.begin();
			this.renderTotal();
			logo.dibujar();
		Render.BATCH.end();
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
		
	}
	
}
