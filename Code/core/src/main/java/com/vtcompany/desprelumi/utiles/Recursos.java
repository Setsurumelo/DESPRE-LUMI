package com.vtcompany.desprelumi.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class Recursos {
	
	public static BitmapFont fuente;

    // Texturas de fondos y opciones de menú
    public static Texture pantallaCargaImagen;
    public static Texture pantallaMenu;
    public static Texture pantallaLucha;

    public static Texture opcionLucha;
    public static Texture opcionEquipo;
    public static Texture opcionConfiguracion;
    public static Texture opcionVolver;

    // Música
    public final static Music MUSICA_MENU = Gdx.audio.newMusic(Gdx.files.internal("musica/SoundMenu.mp3"));
    public final static Music MUSICA_LUCHA = Gdx.audio.newMusic(Gdx.files.internal("musica/SoundBattle.mp3"));
    public static Music musicaActual;
    private static Music musicaAnterior;
    private static float volumenGlobal = 1.0f;

    // Texturas de Bob
    public static Texture bob;
    public static Texture bobFrente;
    public static Texture bobBack;
    public static Texture[] bobFrenteAtaque;
    public static Texture[] bobBackAtaque;

    // Texturas de Dolores
    public static Texture dolores;
    public static Texture doloresBack;
    public static Texture doloresFront;
    public static Texture[] doloresFrenteAtaque;
    public static Texture[] doloresBackAtaque;

    // Texturas de Muk
    public static Texture muk;
    public static Texture mukBack;
    public static Texture mukFront;
    public static Texture[] mukFrenteAtaque;
    public static Texture[] mukBackAtaque;

    public static void cargarFuente() {
    	fuente = new BitmapFont();
    	fuente.setColor(Color.WHITE);
	    fuente.getData().setScale(2);
    }
    
    // Método para cargar texturas
    public static void cargarTexturas() {
        // Fondos y opciones de menú
        pantallaCargaImagen = new Texture(Gdx.files.internal("fondos/DespreLumi.png"));
        pantallaMenu = new Texture(Gdx.files.internal("fondos/Menu1.png"));
        pantallaLucha = new Texture(Gdx.files.internal("fondos/fondoLuchaCompleto1.png"));

        opcionLucha = new Texture(Gdx.files.internal("opcionesMenu/Batalla.png"));
        opcionEquipo = new Texture(Gdx.files.internal("opcionesMenu/Equipo.png"));
        opcionConfiguracion = new Texture(Gdx.files.internal("opcionesMenu/Configuracion.png"));
        opcionVolver = new Texture(Gdx.files.internal("opcionesMenu/Volver.png"));

        // Texturas de Bob
        bob = new Texture(Gdx.files.internal("monstruos/bob/Bob1.png"));
        bobFrente = new Texture(Gdx.files.internal("monstruos/bob/BobFront.png"));
        bobBack = new Texture(Gdx.files.internal("monstruos/bob/BobBack.png"));

        bobFrenteAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/bob/Bob1.png")),
            new Texture(Gdx.files.internal("monstruos/bob/Bob2.png")),
            new Texture(Gdx.files.internal("monstruos/bob/Bob3.png")),
            new Texture(Gdx.files.internal("monstruos/bob/Bob4.png")),
            new Texture(Gdx.files.internal("monstruos/bob/Bob5.png")),
            new Texture(Gdx.files.internal("monstruos/bob/Bob6.png"))
        };

        bobBackAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/bob/BobBack1.png")),
            new Texture(Gdx.files.internal("monstruos/bob/BobBack2.png")),
            new Texture(Gdx.files.internal("monstruos/bob/BobBack3.png")),
            new Texture(Gdx.files.internal("monstruos/bob/BobBack4.png")),
            new Texture(Gdx.files.internal("monstruos/bob/BobBack5.png")),
            new Texture(Gdx.files.internal("monstruos/bob/BobBack6.png"))
        };

        // Texturas de Dolores
        dolores = new Texture(Gdx.files.internal("monstruos/dolores/Dolores1.png"));
        doloresBack = new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack.png"));
        doloresFront = new Texture(Gdx.files.internal("monstruos/dolores/DoloresFront.png"));

        doloresFrenteAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores1.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores2.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores3.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores4.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores5.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/Dolores6.png"))
        };

        doloresBackAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack1.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack2.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack3.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack4.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack5.png")),
            new Texture(Gdx.files.internal("monstruos/dolores/DoloresBack6.png"))
        };

        // Texturas de Muk
        muk = new Texture(Gdx.files.internal("monstruos/muk/Muk1.png"));
        mukBack = new Texture(Gdx.files.internal("monstruos/muk/MukBack.png"));
        mukFront = new Texture(Gdx.files.internal("monstruos/muk/MukFront.png"));

        mukFrenteAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/muk/Muk1.png")),
            new Texture(Gdx.files.internal("monstruos/muk/Muk2.png")),
            new Texture(Gdx.files.internal("monstruos/muk/Muk3.png")),
            new Texture(Gdx.files.internal("monstruos/muk/Muk4.png")),
            new Texture(Gdx.files.internal("monstruos/muk/Muk5.png")),
            new Texture(Gdx.files.internal("monstruos/muk/Muk6.png"))
        };

        mukBackAtaque = new Texture[] {
            new Texture(Gdx.files.internal("monstruos/muk/MukBack1.png")),
            new Texture(Gdx.files.internal("monstruos/muk/MukBack2.png")),
            new Texture(Gdx.files.internal("monstruos/muk/MukBack3.png")),
            new Texture(Gdx.files.internal("monstruos/muk/MukBack4.png")),
            new Texture(Gdx.files.internal("monstruos/muk/MukBack5.png")),
            new Texture(Gdx.files.internal("monstruos/muk/MukBack6.png"))
        };
    }
    
    public static void liberarFuente() {
    	if(fuente != null) fuente.dispose();
    }

    // Método para liberar texturas
    public static void liberarTexturas() {
        if (pantallaCargaImagen != null) pantallaCargaImagen.dispose();
        if (pantallaMenu != null) pantallaMenu.dispose();
        if (pantallaLucha != null) pantallaLucha.dispose();

        if (opcionLucha != null) opcionLucha.dispose();
        if (opcionEquipo != null) opcionEquipo.dispose();
        if (opcionConfiguracion != null) opcionConfiguracion.dispose();
        if (opcionVolver != null) opcionVolver.dispose();

        if (bob != null) bob.dispose();
        if (bobFrente != null) bobFrente.dispose();
        if (bobBack != null) bobBack.dispose();
        if (bobFrenteAtaque != null) for (Texture t : bobFrenteAtaque) if (t != null) t.dispose();
        if (bobBackAtaque != null) for (Texture t : bobBackAtaque) if (t != null) t.dispose();

        if (dolores != null) dolores.dispose();
        if (doloresBack != null) doloresBack.dispose();
        if (doloresFront != null) doloresFront.dispose();
        if (doloresFrenteAtaque != null) for (Texture t : doloresFrenteAtaque) if (t != null) t.dispose();
        if (doloresBackAtaque != null) for (Texture t : doloresBackAtaque) if (t != null) t.dispose();

        if (muk != null) muk.dispose();
        if (mukBack != null) mukBack.dispose();
        if (mukFront != null) mukFront.dispose();
        if (mukFrenteAtaque != null) for (Texture t : mukFrenteAtaque) if (t != null) t.dispose();
        if (mukBackAtaque != null) for (Texture t : mukBackAtaque) if (t != null) t.dispose();
    }

    // Métodos de música (sin cambios respecto al anterior)
    public static void cambiarMusica() {
        if (musicaActual != null && musicaActual.isPlaying()) {
            musicaActual.stop();
        }

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

        musicaActual.setVolume(volumenGlobal);
        musicaActual.setLooping(true);
        musicaActual.play();
    }

    public static void reproducirMusica() {
        if (musicaActual != null && !musicaActual.isPlaying()) {
            musicaActual.setLooping(true);
            musicaActual.play();
        }
    }

    public static void detenerMusica() {
        if (musicaActual != null && musicaActual.isPlaying()) {
            musicaActual.stop();
        }
    }

    public static void ajustarVolumenMusica(float volumen) {
        volumenGlobal = Math.max(0.0f, Math.min(1.0f, volumen));
        if (musicaActual != null) {
            musicaActual.setVolume(volumenGlobal);
        }
    }

    public static void liberarMusica() {
        if (musicaActual != null) {
            musicaActual.dispose();
            musicaActual = null;
        }
    }
}
