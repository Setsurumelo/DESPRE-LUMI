

package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumiServer.efectosAlterados.EfectoBendicionMaxima;
import com.vtcompany.desprelumiServer.tipos.Tipos;

public final class BendicionMaxima extends Ataque {

	public BendicionMaxima() {
		super("Bendicion Maxima", "Aumanta la velocidad en un 20%", 0, 1f, Tipos.LUZ, new EfectoBendicionMaxima(), 1f, AlcancesDeEfectos.MONSTRUO_PROPIO);
	}

}