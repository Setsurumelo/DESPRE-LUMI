package com.vtcompany.desprelumi.ataques;

import com.vtcompany.desprelumi.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumi.efectosAlterados.EfectoJaulaDivina;
import com.vtcompany.desprelumi.tipos.Tipos;

public final class JaulaDivina extends Ataque {

	public JaulaDivina() {
		super("Jaula Divina", "No permite cambiar de pokemon al rival por 3 tuenos", 20, .8f, Tipos.LUZ, new EfectoJaulaDivina(), 1f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
