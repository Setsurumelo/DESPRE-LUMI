package com.vtcompany.desprelumi.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vtcompany.desprelumi.ataques.Ataque;
import com.vtcompany.desprelumi.jugadores.Jugador;
import com.vtcompany.desprelumi.monstruos.Monstruo;
import com.vtcompany.desprelumi.red.HiloCliente;
import com.vtcompany.desprelumi.monstruos.Equipo;
import com.vtcompany.desprelumi.utiles.Imagen;
import com.vtcompany.desprelumi.utiles.Recursos;
import com.vtcompany.desprelumi.utiles.Utiles;

public class PantallaLucha implements Screen {

    private Imagen imagenFondo;
    private Imagen imagenMonstruoJugador;
    private Imagen imagenMonstruoEnemigo;
    private Imagen imagenMonstruoReserva1;
    private Imagen imagenMonstruoReserva2;

    private float a = 0f;
    private Jugador jugador;
    private Jugador enemigo;
    private float tiempoMostrarResultado = 0f; 
    private boolean mostrarResultado = false;  

    private Rectangle areaPokemonReserva1;
    private Rectangle areaPokemonReserva2;
    private Rectangle areaRendirse;
    private Rectangle areaAtk1;
    private Rectangle areaAtk2;
    private Rectangle areaAtk3;
    
    private BitmapFont fuenteNombreAtk = Recursos.fuente;
	private BitmapFont fuenteTipoAtk = Recursos.fuente;
	private BitmapFont fuentePoderAtk = Recursos.fuente;
	private BitmapFont fuenteMonstruo = Recursos.fuente;
	
	private boolean atacando;
	private float tiempoAnimacionAtaque;
	private Animation<TextureRegion> animacionAtaqueJugador;
	private boolean atacandoEnemigo;
	private Animation<TextureRegion> animacionAtaqueEnemigo;

    public PantallaLucha() {
        this.jugador = Utiles.jugador;
        this.enemigo = Utiles.enemigo;
        if (Recursos.musicaActual == Recursos.MUSICA_LUCHA) {
            Recursos.reproducirMusica();
        } else {
            Recursos.cambiarMusica();
        }
        Recursos.cargarFuente();
        Recursos.cargarTexturas();
    }

    private void inicializarAreas() {
        areaPokemonReserva1 = new Rectangle(imagenMonstruoReserva1.getX(),imagenMonstruoReserva1.getY(),imagenMonstruoReserva1.getAncho(),imagenMonstruoReserva1.getAlto());
        areaPokemonReserva2 = new Rectangle(imagenMonstruoReserva2.getX(),imagenMonstruoReserva2.getY(),imagenMonstruoReserva2.getAncho(),imagenMonstruoReserva2.getAlto());
  
        areaRendirse = new Rectangle(844,365,170,340);
        
        areaAtk1 = new Rectangle(5,5,270,120);
        areaAtk2 = new Rectangle(285,5,270,120);
        areaAtk3 = new Rectangle(565,5,270,120);
    }

    @Override
    public void show() {
    	a = 0f; 
        tiempoMostrarResultado = 0f; 
        mostrarResultado = false;

        imagenFondo = new Imagen(Recursos.pantallaLucha);
        imagenFondo.cambiarClaridad(0f);
        
        imagenMonstruoEnemigo = enemigo.getEquipo().getMonstruos()[0].getSprites()[1];
        imagenMonstruoEnemigo.setPosicion(700, 540);
        imagenMonstruoEnemigo.setSize(124, 124);
        
        imagenMonstruoJugador = jugador.getEquipo().getMonstruos()[0].getSprites()[2];
        imagenMonstruoJugador.setPosicion(10, 138);
        imagenMonstruoJugador.setSize(124, 124);
        
        imagenMonstruoReserva1 = jugador.getEquipo().getMonstruos()[1].getSprites()[1];
        imagenMonstruoReserva1.setPosicion(872, 28);
        imagenMonstruoReserva1.setSize(124, 124);
        
        imagenMonstruoReserva2 = jugador.getEquipo().getMonstruos()[2].getSprites()[1];
        imagenMonstruoReserva2.setPosicion(872, 208);
        imagenMonstruoReserva2.setSize(124, 124);
        
        inicializarAreas();
        inicializarFuentes();
        
        Recursos.cambiarMusica();
        Recursos.reproducirMusica();
    }

    private void inicializarFuentes() {
    	fuenteNombreAtk.getData().setScale(3f);
    	fuentePoderAtk.getData().setScale(2f);
    	fuenteTipoAtk.getData().setScale(2f);
    	fuenteMonstruo.getData().setScale(2f);
	}

	@Override
    public void render(float delta) {
        Utiles.borrarPantalla(); // Limpia la pantalla completamente
        Utiles.batch.begin();

        imagenFondo.dibujar(Utiles.batch);
        
        if (a < 1) {
            cambiarClaridadFondo(delta);
        }

        if(Utiles.enemigoAtaca) {
        	atacar();
        }        
       
        if (!atacando && imagenMonstruoJugador != null) {imagenMonstruoJugador.dibujar(Utiles.batch);}
        if (imagenMonstruoReserva1 != null)imagenMonstruoReserva1.dibujar(Utiles.batch);
        if (imagenMonstruoReserva2 != null)imagenMonstruoReserva2.dibujar(Utiles.batch);
        if (!atacandoEnemigo && imagenMonstruoEnemigo != null)imagenMonstruoEnemigo.dibujar(Utiles.batch); 
        
        escribirMonstruo();
        
        if (atacandoEnemigo && animacionAtaqueEnemigo != null) {
            tiempoAnimacionAtaque += delta;
            TextureRegion fotogramaAtaque = animacionAtaqueEnemigo.getKeyFrame(tiempoAnimacionAtaque, false);
            Utiles.batch.draw(fotogramaAtaque, 700, 540, 124, 124); // Posición del enemigo.
            
            if (animacionAtaqueEnemigo.isAnimationFinished(tiempoAnimacionAtaque)) {
                atacandoEnemigo = false;
            }            
        }
        
        if (atacando && animacionAtaqueJugador != null) {
            tiempoAnimacionAtaque += delta;
            TextureRegion fotogramaAtaque = animacionAtaqueJugador.getKeyFrame(tiempoAnimacionAtaque, false); 
            Utiles.batch.draw(fotogramaAtaque, 10, 138,124,124); 
        }

        if (animacionAtaqueJugador != null && animacionAtaqueJugador.isAnimationFinished(tiempoAnimacionAtaque)) {
            atacando = false;
        }
        
        detectarClic();
        

        if (Utiles.murio) {
        	cambiarMonstruoMuerto();
        }
        
        if (Utiles.ganar || Utiles.perder) {
            mostrarResultado = true;
            Recursos.fuente.draw(Utiles.batch, Utiles.ganar ? "GANASTE" : "PERDISTE", 400, 300);
        }

        if (mostrarResultado) {
            tiempoMostrarResultado += delta;
            if (tiempoMostrarResultado > 5) {
                cambiarPantalla();
            }
        }

        detectarCambio();
        Utiles.batch.end();
    }

// PANTALLA

	private void cambiarClaridadFondo(float delta) {
        a += 0.01f;
        imagenFondo.cambiarClaridad(Math.min(a, 1f));
        if (a >= 1) a = 1;
    }

	private void cambiarPantalla() {
		Utiles.hiloC.enviarMensaje("Desconectar");
		Utiles.reiniciarACero();;
        Utiles.cambiarPantalla(new PantallaPrincipal());
        dispose(); 
    }


    private void detectarCambio() {
        if (Utiles.cambio) {
            enemigo.getEquipo().intercambiarMonstruos(Utiles.numCambio, 0);
            
            imagenMonstruoEnemigo = enemigo.getEquipo().getMonstruos()[0].getSprites()[1];
            imagenMonstruoEnemigo.setPosicion(700, 540);
            imagenMonstruoEnemigo.setSize(124, 124);
            
            Utiles.cambio = false;
            Utiles.numCambio=0;
        }
    }

	
    private void detectarClic() {
        if (Gdx.input.justTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (Utiles.turno) {
                if (areaPokemonReserva1.contains(touch) && jugador.getEquipo().getMonstruos()[1].getVidaActual() > 0) {
                    intercambiarMonstruos(1, 0);
                    Utiles.hiloC.enviarMensaje("Cambio1");
                    Utiles.turno = false; 
                    Utiles.hiloC.enviarMensaje("FinTurno");
                } else if (areaPokemonReserva2.contains(touch) && jugador.getEquipo().getMonstruos()[2].getVidaActual() > 0) {
                    intercambiarMonstruos(2, 0);
                    Utiles.turno = false;
                    Utiles.hiloC.enviarMensaje("Cambio2");
                    Utiles.hiloC.enviarMensaje("FinTurno");
                } else if (areaRendirse.contains(touch)) {
                    Utiles.hiloC.enviarMensaje("SeRindio");
                } else if (areaAtk1.contains(touch)) {
                	Utiles.hiloC.enviarMensaje("Atk1");
                	atacar();
                	recibirDaño(jugador.getEquipo().getMonstruos()[0].usarAtaque(0, enemigo.getEquipo().getMonstruos()[0]), true);
                	Utiles.turno = false;
                	Utiles.hiloC.enviarMensaje("FinTurno");
                } else if (areaAtk2.contains(touch)) {
                	Utiles.hiloC.enviarMensaje("Atk2");
                	atacar();
                	recibirDaño(jugador.getEquipo().getMonstruos()[0].usarAtaque(1, enemigo.getEquipo().getMonstruos()[0]), true);
                	Utiles.turno = false;
                	Utiles.hiloC.enviarMensaje("FinTurno");
                } else if (areaAtk3.contains(touch)) {
                	Utiles.hiloC.enviarMensaje("Atk3");
                	atacar();
                	recibirDaño(jugador.getEquipo().getMonstruos()[0].usarAtaque(2, enemigo.getEquipo().getMonstruos()[0]), true);
                	Utiles.turno = false;
                	Utiles.hiloC.enviarMensaje("FinTurno");
                }
            }
        }
    }
    

//MONSTRUOS
    
    private void cambiarMonstruoMuerto() {
    	switch(this.jugador.getEquipo().verificarMuerteMonstruo()) {
		case 1:
			intercambiarMonstruos(1, 0);
			Utiles.hiloC.enviarMensaje("Cambio1");
			Utiles.murio = false;
			break;
		case 2:
			intercambiarMonstruos(2, 0);
			Utiles.hiloC.enviarMensaje("Cambio2");
			Utiles.murio = false;
			break;
		}
    }
    
    private void recibirDaño(int daño, boolean esJugadorAtacante) {
        if (esJugadorAtacante) {
            enemigo.getEquipo().getMonstruos()[0].recibirDaño(daño);
            Utiles.hiloC.enviarMensaje("Daño "+daño);
            if (enemigo.getEquipo().getMonstruos()[0].getVidaActual() <= 0) {
                switch (enemigo.getEquipo().verificarMuerteMonstruo()) {
                    case 1:
                    	Utiles.hiloC.enviarMensaje("SeMurio");
                    case 2:
                        Utiles.hiloC.enviarMensaje("SeMurio");
                        break;
                    case 3:
                        Utiles.hiloC.enviarMensaje("Gane");
                        break;
                }
            }
        } else {
            jugador.getEquipo().getMonstruos()[0].recibirDaño(daño);
            if (jugador.getEquipo().getMonstruos()[0].getVidaActual() <= 0) {
                cambiarMonstruoMuerto();
                Utiles.atkUsarEnemigo=-1;
                Utiles.enemigoAtaca = false;
                Utiles.daño = 0;
            }
        }
    }
    
    private void intercambiarMonstruos(int indiceReserva, int indiceActivo) {
        // Intercambia las posiciones en el equipo del jugador
        jugador.getEquipo().intercambiarMonstruos(indiceReserva, indiceActivo);

        // Actualiza las imágenes del Pokémon activo
        imagenMonstruoJugador = jugador.getEquipo().getMonstruos()[indiceActivo].getSprites()[2];
        imagenMonstruoJugador.setPosicion(10, 138);
        imagenMonstruoJugador.setSize(124, 124);

        // Actualiza la imagen del Pokémon de reserva correspondiente
        if (indiceReserva == 1) {
            imagenMonstruoReserva1 = jugador.getEquipo().getMonstruos()[indiceReserva].getSprites()[1];
            imagenMonstruoReserva1.setPosicion(872, 28);
            imagenMonstruoReserva1.setSize(124, 124);
        } else if (indiceReserva == 2) {
            imagenMonstruoReserva2 = jugador.getEquipo().getMonstruos()[indiceReserva].getSprites()[1];
            imagenMonstruoReserva2.setPosicion(872, 208);
            imagenMonstruoReserva2.setSize(124, 124);
        }
    }
    
    private void atacar() {
    	if (Utiles.enemigoAtaca) {
    	    animacionAtaqueEnemigo = enemigo.getEquipo().getMonstruos()[0].getAnimacionAtaqueFrente();
    	    tiempoAnimacionAtaque = 0f; // Reinicia el tiempo para la animación.
    	    atacandoEnemigo = true;
    	    recibirDaño(Utiles.daño, false);
    	    Utiles.enemigoAtaca = false;
    	}else {
    		animacionAtaqueJugador = jugador.getEquipo().getMonstruos()[0].getAnimacionAtaqueBack();
    		tiempoAnimacionAtaque = 0f;  // Reinicia el tiempo de la animación
            atacando = true;
    	}
    }

    
    private void escribirMonstruo() {
    	if(enemigo.getEquipo().getMonstruos()[0].getNombre().equals("Muk")) {
    		fuenteMonstruo.draw(Utiles.batch, enemigo.getEquipo().getMonstruos()[0].getNombre(), 560, 680);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(enemigo.getEquipo().getMonstruos()[0].getVida()), 620, 656);
        	fuenteMonstruo.draw(Utiles.batch, " / ", 600, 656);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(enemigo.getEquipo().getMonstruos()[0].getVidaActual()), 570, 656);
    	} else {
    		fuenteMonstruo.draw(Utiles.batch, enemigo.getEquipo().getMonstruos()[0].getNombre(), 560, 680);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(enemigo.getEquipo().getMonstruos()[0].getVida()), 635, 656);
        	fuenteMonstruo.draw(Utiles.batch, " / ", 615, 656);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(enemigo.getEquipo().getMonstruos()[0].getVidaActual()), 570, 656);
    	}
    	
    	if(jugador.getEquipo().getMonstruos()[0].getNombre().equals("Muk")) {
    		fuenteMonstruo.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getNombre(), 150, 264);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getVida()), 210, 240);
        	fuenteMonstruo.draw(Utiles.batch, " / ", 190, 240);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getVidaActual()), 160, 240);
    	} else {
    		fuenteMonstruo.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getNombre(), 150, 264);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getVida()), 225, 240);
        	fuenteMonstruo.draw(Utiles.batch, " / ", 205, 240);
        	fuenteMonstruo.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getVidaActual()), 160, 240);
    	}
    	
    	fuenteNombreAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[0].getNombre(), areaAtk1.x+60, areaAtk1.y+100);
    	fuenteTipoAtk.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getAtaques()[0].getPotencia()), areaAtk1.x+110, areaAtk1.y+60);
    	fuentePoderAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[0].getTipo().getNombre(), areaAtk1.x+80, areaAtk1.y+35);
    	
    	fuenteNombreAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[1].getNombre(), areaAtk2.x+60, areaAtk2.y+100);
    	fuenteTipoAtk.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getAtaques()[1].getPotencia()), areaAtk2.x+110, areaAtk2.y+60);
    	fuentePoderAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[1].getTipo().getNombre(), areaAtk2.x+80, areaAtk2.y+35);
    	
    	fuenteNombreAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[2].getNombre(), areaAtk3.x+60, areaAtk3.y+100);
    	fuenteTipoAtk.draw(Utiles.batch, String.valueOf(jugador.getEquipo().getMonstruos()[0].getAtaques()[2].getPotencia()), areaAtk3.x+110, areaAtk3.y+60);
    	fuentePoderAtk.draw(Utiles.batch, jugador.getEquipo().getMonstruos()[0].getAtaques()[2].getTipo().getNombre(), areaAtk3.x+80, areaAtk3.y+35);
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
        Recursos.liberarMusica();
        fuenteMonstruo.dispose();
        fuenteNombreAtk.dispose();
        fuentePoderAtk.dispose();
        fuenteTipoAtk.dispose();
    }
    
    
}
