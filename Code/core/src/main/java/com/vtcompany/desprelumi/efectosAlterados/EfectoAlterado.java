package com.vtcompany.desprelumi.efectosAlterados;

public abstract class EfectoAlterado {
	
	protected EfectoAlterado(String nombre, String desc, boolean positivo, int duracion) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.positivo = positivo;
		this.duracion = duracion;
	}
	
	protected String nombre, descripcion;
	protected boolean positivo;
	protected int duracion;
	
	public String getNombre() {return nombre;}
	public String getDescripcion() {return descripcion;}
	public boolean getpositivo() {return positivo;}
	public int getDuracion() {return duracion;}
	
}
