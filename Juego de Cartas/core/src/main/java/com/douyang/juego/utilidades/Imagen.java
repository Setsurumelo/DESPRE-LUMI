package com.douyang.juego.utilidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Imagen {
	private Texture textura;
	private Sprite sprite;
	
	public Imagen(String ruta) {
		this.textura = new Texture(ruta);
		this.sprite = new Sprite(this.textura);
	}
	
	
	public void dibujar() {
		this.sprite.draw(Render.BATCH);
	}
	
	public Texture getTextura() {
		return textura;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
