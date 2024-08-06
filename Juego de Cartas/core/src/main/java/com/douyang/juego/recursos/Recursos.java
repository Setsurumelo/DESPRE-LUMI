package com.douyang.juego.recursos;

public enum Recursos {
	PANTALLA_PRINCIPAL("Logo/Logo.png"),
	BACKROUND_PANTALLA_MENU_PRINCIPAL("Pantalla Menu Principal/Backround.png"),
	BOTON_JUGAR_PANTALLA_MENU_PRINCIPAL("Pantalla Menu Principal/Boton jugar.png");
	
	private String ruta;
	
	private Recursos(String ruta){
		this.ruta = ruta;
	}
	
	public String getRuta() {
		return ruta;
	}
	
}
