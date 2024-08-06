package com.douyang.juego.utilidades;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.douyang.juego.recursos.Recursos;

public class UtilidadesPantalla {

	public static Stage stage;
	public static ArrayList<Table> tablas = new ArrayList<Table>();
	
	public static TextButton armarBotonConImagen(Recursos recurso, int posX, int posY, ClickListener listener) {
		Imagen imagen = new Imagen(recurso.getRuta());

        TextButton.TextButtonStyle estilo = new TextButton.TextButtonStyle();
        estilo.up = new SpriteDrawable(imagen.getSprite());
        estilo.down = new SpriteDrawable(imagen.getSprite());
        estilo.font = new BitmapFont();

        TextButton botonAUX = new TextButton("", estilo);
        botonAUX.setPosition(posX, posY);
        botonAUX.addListener(listener);
        botonAUX.setSize(imagen.getSprite().getWidth(), imagen.getSprite().getHeight());
        botonAUX.getLabel().setAlignment(Align.center);

        return botonAUX;
	}
	
}
