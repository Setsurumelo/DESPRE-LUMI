package com.vtcompany.desprelumiServer.monstruos;

public final class Equipo {
	
	private final int CANTIDAD_MAXIMA_MONS = 3;	
	private int eleccionMonsruo;
	private Monstruo[] monstruos = new Monstruo[this.CANTIDAD_MAXIMA_MONS];
	private boolean[] monstruosFallecidos = new boolean[this.CANTIDAD_MAXIMA_MONS];
	private int monstruosEnEquipo = 0;
	
	public Equipo(Monstruo[] monstruos) {
		this.monstruos = monstruos;
		this.monstruosFallecidos = new boolean[]{false, false, false};
	}
	
	public void verificarMuerteMonstruo() {
		for(int i = 0 ; i < this.monstruos.length ; i++) {
			if(this.monstruos[i].getVidaActual() == 0) {
				this.monstruosFallecidos[i] = true;
			}
		}
	}
	
	public void inicializarEleccion(int eleccion) {
		this.eleccionMonsruo = eleccion;
	}
	
	public void cambiarMonstruoElegido(int newEleccion) {
		if(this.eleccionMonsruo == newEleccion) {
			this.eleccionMonsruo = newEleccion;
		}
	}
	
	public void armarEquipoString(String monstruo) {
		switch(monstruo) {
			case "Muk":
				monstruos[monstruosEnEquipo]= new Muk();
				break;
			case "Dolores":
				monstruos[monstruosEnEquipo]= new Dolores();
				break;
			case "Bob":
				monstruos[monstruosEnEquipo]= new Bob();
				break;
		}
		monstruosEnEquipo++;
		if(monstruosEnEquipo==this.CANTIDAD_MAXIMA_MONS) {
			monstruosEnEquipo=0;
		}
	}
	
	public int getEleccionMonsruo() {return eleccionMonsruo;}
	public Monstruo[] getMonstruos() {return monstruos;}
	public boolean[] getMonstruosFallecidos() {return monstruosFallecidos;}
	
	
}
