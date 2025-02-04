package com.vtcompany.desprelumiServer.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vtcompany.desprelumiServer.red.HiloServer;
import com.vtcompany.desprelumiServer.jugadores.Jugador;
import com.vtcompany.desprelumiServer.monstruos.Equipo;
import com.vtcompany.desprelumiServer.monstruos.Monstruo;
import com.vtcompany.desprelumiServer.red.HiloServer;
import com.vtcompany.desprelumiServer.utiles.Imagen;
import com.vtcompany.desprelumiServer.utiles.Recursos;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class PantallaLucha implements Screen {

	private Imagen imagenFondo;
    private Imagen imagenMonstruoJugador;
    private Imagen imagenMonstruoEnemigo;
    private Imagen imagenMonstruoReserva1;
    private Imagen imagenMonstruoReserva2;

    private float a = 0f;
    private Jugador jugador;
    private Jugador enemigo;
    private HiloServer hiloS;

    private Rectangle areaPokemonReserva1;
    private Rectangle areaPokemonReserva2;
    
    private BitmapFont fuente;

    public PantallaLucha() {
        inicializarAreas();
    }

    private void inicializarAreas() {
        // Ajustar posiciones y tamaños según la pantalla y el diseño
//    	imagenMonstruoEnemigo = enemigo.getEquipo().getMonstruos()[0].getSprites()[1];
//        imagenMonstruoEnemigo.setPosicion(700, 591);
//        imagenMonstruoEnemigo.setSize(124, 124);
    	
        imagenMonstruoJugador = jugador.getEquipo().getMonstruos()[0].getSprites()[2];
        imagenMonstruoJugador.setPosicion(10, 138);
        imagenMonstruoJugador.setSize(124, 124);

        imagenMonstruoReserva1 = jugador.getEquipo().getMonstruos()[1].getSprites()[1];
        imagenMonstruoReserva1.setPosicion(852, 28);
        imagenMonstruoReserva1.setSize(124, 124);
        areaPokemonReserva1 = new Rectangle(imagenMonstruoReserva1.getX(),imagenMonstruoReserva1.getY(),imagenMonstruoReserva1.getAncho(),imagenMonstruoReserva1.getAlto());

        imagenMonstruoReserva2 = jugador.getEquipo().getMonstruos()[2].getSprites()[1];
        imagenMonstruoReserva2.setPosicion(150, 120);
        imagenMonstruoReserva2.setSize(124, 124);
        areaPokemonReserva2 = new Rectangle(imagenMonstruoReserva2.getX(),imagenMonstruoReserva2.getY(),imagenMonstruoReserva2.getAncho(),imagenMonstruoReserva2.getAlto());
    }

    @Override
    public void show() {
        imagenFondo = new Imagen(Recursos.PANTALLA_LUCHA);
        Recursos.cambiarMusica();
        Recursos.reproducirMusica();
    }

    @Override
    public void render(float delta) {
    	Utiles.borrarPantalla();
        Utiles.batch.begin();
    	
        if (a < 1) {
            cambiarClaridadFondo(delta);
        }
        imagenFondo.dibujar(Utiles.batch);
        imagenMonstruoJugador.dibujar(Utiles.batch);
//	        imagenMonstruoEnemigo.dibujar(Utiles.batch);
        imagenMonstruoReserva1.dibujar(Utiles.batch);
        imagenMonstruoReserva2.dibujar(Utiles.batch);

        detectarClic();
	   
    	Utiles.batch.end();
    }

    private void cambiarClaridadFondo(float delta) {
        a += 0.01f;
        imagenFondo.cambiarClaridad(Math.min(a, 1f));
        if (a >= 1) a = 1;
    }

    private void detectarClic() {
        if (Gdx.input.justTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (areaPokemonReserva1.contains(touch)) {
                cambiarPokemonActivo(0); // Cambiar al primer Pokémon de reserva
            } else if (areaPokemonReserva2.contains(touch)) {
                cambiarPokemonActivo(1); // Cambiar al segundo Pokémon de reserva
            }
        }
    }

    private void cambiarPokemonActivo(int indiceReserva) {
        
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
        imagenFondo.dispose();
        imagenMonstruoJugador.dispose();
        imagenMonstruoEnemigo.dispose();
        imagenMonstruoReserva1.dispose();
        imagenMonstruoReserva2.dispose();
        fuente.dispose();
        Recursos.detenerMusica();
        Recursos.cambiarMusica();
    }
}
