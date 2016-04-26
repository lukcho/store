package store.model.manager;

import store.model.dao.entities.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerProductosServicios{

	@EJB
	private ManagerDAO mDAO;

	private static FabProducto fab_prod;
	private static FabCatalogoitem fab_cati;
	private static FabDia fab_dia;
	
	String h="";		
				
	public ManagerProductosServicios() {
	}
	
	// Producto
	
	/**
	 * buscar todos productos
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabProducto> findprod() {
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
			h="Estado Modificado";
			}
		else if(prod.getProEstado().equals("I")){
			prod.setProEstado("A");
			h="Estado Modificado";
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
	
	//CATALOGOITEM
	
	/**
	 * buscar catalogo_item por ID
	 * @param prod_id
	 * @throws Exception
	 */
	public FabCatalogoitem CatalogoItemByID(Integer cati_id) throws Exception {
		return (FabCatalogoitem) mDAO.findById(FabCatalogoitem.class, cati_id);
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
	
	/**
	 * Agrega producto_catalogoItem
	 * @param prod_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarprodalogoItems(String nombre,String direccion) throws Exception {
		FabProductoFoto prodfoto = new FabProductoFoto();
		prodfoto.setFabProducto(fab_prod);
		prodfoto.setProfNombre(nombre);
		prodfoto.setProfDireccion(direccion);
		prodfoto.setProfEstado("A");
		mDAO.insertar(prodfoto);
	}

	/**
	 * Cambiar datos de producto_catalogoItem
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
		return mDAO.findWhere(FabProductoFoto.class, " o.profMostrar = true", null);
	}

	/**
	 * listar todos los productofotos
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabProductoFoto> findAllprodfotos() {
		return mDAO.findAll(FabProductoFoto.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<FabProductoFoto> getListProdfotoByID(String pro_id)
	{
		
		return mDAO.findWhere(FabProductoFoto.class, "o.prodId="+pro_id, null);
	}

	/**
	 * buscar productofotos por ID
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public FabProductoFoto productofotoByID(Integer prodfoto_id) throws Exception {
		return (FabProductoFoto) mDAO.findById(FabProductoFoto.class, prodfoto_id);
	}
	

	@SuppressWarnings("unchecked")
	public List<FabProductoFoto> productoFotoByNombre(String prodfoto_id) throws Exception {
		return mDAO.findWhere(FabProductoFoto.class, "o.profNombre='"+prodfoto_id+"'", null);
	}
	
	/**
	 * Agrega productofoto
	 * @param prod_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarproducto_foto(String nombre,String direccion) throws Exception {
		FabProductoFoto prodfoto = new FabProductoFoto();
		prodfoto.setFabProducto(fab_prod);
		prodfoto.setProfNombre(nombre);
		prodfoto.setProfDireccion(direccion);
		prodfoto.setProfEstado("A");
		prodfoto.setProfMostrar(false);
		mDAO.insertar(prodfoto);
	}
	
	/**
	 * Elimina productofoto
	 * @param prod_id
	 * @throws Exception
	 */
	public void eliminarproducto_foto(Integer prof_id) throws Exception {
		mDAO.eliminar(FabProductoFoto.class,prof_id);
	}
	
	/**
	 * Cambiar datos de producto_catalogoItem
	 * @param id_prod
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	public void editarproducto_foto(Integer prodfoto_id, String nombre, String valor, String direccion) throws Exception {
		FabProductoFoto prodfoto = this.productofotoByID(prodfoto_id);
		prodfoto.setFabProducto(fab_prod);
		prodfoto.setProfNombre(nombre);
		prodfoto.setProfDireccion(direccion);
		prodfoto.setProfEstado(valor);
		mDAO.actualizar(prodfoto);
	}
		
	/**
	 * Cambiar estado productofotos
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
		else if(prodfoto.getProfEstado().equals("I")){
			prodfoto.setProfEstado("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(prodfoto);
		return h;
		}		
	
	/**
	 * Cambiar estado productofotos
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioMostrarprodfoto(Integer prodfoto_id, FabProductoFoto prodf) throws Exception{
		String h="";
		List<FabProductoFoto> cond;
			cond = productoFotoByNombre(prodf.getProfNombre());
			for (FabProductoFoto y : cond) {
				if (y.getProfMostrar() == true) {
					y.setProfMostrar(false);
				}
			}
		FabProductoFoto prodfoto = productofotoByID(prodfoto_id);
		if(prodfoto.getProfMostrar() == false){
			prodfoto.setProfMostrar(true);
			h="Modificado mostrar imagen";
			}
		else if(prodfoto.getProfMostrar() == true){
			prodfoto.setProfMostrar(false);
			h="Modificado mostrar imagen";
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

	// DIA y horario disponible

	/**
	 * buscar todos Dias
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FabDia> finddia() {
		return mDAO.findWhere(FabDia.class, "1=1", null);
	}

	/**
	 * listar todos los Dias
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FabDia> findAllDias() {
		return mDAO.findAll(FabDia.class);
	}

	/**
	 * buscar Dia por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public FabDia DiaByID(Integer dia_id) throws Exception {
		return (FabDia) mDAO.findById(FabDia.class, dia_id);
	}
	
	/**
	 * Agrega productofoto
	 * @param prod_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarhorario(Time hora_inicio, Time hora_final) throws Exception {
		FabHorario horario = new FabHorario();
		horario.setFabProducto(fab_prod);
		horario.setFabDia(fab_dia);
		horario.setHorHoraInicio(hora_inicio);
		horario.setHorHoraFinal(hora_final);
		horario.setHorEstado("A");
		mDAO.insertar(horario);
	}
	
	/**
	 * Elimina dia
	 * @param prod_id
	 * @throws Exception
	 */
	public void eliminarHorario(Integer hor_id) throws Exception {
		mDAO.eliminar(FabHorario.class,hor_id);
	}
	
	/**
	 * buscar horario por prodid
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FabHorario> horarioByProdId(String prod_id) throws Exception {
		return mDAO.findWhere(FabHorario.class, "o.fabProducto.proId='"+prod_id+"'", null);
	}
	
	/**
	 * metodo para asignar el dia a un producto
	 * 
	 * @param u
	 *            Dia a analizar
	 * @return true o false
	 */
	public FabDia asignardia(Integer dia_id) {
		try {
			fab_dia = DiaByID(dia_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return fab_dia;
	}
	
	/**
	 * buscar horario por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public FabHorario HorarioByID(Integer hor_id) throws Exception {
		return (FabHorario) mDAO.findById(FabHorario.class, hor_id);
	}
	
	/**
	 * Cambiar estado productofotos
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadoHorario(Integer hor_id) throws Exception{
		String h="";
		FabHorario fabhor = HorarioByID(hor_id);						
		
		if(fabhor.getHorEstado().equals("A")){
			fabhor.setHorEstado("I");
			h="Estado del Horario Modificado";
			}
		else if(fabhor.getHorEstado().equals("I")){
			fabhor.setHorEstado("A");
			h="Estado del Horario Modificado";
			}
		mDAO.actualizar(fabhor);
		return h;
		}	
	
	// horario no disponible
	
		/**
		 * Agrega horarionodispo
		 * @param prod_id
		 * @param nombre
		 * @param valor
		 * @throws Exception
		 */
		public void insertarhorarioNoDis(Date fab_dia,Time hora_inicio, Time hora_final) throws Exception {
			FabHorarioNodisponible horario = new FabHorarioNodisponible();
			horario.setFabProducto(fab_prod);
			horario.setHornodisDia(fab_dia);
			horario.setHornodisHinicial(hora_inicio);
			horario.setHornodisHfinal(hora_final);
			horario.setHornodisEstado("A");
			mDAO.insertar(horario);
		}
		
		/**
		 * Elimina dia hora no disponible
		 * @param prod_id
		 * @throws Exception
		 */
		public void eliminarHorarioNoDis(Integer hor_id) throws Exception {
			mDAO.eliminar(FabHorarioNodisponible.class,hor_id);
		}
		
		/**
		 * buscar horario no dis por prodid
		 * 
		 * @param prod_id
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List<FabHorarioNodisponible> horarioNoDisByProdId(String prod_id) throws Exception {
			return mDAO.findWhere(FabHorarioNodisponible.class, "o.fabProducto.proId='"+prod_id+"'", null);
		}
		
		/**
		 * buscar horario por ID
		 * 
		 * @param prod_id
		 * @throws Exception
		 */
		public FabHorarioNodisponible HorarioNoDisByID(Integer hor_id) throws Exception {
			return (FabHorarioNodisponible) mDAO.findById(FabHorarioNodisponible.class, hor_id);
		}
		
		/**
		 * Cambiar estado productofotos
		 * @param id_prod
		 * @param nombre
		 * @param apellido
		 * @param correo
		 * @throws Exception
		 */	
		public String cambioEstadoHorarioNoDis(Integer hor_id) throws Exception{
			String h="";
			FabHorarioNodisponible fabhornodis = HorarioNoDisByID(hor_id);						
			
			if(fabhornodis.getHornodisEstado().equals("A")){
				fabhornodis.setHornodisEstado("I");
				h="Estado del Horario Modificado";
				}
			else if(fabhornodis.getHornodisEstado().equals("I")){
				fabhornodis.setHornodisEstado("A");
				h="Estado del Horario Modificado";
				}
			mDAO.actualizar(fabhornodis);
			return h;
			}	
	

}
