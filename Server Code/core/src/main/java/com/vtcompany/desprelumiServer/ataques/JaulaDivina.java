package com.vtcompany.desprelumiServer.ataques;

import com.vtcompany.desprelumiServer.efectosAlterados.AlcancesDeEfectos;
import com.vtcompany.desprelumiServer.efectosAlterados.EfectoJaulaDivina;
import com.vtcompany.desprelumiServer.tipos.Tipos;

public final class JaulaDivina extends Ataque {

	public JaulaDivina() {
		super("Jaula Divina", "No permite cambiar de pokemon al rival por 3 tuenos", 20, .8f, Tipos.LUZ, new EfectoJaulaDivina(), 1f, AlcancesDeEfectos.MONSTRUO_RIVAL);
	}

}
