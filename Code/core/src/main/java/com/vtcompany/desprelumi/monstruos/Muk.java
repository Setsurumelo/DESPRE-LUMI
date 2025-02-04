package com.vtcompany.desprelumi.monstruos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.vtcompany.desprelumi.ataques.*;
import com.vtcompany.desprelumi.tipos.Tipos;
import com.vtcompany.desprelumi.utiles.Imagen;
import com.vtcompany.desprelumi.utiles.Recursos;

public final class Muk extends Monstruo {

    public Muk() {
        super("Muk", Tipos.VENENO, 80, 120, 100, 50);
        this.ataques[0] = new PuñoVeneno();
        this.ataques[1] = new NieblaInfria();
        this.ataques[2] = new PuñoNebula();
    }

    @Override
    protected void cargarSprites() {
        this.sprites[0] = new Imagen(Recursos.muk);
        this.sprites[1] = new Imagen(Recursos.mukFront);
        this.sprites[2] = new Imagen(Recursos.mukBack);
    }

    @Override
    protected void cargarAnimacionAtaqueFrente() {
        Array<TextureRegion> frames = new Array<>();
        for (Texture textura : Recursos.mukFrenteAtaque) {
            frames.add(new TextureRegion(textura));
        }
        animacionAtaqueFrente = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
    }

    @Override
    protected void cargarAnimacionAtaqueBack() {
        Array<TextureRegion> frames = new Array<>();
        for (Texture textura : Recursos.mukBackAtaque) {
            frames.add(new TextureRegion(textura));
        }
        animacionAtaqueBack = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
    }
}
