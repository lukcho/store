package store.model.manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import store.general.connection.SingletonJDBC;
import store.model.dao.entities.Producto;
import store.model.dao.entities.ProductoFoto;

/**
 * Contiene la lógica de negocio para realizar el proceso de reserva de sitios
 * 
 * @author
 * 
 */
public class ManagerCarga {

	private SingletonJDBC conDao;
	
	@EJB
	ManagerCatalogos man;


	public ManagerCarga() {
		conDao = SingletonJDBC.getInstance();
	}

	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public ProductoFoto ProductoByDNI(String producto) throws Exception {
		ProductoFoto f = null;
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres, "
						+ " p.per_apellidos as apellidos, p.per_correo as correo, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia,"
						+ " (select pe.per_nombres||' '||pe.per_apellidos "
						+ " from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as nombreJefe "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE u.usu_login = '" + producto + "'");
		if (consulta != null) {
			consulta.next();
//			f = new FabProductoFoto();
//			f.setPerDNI(consulta.getString("dni"));
//			f.setPerNombres(consulta.getString("nombres"));
//			f.setPerApellidos(consulta.getString("apellidos"));
//			f.setPerCorreo(consulta.getString("correo"));
//			f.setCargo(consulta.getString("cargo"));
//			f.setJefeInmediato(consulta.getString("jefe"));
//			f.setPerGerencia(consulta.getString("gerencia"));
		}
		return f;
	}

	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public  List<ProductoFoto> funcionarioByGerencia(String prod_id) throws Exception {
		ProductoFoto f = null;
		List<ProductoFoto> filterUsers = new ArrayList<ProductoFoto>();
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres,  p.per_apellidos as apellidos, p.per_correo as correo, p.per_telefono as telefono, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE f.fun_gerencia = '" + prod_id + "'");
		if (consulta != null) {
			while(consulta.next()){
				f = new ProductoFoto();
				f.setProfId(Integer.parseInt(consulta.getString("dni")));
				f.setProfNombre(consulta.getString("nombres"));
				f.setProfDireccion(consulta.getString("apellidos"));
				f.setProfEstado(consulta.getString("correo"));
	//			f.setFabProducto(consulta.getString("cargo"));
				filterUsers.add(f);
			}
		}
		return filterUsers;
	}
	
	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Producto ProductoByprodfoto(String pro_id) throws Exception {
		Producto f = null;
		System.out.println(pro_id);
		ResultSet consulta = conDao
				.consultaSQL("SELECT distinct p.pro_id as codigo , p.cati_id as categoria, "
						+ " p.pro_codigo_barras as cod_barras, p.pro_tipo as tipo, "
						+ " p.pro_nombre as nombre, p.pro_descripcion as descripcion, "
						+ " p.pro_costo as costo, p.pro_precio as precio, "
						+ " p.pro_stock as stock, p.pro_estado as estado "
						+ " FROM fab_producto p INNER JOIN fab_producto_fotos pf ON p.pro_id = pf.pro_id "
						+ " WHERE pf.pro_id = '"+pro_id+"'");
		if (consulta != null) {
			consulta.next();
			f = new Producto();
			f.setProId(consulta.getString("codigo"));
			f.setProNombre(consulta.getString("nombre"));
			f.setFabCatalogoitem(consulta.getInt("categoria")); 
			f.setProDescripcion(consulta.getString("descripcion"));
			f.setProTipo(consulta.getString("tipo"));
			f.setProCodigoBarras(consulta.getString("cod_barras"));
			f.setProCosto(consulta.getBigDecimal("costo"));
			f.setProPrecio(consulta.getBigDecimal("precio"));	
			f.setProStock( consulta.getInt("stock"));
			f.setProEstado(consulta.getString("estado"));
			
			}
		return f;
	}
	
}
