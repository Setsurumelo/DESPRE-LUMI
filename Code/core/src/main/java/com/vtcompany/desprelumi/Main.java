package com.vtcompany.desprelumi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vtcompany.desprelumi.pantallas.PantallaCarga;
import com.vtcompany.desprelumi.utiles.Recursos;
import com.vtcompany.desprelumi.utiles.Utiles;

public class Main extends Game {

    @Override
    public void create() {
    	Utiles.batch = new SpriteBatch();
    	Utiles.miJuego = this;
    	Recursos.cargarTexturas();
    	Utiles.cambiarPantalla(new PantallaCarga());
    	Recursos.cargarFuente();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    	Recursos.liberarTexturas();
    	Recursos.liberarMusica();
    	Recursos.liberarFuente();
    	Utiles.hiloC.enviarMensaje("SeRindio");
    	Utiles.hiloC.enviarMensaje("Desconectar");
    	Utiles.batch.dispose();
    }
}