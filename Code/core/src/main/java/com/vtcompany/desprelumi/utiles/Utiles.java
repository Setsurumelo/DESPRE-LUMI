package com.vtcompany.desprelumi.utiles;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vtcompany.desprelumi.jugadores.Jugador;
import com.vtcompany.desprelumi.monstruos.Bob;
import com.vtcompany.desprelumi.monstruos.Dolores;
import com.vtcompany.desprelumi.monstruos.Monstruo;
import com.vtcompany.desprelumi.monstruos.Muk;
import com.vtcompany.desprelumi.pantallas.PantallaPrincipal;
import com.vtcompany.desprelumi.red.HiloCliente;

public final class Utiles {
	
	public static Game miJuego;
	public static SpriteBatch batch;
	public static Jugador jugador;
	public static Jugador enemigo;
	private static Random random = new Random();
	public static boolean empieza = false, perder = false, ganar = false, cambio = false, turno = false, 
			murio = false, volver = false, enemigoAtaca = false, efectoEP = false, efectoER = false,
			efectoMP = false, efectoMR = false;
	public static int numCambio = 0, daño, atkUsarEnemigo = -1;
	public static HiloCliente hiloC;

	public static void reiniciarACero() {
		enemigo = null;
		empieza = false;
		perder = false;
		ganar = false;
		numCambio = 0;
		turno = false;
	}
	
	public static void cambiarPantalla(Screen pantalla) {
		miJuego.setScreen(pantalla);
	}
	
	public static void borrarPantalla() {
		ScreenUtils.clear(0, 0, 0, 0);
	}
	
	public static int generarRandomInt(int maximo, int minimo) {
		return random.nextInt(minimo, maximo) + 1;
	}
	
	public static float generarRandomFloat(float maximo, float minimo) {
		return random.nextFloat(minimo, maximo);
	}
	
	public static boolean equalsIgnoreAfterWord(String mensaje, String palabra) {
        return mensaje.split(" ")[0].equals(palabra);
    }
	
	public static String eliminarJugadorYEspacio(String mensaje) {
		return mensaje.replace("Jugador ", "").trim();
	}
	
	public static String eliminarDañoYEspacio(String mensaje) {
		return mensaje.replace("Daño ", "").trim();
	}
	
	public static String eliminarMonstruoYEspacio(String mensaje) {
	    return mensaje.replace("Monstruo ", "").trim();
	}
	
	public static Monstruo cambiarStringAMonstruo(String nombre) {
		Monstruo monstruo = null;
		switch(nombre) {
	        case "Muk":
	            monstruo = new Muk();
	            System.out.println("Se creo Muk");
	            break;
	        case "Dolores":
	            monstruo = new Dolores();
	            System.out.println("Se creo Dolores");
	            break;
	        case "Bob":
	            monstruo = new Bob();
	            System.out.println("Se creo Bob");
	            break;
		}
		return monstruo;
	}
	
}
