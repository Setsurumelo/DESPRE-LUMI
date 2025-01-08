package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumiServer.efectosAlterados.Confusion;
import com.vtcompany.desprelumiServer.tipos.Tipos;

public class PuñoNebula extends Ataque {

	public PuñoNebula() {
		super("Puño nebula", "Tecnica especial de bob, parece provenir del mismo universo que el",
				75, .50f, Tipos.NORMAL, new Confusion(), .10f, AlcancesDeEfectos.MONSTRUO_PROPIO);
	}


}
