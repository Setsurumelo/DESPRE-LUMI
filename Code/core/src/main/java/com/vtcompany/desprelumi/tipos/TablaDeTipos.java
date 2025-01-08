package com.vtcompany.desprelumi.tipos;

public final class TablaDeTipos {
	
	public static boolean comparacionDeTablaTipos(Tipos tipoAtaque, Tipos tipoMonstruo) {
		boolean debilidad = false;
		
		if(tipoAtaque == Tipos.FUEGO && tipoMonstruo == Tipos.PLANTA) debilidad = true;
		else if(tipoAtaque == Tipos.PLANTA && tipoMonstruo == Tipos.AGUA) debilidad = true;
		else if(tipoAtaque == Tipos.AGUA && tipoMonstruo == Tipos.FUEGO) debilidad = true;
		else if(tipoAtaque == Tipos.METAL && tipoMonstruo == Tipos.VENENO) debilidad = true;
		else if(tipoAtaque == Tipos.VENENO && tipoMonstruo == Tipos.LUZ) debilidad = true;
		else if(tipoAtaque == Tipos.LUZ && tipoMonstruo == Tipos.OSCURIDAD) debilidad = true;
		else if(tipoAtaque == Tipos.OSCURIDAD && tipoMonstruo == Tipos.METAL) debilidad = true;
		else debilidad = false;
		
		return debilidad;
	}
	
}
