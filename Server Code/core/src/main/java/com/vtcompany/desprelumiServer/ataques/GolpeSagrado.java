package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumiServer.efectosAlterados.Confusion;
import com.vtcompany.desprelumiServer.tipos.Tipos;

public final class GolpeSagrado extends Ataque {

	public GolpeSagrado() {
		super("Golpe sagrado", "Golpe bendecido por alguna entidad superior", 60, 1, Tipos.LUZ, new Confusion(), .1f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
