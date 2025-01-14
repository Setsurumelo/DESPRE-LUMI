package com.vtcompany.desprelumiServer.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vtcompany.desprelumiServer.pantallas.PantallaConfiguracion;
import com.vtcompany.desprelumiServer.pantallas.PantallaEleccionMonstruos;
import com.vtcompany.desprelumiServer.pantallas.PantallaLucha;
import com.vtcompany.desprelumiServer.utiles.Imagen;
import com.vtcompany.desprelumiServer.utiles.Recursos;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class PantallaPrincipal implements Screen {

    private Imagen fondo;
    private Imagen opcionMenuBatalla;
    private Imagen opcionMenuEquipo;
    private Imagen opcionMenuConfiguracion;
    private float a = 0f;
    private Rectangle areaOpcionMenuBatalla;
    private Rectangle areaOpcionMenuEquipo;
    private Rectangle areaOpcionMenuConfiguracion;

    @Override
    public void show() {
        // Imagen de fondo
        fondo = new Imagen(Recursos.PANTALLA_MENU);
        fondo.cambiarClaridad(0f);

        // Opciones de menu
        opcionMenuBatalla = new Imagen(Recursos.OPCION_LUCHA);
        opcionMenuBatalla.setPosicion(384, 390);
        opcionMenuBatalla.setSize(256, 57);
        
        opcionMenuEquipo = new Imagen(Recursos.OPCION_EQUIPO);
        opcionMenuEquipo.setPosicion(405, 308);
        opcionMenuEquipo.setSize(214, 62);
        
        opcionMenuConfiguracion = new Imagen(Recursos.OPCION_CONFIGURACION);
        opcionMenuConfiguracion.setPosicion(262, 218);
        opcionMenuConfiguracion.setSize(500, 70);

        // Área interactiva para los botones
        areaOpcionMenuBatalla = new Rectangle(opcionMenuBatalla.getX(), opcionMenuBatalla.getY(), opcionMenuBatalla.getAncho(), opcionMenuBatalla.getAlto());
        areaOpcionMenuEquipo = new Rectangle(opcionMenuEquipo.getX(), opcionMenuEquipo.getY(),opcionMenuEquipo.getAncho(), opcionMenuEquipo.getAlto());
        areaOpcionMenuConfiguracion = new Rectangle(opcionMenuConfiguracion.getX(),opcionMenuConfiguracion.getY(),opcionMenuConfiguracion.getAncho(), opcionMenuConfiguracion.getAlto());
        
        Recursos.reproducirMusica();
    }

    @Override
    public void render(float delta) {
        Utiles.borrarPantalla();

        Utiles.batch.begin();
        if (a < 1) {
            cambiarClaridadFondo(delta);
        }
        fondo.dibujar(Utiles.batch);
        opcionMenuBatalla.dibujar(Utiles.batch);
        opcionMenuEquipo.dibujar(Utiles.batch);
        opcionMenuConfiguracion.dibujar(Utiles.batch);
        Utiles.batch.end();

        detectarClic();
    }

    private void cambiarClaridadFondo(float delta) {
        a += 0.01f;
        fondo.cambiarClaridad(Math.min(a, 1f));

        if (a >= 1) {
            a = 1;
        }
    }

    private void detectarClic() {
        if (Gdx.input.justTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            // Cambia de pantalla si se hace clic en la opción de menú
            if (areaOpcionMenuBatalla.contains(touch.x, touch.y)) {
            	if (Utiles.jugador.getEquipo() == null) {
            		Utiles.miJuego.setScreen(new PantallaEleccionMonstruos());
            	}else {
            		Utiles.miJuego.setScreen(new PantallaEspera());
            	}
            } else if (areaOpcionMenuEquipo.contains(touch.x, touch.y)) {
                Utiles.miJuego.setScreen(new PantallaEleccionMonstruos());
            } else if (areaOpcionMenuConfiguracion.contains(touch.x, touch.y)) {
            	Utiles.miJuego.setScreen(new PantallaConfiguracion());
            }
        }
    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        fondo.dispose();
        opcionMenuBatalla.dispose();
        opcionMenuConfiguracion.dispose();
        opcionMenuEquipo.dispose();
        Recursos.detenerMusica();
    }
}
