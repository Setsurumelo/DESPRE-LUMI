package com.vtcompany.desprelumiServer.monstruos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.vtcompany.desprelumiServer.ataques.Ataque;
import com.vtcompany.desprelumiServer.efectosAlterados.EfectoAlterado;
import com.vtcompany.desprelumiServer.efectosAlterados.EfectoAlteradoAplicado;
import com.vtcompany.desprelumiServer.tipos.Tipos;
import com.vtcompany.desprelumiServer.utiles.Imagen;


public abstract class Monstruo {
	
	public Monstruo(String nombre, Tipos tipo, int vida, int atk, int def, int vel) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.vidaActual = this.vida = vida;
		this.atkActual = this.atk = atk;
		this.defActual = this.def = def;
		this.velActual = this.vel = vel;
		cargarSprites();
	}
	
	protected String nombre;
	protected Tipos tipo;
	protected int vida, atk, def, vel;
	protected int vidaActual, atkActual, defActual, velActual;
	protected Imagen[] sprites = new Imagen[3];
	protected Ataque[] ataques = new Ataque[3];
	protected ArrayList<EfectoAlteradoAplicado> efectosAplicados = new ArrayList<EfectoAlteradoAplicado>();
	protected Animation<TextureRegion> animacionAtaqueFrente;
	protected Animation<TextureRegion> animacionAtaqueBack;
	
	protected abstract void cargarSprites();
	
	protected abstract void cargarAnimacionAtaqueFrente();
	protected abstract void cargarAnimacionAtaqueBack();
	
	public void aplicarEfectoAlerado(EfectoAlterado efecto) {
		this.aplicarEfectoAlerado(new EfectoAlteradoAplicado(efecto));
	}
	
	public void aplicarEfectoAlerado(EfectoAlteradoAplicado efecto ) {
		if(!(efecto.getEfectoBase().getNombre() == "Veneno" && this.tipo == Tipos.VENENO || 
				this.buscarEfecto("Velo Sagrado") != -1)) {
		}else {
			int busqueda = this.buscarEfecto(efecto.getEfectoBase().getNombre());
			if(busqueda != -1) this.efectosAplicados.remove(busqueda);
			this.efectosAplicados.add(efecto);
		}
	}
	
	private int buscarEfecto(String nombre) {
		int existe = -1;
		for(int i = 0 ; i < this.efectosAplicados.size() ; i++) {
			if(this.efectosAplicados.get(i).getEfectoBase().getNombre() == nombre) existe = i;
		}
		return existe;
	}
	
	public void finaEfectosAlterados() {
		boolean eliminado = false;
		do {
			for(int i = 0 ; i < this.efectosAplicados.size() ; i++) {
				EfectoAlteradoAplicado e = this.efectosAplicados.get(i);
				
				if(e.getEfectoBase().getNombre() == "Veneno") this.vidaActual -= this.vida/8;
				
				e.pasarTurno();
				if(e.efectoFinalizado()) {
					this.efectosAplicados.remove(i);
					eliminado = true;
				}
			}
		}while(eliminado);
	}
	
	public void recibirDaño(int daño) {
		this.vidaActual -= daño;
		if(this.vidaActual < 0) this.vidaActual = 0;
	}
	
	public String getNombre() {return nombre;}
	public Tipos getTipo() {return tipo;}
	public Ataque[] getAtaques() {return ataques;}
	public ArrayList<EfectoAlteradoAplicado> getEfectosAplicados() {return efectosAplicados;}
	
	public int getVida() {return vida;}
	public int getAtk() {return atk;}
	public int getDef() {return def;}
	public int getVel() {return vel;}
	public int getVidaActual() {return vidaActual;}
	public int getAtkActual() {return atkActual;}
	public int getDefActual() {return defActual;}
	public int getVelActual() {return velActual;}
	
	
	public Animation<TextureRegion> getAnimacionAtaqueFrente() {
		return animacionAtaqueFrente;
	}

	public Animation<TextureRegion> getAnimacionAtaqueBack() {
		return animacionAtaqueBack;
	}

	public Imagen[] getSprites() {return sprites;}
}
