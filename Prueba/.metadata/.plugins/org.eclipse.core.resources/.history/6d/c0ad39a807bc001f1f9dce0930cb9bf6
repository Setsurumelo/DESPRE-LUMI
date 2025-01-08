package com.vtcompany.desprelumi.monstruos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.vtcompany.desprelumi.ataques.*;
import com.vtcompany.desprelumi.tipos.Tipos;
import com.vtcompany.desprelumi.utiles.Imagen;
import com.vtcompany.desprelumi.utiles.Recursos;

public class Dolores extends Monstruo {

    public Dolores() {
        super("Dolores", Tipos.LUZ, 150, 80, 80, 40);
        this.ataques[0] = new GolpeSagrado();
        this.ataques[1] = new JaulaDivina();
        this.ataques[2] = new BendicionMaxima();
    }

    @Override
    protected void cargarSprites() {
        this.sprites[0] = new Imagen(Recursos.dolores);
        this.sprites[1] = new Imagen(Recursos.doloresFront);
        this.sprites[2] = new Imagen(Recursos.doloresBack);
    }

    @Override
    protected void cargarAnimacionAtaqueFrente() {
        Array<TextureRegion> frames = new Array<>();
        for (Texture textura : Recursos.doloresFrenteAtaque) {
            frames.add(new TextureRegion(textura));
        }
        animacionAtaqueFrente = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
    }

    @Override
    protected void cargarAnimacionAtaqueBack() {
        Array<TextureRegion> frames = new Array<>();
        for (Texture textura : Recursos.doloresBackAtaque) {
            frames.add(new TextureRegion(textura));
        }
        animacionAtaqueBack = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
    }
}
