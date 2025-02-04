package com.vtcompany.desprelumiServer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vtcompany.desprelumiServer.pantallas.PantallaCarga;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class Main extends Game {

    @Override
    public void create() {
    	Utiles.batch = new SpriteBatch();
    	Utiles.miJuego = this;
    	Utiles.miJuego.setScreen(new PantallaCarga());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    	Utiles.batch.dispose();
    }
}