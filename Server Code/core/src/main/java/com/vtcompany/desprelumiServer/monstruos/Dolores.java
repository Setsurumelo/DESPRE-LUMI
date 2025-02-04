package com.vtcompany.desprelumiServer.monstruos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.vtcompany.desprelumiServer.ataques.NeblinaMagica;
import com.vtcompany.desprelumiServer.ataques.NieblaInfria;
import com.vtcompany.desprelumiServer.ataques.PuñoNebula;
import com.vtcompany.desprelumiServer.tipos.Tipos;
import com.vtcompany.desprelumiServer.utiles.Imagen;
import com.vtcompany.desprelumiServer.utiles.Recursos;

public class Dolores extends Monstruo{

	public Dolores() {
		super("Dolores", Tipos.LUZ, 150, 80, 80, 40);
		this.ataques[0] = new NeblinaMagica();
		this.ataques[1] = new NieblaInfria();
		this.ataques[2] = new PuñoNebula();
	}

	@Override
	protected void cargarSprites() {
		this.sprites[0] = new Imagen(Recursos.DOLORES);
		this.sprites[1] = new Imagen(Recursos.DOLORES_FRONT);
		this.sprites[2] = new Imagen(Recursos.DOLORES_BACK);
	}

	@Override
	protected void cargarAnimacionAtaqueFrente() {
		Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i <= 10; i++) {
            frames.add(new TextureRegion(new Texture(Recursos.DOLORES_FRENTE_ATAQUE[i])));
        }
        animacionAtaqueFrente = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
	}

	@Override
	protected void cargarAnimacionAtaqueBack() {
		Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i <= 10; i++) {
            frames.add(new TextureRegion(new Texture(Recursos.DOLORES_BACK_ATAQUE[i])));
        }
        animacionAtaqueBack = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);
	}

}
