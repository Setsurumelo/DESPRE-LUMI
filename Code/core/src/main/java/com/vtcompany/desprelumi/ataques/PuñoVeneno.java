package com.vtcompany.desprelumi.ataques;

import com.vtcompany.desprelumi.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumi.efectosAlterados.Veneno;
import com.vtcompany.desprelumi.tipos.Tipos;

public final class PuñoVeneno extends Ataque {

	public PuñoVeneno() {
		super("Puño veneno", "Poderoso puño capaz de envenenar al rival.", 75, .9f, Tipos.VENENO, new Veneno(), .6f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
