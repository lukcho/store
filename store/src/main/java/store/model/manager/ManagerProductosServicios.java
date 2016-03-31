package store.model.manager;

import store.model.dao.entities.*;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerProductosServicios{

	@EJB
	private ManagerDAO mDAO;

	private static FabProducto fab_prod;
	private static FabCatalogoitem fab_cati;
	String h="";		
				
	public ManagerProductosServicios() {
	}
	
	// Producto
	
	/**
	 * buscar todos productos
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabProducto> findprodalogo() {
		return mDAO.findWhere(FabProducto.class, "1=1", null);
	}

	/**
	 * listar todos los productos
	 * @param prod_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabProducto> findAllProductos() {
		return mDAO.findAll(FabProducto.class);
	}

	/**
	 * buscar Productos por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public FabProducto ProductoByID(String prod_id) throws Exception {
		return (FabProducto) mDAO.findById(FabProducto.class, prod_id);
	}
	
	/**
	 * buscar catalogo_item por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public FabCatalogoitem CatalogoItemByID(Integer cati_id) throws Exception {
		return (FabCatalogoitem) mDAO.findById(FabCatalogoitem.class, cati_id);
	}
	
	/**
	 * Agrega Producto
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public void insertarProducto(String pro_id, String nombre, String cod_barras,String tipo ,String descripcion, BigDecimal costo, BigDecimal precio, Integer stock) throws Exception {
		FabProducto prod = new FabProducto();
		prod.setProId(pro_id);
		prod.setProNombre(nombre);
		prod.setProCodigoBarras(cod_barras);
		prod.setProTipo(tipo);
		prod.setProDescripcion(descripcion);
		prod.setProCosto(costo);
		prod.setProPrecio(precio);
		prod.setProStock(stock);
		prod.setProEstado("A");
		prod.setProEstadoFuncional("A");
		prod.setFabCatalogoitem(fab_cati);
		mDAO.insertar(prod);		
	}

	/**
	 * Cambiar datos de prodalogo
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */	
	public void editarproducto(String pro_id, String nombre, String cod_barras,String tipo, String descripcion, BigDecimal costo, BigDecimal precio, Integer stock, String estado, String estado_funcional) throws Exception {
		FabProducto prod = this.ProductoByID(pro_id);
		prod.setProId(pro_id);
		prod.setProNombre(nombre);
		prod.setProCodigoBarras(cod_barras);
		prod.setProDescripcion(descripcion);
		prod.setProCosto(costo);
		prod.setProPrecio(precio);
		prod.setProStock(stock);
		prod.setProEstado(estado);
		prod.setProTipo(tipo);
		prod.setProEstadoFuncional(estado_funcional);
		prod.setFabCatalogoitem(fab_cati);
		mDAO.actualizar(prod);
	}
	
	/**
	 * Cambiar estado prodalogo
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoprod(String prod_id) throws Exception{
		String h="";
		FabProducto prod = ProductoByID(prod_id);						
		
		if(prod.getProEstado().equals("A")){
			prod.setProEstado("I");
			h="Estado del prodalogo Modificado";
			}
		else if(prod.getProEstado().equals("I")){
			prod.setProEstado("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(prod);
		return h;
		}		
	
	/**
	 * Verifica si el producto esta activado
	 * @param u producto a analizar
	 * @return true o false
	 */
	public boolean esprodActivo(FabProducto u){
		boolean  resp = false;
		if(u.getProEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	/**
	 * 	metodo para asignar el producto a un item
	 * @param u prodalogo a analizar
	 * @return true o false
	 */
	 	public FabCatalogoitem asignarcati(Integer cati_id) {
	 		try {
	 			fab_cati = CatalogoItemByID(cati_id);	 				
			} catch (Exception e) {
				// TODO Auto-generated prodch block
				e.printStackTrace();
			}
	 		return fab_cati;
		}
	
	public FabCatalogo getcatalogo(String pro_id)
	{
		return (FabCatalogo) mDAO.findJPQL("select  c.* from FabCatalogo c, "
				+ "FabCatalogoitem ci, FabProducto p where p.fabCatalogoitem.catiId = ci.catiId and ci.fabCatalogo.catId=c.catId and p.proId = '"+pro_id+"'");
	}
	
	public FabProductoFoto getprodfoto(String pro_id)
	{
		return (FabProductoFoto) mDAO.findJPQL("select  pf.* from fab_producto_fotos pf, fab_producto p "
				+ "where p.pro_id = pf.pro_id and p.pro_id '"+pro_id+"'");
	}
	
	// PRODUCTOFOTOS
	/**
	 * buscar todos productofotos
	 * @param prodfoto_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabProductoFoto> findProdFoto() {
		return mDAO.findWhere(FabProductoFoto.class, " ", null);
	}

	/**
	 * listar todos los prodalogositems
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabProductoFoto> findAllprodfotos() {
		return mDAO.findAll(FabProductoFoto.class);
	}

	/**
	 * buscar producto_foto por ID
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public FabProductoFoto productofotoByID(Integer prodfoto_id) throws Exception {
		return (FabProductoFoto) mDAO.findById(FabProductoFoto.class, prodfoto_id);
	}
	
	/**
	 * Agrega producto_foto
	 * @param prod_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarprodalogoItems(Integer prodfoto_id, String nombre,String direccion) throws Exception {
		FabProductoFoto prodfoto = new FabProductoFoto();
		prodfoto.setFabProducto(fab_prod);
		prodfoto.setProfNombre(nombre);
		prodfoto.setProfDireccion(direccion);
		prodfoto.setProfEstado("A");
		mDAO.insertar(prodfoto);
	}

	/**
	 * Cambiar datos de prodalogoItems
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	public void editarprodalogoItems(Integer prodfoto_id, String nombre, String valor, String direccion) throws Exception {
		FabProductoFoto prodfoto = this.productofotoByID(prodfoto_id);
		prodfoto.setFabProducto(fab_prod);
		prodfoto.setProfNombre(nombre);
		prodfoto.setProfDireccion(direccion);
		prodfoto.setProfEstado(valor);
		mDAO.actualizar(prodfoto);
	}
	
	/**
	 * Cambiar estado prodalogoItems
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoprodfoto(Integer prodfoto_id) throws Exception{
		String h="";
		FabProductoFoto prodfoto = productofotoByID(prodfoto_id);						
		
		if(prodfoto.getProfEstado().equals("A")){
			prodfoto.setProfEstado("I");
			h="Estado del prodalogo Modificado";
			}
		else if(prodfoto.getProfEstado().equals("D")){
			prodfoto.setProfEstado("I");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(prodfoto);
		return h;
		}		
	
	/**
	 * Verifica si el producto_foto esta activado
	 * @param u prodalogo a analizar
	 * @return true o false
	 */
	public boolean esProdFotoActivo(FabProductoFoto u){
		boolean  resp = false;
		if(u.getProfEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	/**
	 * metodo para asignar el producto
	 * 
	 * @param u
	 *            producto a analizar
	 * @return true o false
	 */
	public FabProducto asignarprod(String prod_id) {
		try {
			fab_prod = ProductoByID(prod_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return fab_prod;
	}
	
}
