package com.vtcompany.desprelumiServer.jugadores;

import com.vtcompany.desprelumiServer.monstruos.Equipo;
import com.vtcompany.desprelumiServer.monstruos.Monstruo;

public final class Jugador {
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	private int id = 0;
	private String nombre;
	private Equipo equipo;
	
	public void crearEquipo(Monstruo[] monstruos) {
		this.equipo = new Equipo(monstruos);
	}
	
	public int getId() {return id;}
	public String getNombre() {return nombre;}
	public Equipo getEquipo() {return this.equipo;}
	
	public void setid(boolean servidor, int newId) {
		if(servidor && id==0 && newId!=0) this.id = newId;
	}
	
}
