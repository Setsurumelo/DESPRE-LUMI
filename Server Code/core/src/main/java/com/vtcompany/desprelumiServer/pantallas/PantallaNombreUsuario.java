package com.vtcompany.desprelumiServer.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vtcompany.desprelumiServer.pantallas.PantallaPrincipal;
import com.vtcompany.desprelumiServer.utiles.Recursos;
import com.vtcompany.desprelumiServer.jugadores.Jugador;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class PantallaNombreUsuario implements Screen {

    private Stage stage;
    private Skin skin;
    private TextField textField;
    private String nombreUsuario;

    @Override
    public void show() {
        // Crear el escenario y la skin
    	Recursos.cambiarMusica();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Asigna el input al escenario

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Crear un campo de texto para ingresar el nombre
        textField = new TextField("", skin);
        textField.setMessageText("Ingresa tu nombre...");

        // Crear un botón para confirmar
        TextButton confirmarButton = new TextButton("Confirmar", skin);
        confirmarButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                nombreUsuario = textField.getText(); // Guardar el texto ingresado
                Utiles.jugador = new Jugador(nombreUsuario);
                Utiles.miJuego.setScreen(new PantallaPrincipal());
            }
        });

        // Organizar los elementos en una tabla para alinearlos
        Table table = new Table();
        table.setFillParent(true); // Hace que la tabla ocupe toda la pantalla
        table.center(); // Centra el contenido

        table.add(textField).width(300).pad(10); // Agrega el campo de texto
        table.row();
        table.add(confirmarButton).width(150).pad(10); // Agrega el botón debajo

        stage.addActor(table); // Agrega la tabla al escenario
    }

    @Override
    public void render(float delta) {
        // Limpia la pantalla
        Utiles.borrarPantalla();

        // Actualiza y dibuja el escenario
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
