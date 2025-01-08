package com.vtcompany.desprelumiServer.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Imagen {
	private Texture textura;
	private Sprite sprite;
	
	public Imagen(String direccion) {
		this.textura = new Texture(direccion);
		this.sprite = new Sprite(this.textura);
	}
	
	public void dibujar(SpriteBatch batch) {
		this.sprite.draw(batch);
	}
	
	public void setPosicion(float x, float y) {
		sprite.setPosition(x, y);
	}
	
	public void setSize(float ancho, float alto) {
		sprite.setSize(ancho, alto);
	}
	
	public void cambiarClaridad(float a) {
		this.sprite.setAlpha(a);
	}
	
	public void dispose() {
		textura.dispose();
	}
	
	public Sprite getSprite() {return this.sprite;}
	public Texture getTextura() {return this.textura;}

	public float getAncho() {
		return this.sprite.getWidth();
	}
	
	public float getAlto() {
		return this.sprite.getHeight();
	}
	
	public float getX() {
		return this.sprite.getX();
	}
	
	public float getY() {
		return this.sprite.getY();
	}
	
}
