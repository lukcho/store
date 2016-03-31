package store.entidades.help;

import store.model.dao.entities.FabCatalogo;
import store.model.manager.ManagerProductosServicios;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerProductosServicios mprod = new ManagerProductosServicios();
		FabCatalogo fcat = mprod.getcatalogo("Prod001");
		System.out.println(" pp "+fcat.getCatNombre()+" : "+fcat.getCatValor()+" ");
	}

}
