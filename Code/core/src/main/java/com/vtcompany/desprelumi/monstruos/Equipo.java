package com.vtcompany.desprelumi.monstruos;

public final class Equipo {
	
	private final int CANTIDAD_MAXIMA_MONS = 3;	
	private int eleccionMonsruo;
	private Monstruo[] monstruos = new Monstruo[this.CANTIDAD_MAXIMA_MONS];
	private boolean[] monstruosFallecidos = new boolean[this.CANTIDAD_MAXIMA_MONS];
	private int monstruosEnEquipo;
	
	public Equipo(Monstruo[] monstruos) {
		this.monstruos = monstruos;
		this.monstruosFallecidos = new boolean[]{false, false, false};
	}
	
	public void curarEquipo() {
		for (int i = 0; i < monstruos.length; i++) {
			monstruos[i].vidaActual = monstruos[i].getVida();
			monstruos[i].atkActual = monstruos[i].getAtk();
			monstruos[i].velActual = monstruos[i].getVel();
			monstruos[i].defActual = monstruos[i].getDef();
			monstruos[i].efectosAplicados = null;	
		}
	}
	
	public int verificarMuerteMonstruo() {
		int numMuertos = 0;
		for(int i = 0 ; i < this.monstruos.length ; i++) {
			if(this.monstruos[i].getVidaActual() <= 0) {
				this.monstruos[i].vidaActual = 0;
				this.monstruosFallecidos[i] = true;
				numMuertos++;
			}
		}
		return numMuertos;
	}
	
	public void inicializarEleccion(int eleccion) {
		this.eleccionMonsruo = eleccion;
	}
	
	public void intercambiarMonstruos(int indice1, int indice2) {
		if(monstruos[indice1].vidaActual > 0) {
		    Monstruo temp = monstruos[indice1];
		    monstruos[indice1] = monstruos[indice2];
		    monstruos[indice2] = temp;
		}
	}

	public void aplicarEfecto(Monstruo[] monstruo) {
		for (int i = 0; i < monstruo.length; i++) {
			this.monstruos[i].aplicarEfectoAlerado(monstruo[i].getEfectosAplicados().getLast());
		}
	}
	
	public int getEleccionMonsruo() {return eleccionMonsruo;}
	public Monstruo[] getMonstruos() {return monstruos;}
	public boolean[] getMonstruosFallecidos() {return monstruosFallecidos;}
	
	
}
