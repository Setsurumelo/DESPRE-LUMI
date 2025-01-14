package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.*;
import com.vtcompany.desprelumiServer.monstruos.Equipo;
import com.vtcompany.desprelumiServer.monstruos.Monstruo;
import com.vtcompany.desprelumiServer.tipos.Tipos;
import com.vtcompany.desprelumiServer.utiles.Utiles;

public abstract class Ataque {
	
	public Ataque(String nombre, String desc, int potencia, float prec, Tipos tipo, EfectoAlterado efecto,
			float prob, AlcancesDeEfectos alcance) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.potencia = potencia;
		this.probGolpe = prec;
		this.tipo = tipo;
		this.efectoAplicable = efecto;
		this.probEfectoAlterado = prob;
		this.alcance = alcance;
	}
	
	//Datos del ataque
	protected String nombre, descripcion;
	protected int potencia;
	protected Tipos tipo;
	protected float probGolpe;
	
	//Efecto
	protected EfectoAlterado efectoAplicable;
	protected float probEfectoAlterado;
	protected AlcancesDeEfectos alcance;
	
	public void usarEfecto(Equipo equipoAliado, Equipo equipoRival, Monstruo monstruoAtacante, Monstruo monstruoEnemigo) {
		if(this.efectoAplicable != null && Utiles.generarRandomFloat(1, 0) < this.probEfectoAlterado) {
			if(this.alcance == AlcancesDeEfectos.EQUIPO_PROPIO) {
				for(Monstruo monstruoAfectado : equipoAliado.getMonstruos()) {
					monstruoAfectado.aplicarEfectoAlerado(this.efectoAplicable);
				}
			}else if(this.alcance == AlcancesDeEfectos.EQUIPO_RIVAL) {
				for(Monstruo monstruoAfectado : equipoRival.getMonstruos()) {
					monstruoAfectado.aplicarEfectoAlerado(this.efectoAplicable);
				}
			}else if(this.alcance == AlcancesDeEfectos.MONSTRUO_PROPIO) {
				monstruoAtacante.aplicarEfectoAlerado(this.efectoAplicable);
			}else {
				monstruoEnemigo.aplicarEfectoAlerado(this.efectoAplicable);
			}
		}
	}
	
	public String getNombre() {return nombre;}
	public String getDescripcion() {return descripcion;}
	public int getPotencia() {return potencia;}
	public Tipos getTipo() {return tipo;}
	public float getProbGolpe() {return probGolpe;}
	public EfectoAlterado getEfectoAplicable() {return efectoAplicable;}
	public float getProbEfectoAlterado() {return probEfectoAlterado;}
}
