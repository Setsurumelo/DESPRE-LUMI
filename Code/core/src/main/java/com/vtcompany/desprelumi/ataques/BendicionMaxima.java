

package com.vtcompany.desprelumi.ataques;

import com.vtcompany.desprelumi.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumi.efectosAlterados.EfectoBendicionMaxima;
import com.vtcompany.desprelumi.tipos.Tipos;

public final class BendicionMaxima extends Ataque {

	public BendicionMaxima() {
		super("Bendicion Maxima", "Aumanta la velocidad en un 20%", 0, 1f, Tipos.LUZ, new EfectoBendicionMaxima(), 1f, AlcancesDeEfectos.MONSTRUO_PROPIO);
	}

}
