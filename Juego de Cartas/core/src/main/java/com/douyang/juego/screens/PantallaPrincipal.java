package com.douyang.juego.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.douyang.juego.recursos.Recursos;
import com.douyang.juego.utilidades.Imagen;
import com.douyang.juego.utilidades.Render;
import com.douyang.juego.utilidades.UtilidadesPantalla;

public class PantallaPrincipal implements Screen {
    private Imagen fondoDePantalla;
    private TextButton botonJugar;
    private TextButton botonElegirMazo;
    private TextButton botonSalir;

    public PantallaPrincipal() {
    	UtilidadesPantalla.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        this.fondoDePantalla = new Imagen(Recursos.BACKROUND_PANTALLA_MENU_PRINCIPAL.getRuta());
        this.inicializarBotonJugar();
        
    }

    public void inicializarBotonJugar() {
    	this.botonJugar = UtilidadesPantalla.armarBotonConImagen(Recursos.BOTON_JUGAR_PANTALLA_MENU_PRINCIPAL, 490, 500, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//				Render.app.setScreen(new PantallaJuego());
            }
        });
        UtilidadesPantalla.stage.addActor(this.botonJugar);
        Gdx.input.setInputProcessor(UtilidadesPantalla.stage);
    }
    
    @Override
    public void render(float delta) {
        Render.BATCH.begin();
        	this.fondoDePantalla.dibujar();
        	UtilidadesPantalla.stage.act(delta);
        	UtilidadesPantalla.stage.draw();
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
    	UtilidadesPantalla.stage.dispose();
        fondoDePantalla.getTextura().dispose();
    }
}
