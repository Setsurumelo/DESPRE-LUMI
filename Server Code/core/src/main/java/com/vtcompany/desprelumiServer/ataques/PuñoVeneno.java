package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumiServer.efectosAlterados.Veneno;
import com.vtcompany.desprelumiServer.tipos.Tipos;

public final class PuñoVeneno extends Ataque {

	public PuñoVeneno() {
		super("Puño veneno", "Poderoso puño capaz de envenenar al rival.", 75, .9f, Tipos.VENENO, new Veneno(), .6f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
