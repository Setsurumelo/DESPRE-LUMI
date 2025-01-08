package com.vtcompany.desprelumiServer.tipos;

public enum Tipos {
	NORMAL("Normal"),
	FUEGO("Fuego"),
	AGUA("Agua"),
	PLANTA("Planta"),
	METAL("Metal"),
	LUZ("Luz"),
	VENENO("Veneno"),
	OSCURIDAD("Oscuridad");
	
	private String nombre;
	
	private Tipos(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {return this.nombre;}
	
}
