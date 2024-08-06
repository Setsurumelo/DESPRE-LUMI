package com.douyang.juego;

import com.badlogic.gdx.Game;
import com.douyang.juego.screens.PantallaLogoInicial;
//import com.badlogic.gdx.Gdx;
import com.douyang.juego.utilidades.Render;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    
    @Override
    public void create() {
    	Render.app = this;
    	Render.app.setScreen(new PantallaLogoInicial());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        Render.BATCH.dispose();
    }
}
