package com.vtcompany.desprelumi.ataques;

import com.vtcompany.desprelumi.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumi.efectosAlterados.Congelado;
import com.vtcompany.desprelumi.tipos.Tipos;

public class NieblaInfria extends Ataque {

	public NieblaInfria() {
		super("Niebla Ingria", "Espesa niebla incapaz de hacer daño, pero con una leve posibiidad de congelar",
				0, .75f, Tipos.AGUA, new Congelado(), .25f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
