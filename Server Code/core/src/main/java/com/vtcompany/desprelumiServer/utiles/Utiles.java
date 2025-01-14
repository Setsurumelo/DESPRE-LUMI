package com.vtcompany.desprelumiServer.utiles;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.vtcompany.desprelumiServer.jugadores.Jugador;

public final class Utiles {
	
	public static Game miJuego;
	public static SpriteBatch batch;
	public static Jugador jugador;
	public static Jugador enemigo;
	private static Random random = new Random();
	public static BitmapFont font = new BitmapFont();
	public static boolean empieza = false;
	
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
	
	public static String eliminarEfectoYEspacio(String mensaje) {
		String msj = null;
		if(equalsIgnoreAfterWord(mensaje, "EfectoEP")) {
			msj = mensaje.replace("EfectoEP ", "").trim();
		}else if(equalsIgnoreAfterWord(mensaje, "EfectoER")) {
			msj = mensaje.replace("EfectoER ", "").trim();
		}else if(equalsIgnoreAfterWord(mensaje, "EfectoMP")) {
			msj = mensaje.replace("EfectoMP ", "").trim();
		}else if(equalsIgnoreAfterWord(mensaje, "EfectoMR")) {
			msj = mensaje.replace("EfectoMR ", "").trim();
		}
		return msj;
	}
	
}
