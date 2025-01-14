package com.vtcompany.desprelumi.ataques;

import com.vtcompany.desprelumi.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumi.efectosAlterados.EfectoAlterado;
import com.vtcompany.desprelumi.efectosAlterados.EfectoAlteradoAplicado;
import com.vtcompany.desprelumi.monstruos.Equipo;
import com.vtcompany.desprelumi.monstruos.Monstruo;
import com.vtcompany.desprelumi.tipos.Tipos;
import com.vtcompany.desprelumi.utiles.Utiles;

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
		this.efectoAplicado = new EfectoAlteradoAplicado(efecto);
	}
	
	//Datos del ataque
	protected String nombre, descripcion;
	protected int potencia;
	protected Tipos tipo;
	protected float probGolpe;
	
	//Efecto
	protected EfectoAlteradoAplicado efectoAplicado;
	protected EfectoAlterado efectoAplicable;
	protected float probEfectoAlterado;
	protected AlcancesDeEfectos alcance;
	
	public void usarEfecto() {
		if(this.efectoAplicable != null && Utiles.generarRandomFloat(1, 0) < this.probEfectoAlterado) {
			if(this.alcance == AlcancesDeEfectos.EQUIPO_PROPIO) {
				Utiles.hiloC.enviarMensaje("EfectoEP "+efectoAplicable.getNombre());
			}else if(this.alcance == AlcancesDeEfectos.EQUIPO_RIVAL) {
				Utiles.hiloC.enviarMensaje("EfectoER "+efectoAplicable.getNombre());
			}else if(this.alcance == AlcancesDeEfectos.MONSTRUO_PROPIO) {
				Utiles.hiloC.enviarMensaje("EfectoMP "+efectoAplicable.getNombre());
			}else if(this.alcance == AlcancesDeEfectos.MONSTRUO_RIVAL){
				Utiles.hiloC.enviarMensaje("EfectoMR "+efectoAplicable.getNombre());
			}
		}
	}
	
	public Monstruo aplicarEfectoIndividual(Monstruo monstruo) {
		monstruo.aplicarEfectoAlerado(this.getEfectoAplicado());
		return monstruo;
	}
	
	public Monstruo[] aplicarEfectoGrupal(Monstruo[] monstruo) {
		for (int i = 0; i < monstruo.length; i++) {
			monstruo[i].aplicarEfectoAlerado(this.getEfectoAplicado());
		}
		return monstruo;
	}
	
	public String getNombre() {return nombre;}
	public String getDescripcion() {return descripcion;}
	public int getPotencia() {return potencia;}
	public Tipos getTipo() {return tipo;}
	public float getProbGolpe() {return probGolpe;}
	public EfectoAlterado getEfectoAplicable() {return efectoAplicable;}
	public float getProbEfectoAlterado() {return probEfectoAlterado;}
	public EfectoAlteradoAplicado getEfectoAplicado() {return efectoAplicado;}
}
