package com.douyang.juego.utilidades;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render {
	
	public static Game app;
	
	public static final SpriteBatch BATCH = new SpriteBatch();
	
	public static void cleanScreen(float r, float g, float b) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
}
