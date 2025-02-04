package com.vtcompany.desprelumiServer.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.vtcompany.desprelumiServer.utiles.Imagen;
import com.vtcompany.desprelumiServer.utiles.Recursos;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public class PantallaConfiguracion implements Screen {

    private Imagen fondo;
    private float a = 0f;
    private Imagen opcionVolver;
    private Rectangle areaOpcionVolver;

    private ShapeRenderer shapeRenderer;
    private Rectangle barraVolumen;
    private Rectangle cursorVolumen;
    private float volumenActual;
    private Preferences preferencias;

    @Override
    public void show() {
        // Inicializar preferencias
        preferencias = Gdx.app.getPreferences("configuracionJuego");
        volumenActual = preferencias.getFloat("volumen", 0.5f); // Cargar volumen guardado o 0.5 por defecto

        // Imagen de fondo
        fondo = new Imagen(Recursos.PANTALLA_MENU);
        fondo.cambiarClaridad(0f);

        // Opciones
        opcionVolver = new Imagen(Recursos.OPCION_VOLVER);
        opcionVolver.setSize(254, 78);
        opcionVolver.setPosicion(15, 15);

        areaOpcionVolver = new Rectangle(opcionVolver.getX(), opcionVolver.getY(), opcionVolver.getAncho(), opcionVolver.getAlto());

        // Barra de volumen centrada en pantalla 1024x720
        shapeRenderer = new ShapeRenderer();
        barraVolumen = new Rectangle(312, 350, 400, 20); // Centramos en pantalla
        cursorVolumen = new Rectangle(barraVolumen.x + barraVolumen.width * volumenActual - 10, barraVolumen.y - 10, 20, 40); // Cursor

        // Música de fondo
        Recursos.reproducirMusica();
        Recursos.ajustarVolumenMusica(volumenActual); // Ajustar volumen inicial
    }

    @Override
    public void render(float delta) {
        Utiles.borrarPantalla();

        Utiles.batch.begin();
        if (a < 1) {
            cambiarClaridadFondo(delta);
        }
        fondo.dibujar(Utiles.batch);
        opcionVolver.dibujar(Utiles.batch);

        // Texto de volumen
        Utiles.font.getData().setScale(2.0f); // Aumentar tamaño de fuente
        Utiles.font.setColor(Color.GRAY); // Color de la barra
        Utiles.font.draw(Utiles.batch, "VOLUMEN", barraVolumen.x, barraVolumen.y + 70, barraVolumen.width, Align.center, false);
        Utiles.batch.end();

        // Dibujar barra de volumen
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY); // Barra oscurecida
        shapeRenderer.rect(barraVolumen.x, barraVolumen.y, barraVolumen.width, barraVolumen.height);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(cursorVolumen.x, cursorVolumen.y, cursorVolumen.width, cursorVolumen.height);
        shapeRenderer.end();

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
        if (Gdx.input.isTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            // Volver al menú principal
            if (areaOpcionVolver.contains(touch.x, touch.y)) {
                Utiles.miJuego.setScreen(new PantallaPrincipal());
                return;
            }

            // Ajustar volumen si toca la barra
            if (barraVolumen.contains(touch.x, touch.y)) {
                volumenActual = (touch.x - barraVolumen.x) / barraVolumen.width;
                volumenActual = Math.max(0, Math.min(1, volumenActual)); // Limitar a [0, 1]

                // Mover el cursor
                cursorVolumen.x = barraVolumen.x + barraVolumen.width * volumenActual - cursorVolumen.width / 2;

                // Ajustar volumen de la música
                Recursos.ajustarVolumenMusica(volumenActual);

                // Guardar el volumen en las preferencias
                preferencias.putFloat("volumen", volumenActual);
                preferencias.flush();
            }
        }
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
        fondo.dispose();
        Recursos.detenerMusica();
        shapeRenderer.dispose();
    }
}
