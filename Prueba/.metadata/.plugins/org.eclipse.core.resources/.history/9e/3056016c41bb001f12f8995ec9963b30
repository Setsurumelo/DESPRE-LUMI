package com.vtcompany.desprelumiServer.efectosAlterados;

public class EfectoAlteradoAplicado {
	
	public EfectoAlteradoAplicado(EfectoAlterado efecto) {
		this.efectoBase = efecto;
		this.turnosUsados = 0;
	}
	
	protected EfectoAlterado efectoBase;
	protected int turnosUsados;
	
	public void pasarTurno() {
		this.turnosUsados ++;
	}
	
	public boolean efectoFinalizado() {
		return this.turnosUsados == this.efectoBase.getDuracion();
	}
	
	public EfectoAlterado getEfectoBase() {return efectoBase;}
	public int getTurnosUsados() {return turnosUsados;}
	
}
