package com.vtcompany.desprelumiServer.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public final class Recursos {
	
	public final static String PANTALLA_CARGA_IMAGEN = "fondos/DespreLumi.png";
	public final static String PANTALLA_MENU = "fondos/Menu1.png";
	public final static String PANTALLA_LUCHA = "fondos/fondoLuchaCompleto1.png";
	
	public final static String OPCION_LUCHA = "opcionesMenu/Batalla.png";
	public final static String OPCION_EQUIPO = "opcionesMenu/Equipo.png";
	public final static String OPCION_CONFIGURACION = "opcionesMenu/Configuracion.png";
	public final static String OPCION_VOLVER = "opcionesMenu/Volver.png";
	
	public final static Music MUSICA_MENU = Gdx.audio.newMusic(Gdx.files.internal("musica/SoundMenu.mp3"));
	public final static Music MUSICA_LUCHA = Gdx.audio.newMusic(Gdx.files.internal("musica/SoundBattle.mp3"));
	private static Music musicaActual;
	private static Music musicaAnterior;
	private static float volumenGlobal = 1.0f; // Variable para guardar el volumen

	// Método para cambiar la música
	public static void cambiarMusica() {
	    // Detener la música anterior si está sonando
	    if (musicaActual != null && musicaActual.isPlaying()) {
	        musicaActual.stop();
	    }

	    // Cambiar de música (de menú a lucha o viceversa)
	    if (musicaAnterior == null && musicaActual == null) {
	        musicaActual = MUSICA_MENU;
	        musicaAnterior = musicaActual;
	    } else if (musicaAnterior == MUSICA_MENU) {
	        musicaAnterior = musicaActual;
	        musicaActual = MUSICA_LUCHA;
	    } else if (musicaAnterior == MUSICA_LUCHA) {
	        musicaAnterior = musicaActual;
	        musicaActual = MUSICA_MENU;
	    }

	    // Establecer el volumen de la música actual
	    musicaActual.setVolume(volumenGlobal); // Aplicar el volumen global
	    musicaActual.setLooping(true); // Reproducir en bucle
	    musicaActual.play(); // Reproducir la música
	}

	// Método para reproducir la música sin cambiarla
	public static void reproducirMusica() {
	    if (musicaActual != null && !musicaActual.isPlaying()) {
	        musicaActual.setLooping(true); // Reproducir en bucle
	        musicaActual.play(); // Reproducir la música
	    }
	}

	// Método para detener la música actual
	public static void detenerMusica() {
	    if (musicaActual != null && musicaActual.isPlaying()) {
	        musicaActual.stop(); // Detener la música
	    }
	}

	// Método para ajustar el volumen de la música
	public static void ajustarVolumenMusica(float volumen) {
	    volumenGlobal = Math.max(0.0f, Math.min(1.0f, volumen)); // Asegurarse que el volumen esté en el rango válido
	    if (musicaActual != null) {
	        musicaActual.setVolume(volumenGlobal); // Aplicar el volumen a la música actual
	    }
	}

	// Método para liberar los recursos de la música (cuando ya no se necesita)
	public static void liberarMusica() {
	    if (musicaActual != null) {
	        musicaActual.dispose(); // Liberar los recursos de la música
	        musicaActual = null;
	    }
	}

    
	public final static String BOB = "monstruos/bob/Bob1.png";
	public final static String BOB_FRENTE = "monstruos/bob/BobFront.png";
	public final static String BOB_BACK = "monstruos/bob/BobBack.png";
	public final static String[] BOB_FRENTE_ATAQUE = {
			"monstruos/bob/Bob1.png",
			"monstruos/bob/Bob2.png",
			"monstruos/bob/Bob3.png",
			"monstruos/bob/Bob4.png",
			"monstruos/bob/Bob5.png",
			"monstruos/bob/Bob6.png"
	};
	public final static String[] BOB_BACK_ATAQUE = {
			"monstruos/bob/BobBack1.png",
			"monstruos/bob/BobBack2.png",
			"monstruos/bob/BobBack3.png",
			"monstruos/bob/BobBack4.png",
			"monstruos/bob/BobBack5.png",
			"monstruos/bob/BobBack6.png"
	};
	
	public final static String DOLORES = "monstruos/dolores/Dolores1.png";
	public final static String DOLORES_BACK = "monstruos/dolores/DoloresBack.png";
	public final static String DOLORES_FRONT = "monstruos/dolores/DoloresFront.png";
	public final static String[] DOLORES_FRENTE_ATAQUE = {
			"monstruos/dolores/Dolores1.png",
			"monstruos/dolores/Dolores2.png",
			"monstruos/dolores/Dolores3.png",
			"monstruos/dolores/Dolores4.png",
			"monstruos/dolores/Dolores5.png",
			"monstruos/dolores/Dolores6.png"
	};
	public final static String[] DOLORES_BACK_ATAQUE = {
			"monstruos/dolores/DoloresBack1.png",
			"monstruos/dolores/DoloresBack1.png",
			"monstruos/dolores/DoloresBack1.png",
			"monstruos/dolores/DoloresBack1.png",
			"monstruos/dolores/DoloresBack1.png",
			"monstruos/dolores/DoloresBack1.png"
	};
	
	public final static String MUK = "monstruos/muk/Muk1.png";
	public final static String MUK_BACK = "monstruos/muk/MukBack.png";
	public final static String MUK_FRONT = "monstruos/muk/MukFront.png";
	public final static String[] MUK_FRENTE_ATAQUE = {
			"monstruos/muk/Muk1.png",
			"monstruos/muk/Muk2.png",
			"monstruos/muk/Muk3.png",
			"monstruos/muk/Muk4.png",
			"monstruos/muk/Muk5.png",
			"monstruos/muk/Muk6.png"
	};
	public final static String[] MUK_BACK_ATAQUE = {
			"monstruos/muk/MukBack1.png",
			"monstruos/muk/MukBack2.png",
			"monstruos/muk/MukBack3.png",
			"monstruos/muk/MukBack4.png",
			"monstruos/muk/MukBack5.png",
			"monstruos/muk/MukBack6.png"
	};
}
