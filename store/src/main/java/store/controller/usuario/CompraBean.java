package store.controller.usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import store.model.dao.entities.FabCatalogo;
import store.model.dao.entities.FabCatalogoitem;
import store.model.dao.entities.FabHorario;
import store.model.dao.entities.FabProducto;
import store.model.dao.entities.FabProductoFoto;
import store.model.dao.entities.Producto;
import store.model.manager.ManagerCarga;
import store.model.manager.ManagerCatalogos;
import store.model.manager.ManagerProductosServicios;

@SessionScoped
@ManagedBean
public class CompraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerProductosServicios managerprod;
	@EJB
	private ManagerCatalogos managercat;

	ManagerCarga mc;

	// PRODUCTOS Y SERVICIOS
	private String prodser_id;
	private Integer cat_id;// para la seleccion de categoria
	private Integer cati_id;// para la seleccion de categoria
	private Integer cati_idhijo;// para la seleccion de categoriahijo
	private String categoria;
	private String categoriaitem;
	private String pro_cod_barras;
	private String pro_tipo;// servicio o producto
	private String pro_nombre;
	private String pro_descripcion;
	private BigDecimal pro_costo;
	private BigDecimal pro_precio;
	private Integer pro_stock;
	private String pro_estado;
	private String pro_estado_funcional;

	private boolean mostrarpro_id;
	private boolean guardarin;

	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;

	private List<FabProducto> listaProducto;
	private List<FabProductoFoto> listaProductofoto;

	private FabProducto fabprod;
	private FabProductoFoto fabproductofoto;

	private Producto prod;

	// imagnees
	private List<String> images;
	
	
	//horarios
	private List<FabHorario> listaHorario;


	public CompraBean() {
	}

	@PostConstruct
	public void ini() {
		mc = new ManagerCarga();
		pro_stock = 1;
		pro_estado_funcional = "A";
		cat_id = 0;
		pro_costo = BigDecimal.ZERO;
		pro_precio = BigDecimal.ZERO;
		edicion = false;
		ediciontipo = false;
		verhorario = false;
		images = new ArrayList<String>();
		listaProducto = managerprod.findAllProductos();
		listaHorario = new ArrayList<FabHorario>();
	}

	public List<FabHorario> getListaHorario() {
		return listaHorario;
	}

	public void setListaHorario(List<FabHorario> listaHorario) {
		this.listaHorario = listaHorario;
	}
	
	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
	}

	public boolean isGuardarin() {
		return guardarin;
	}

	public void setGuardarin(boolean guardarin) {
		this.guardarin = guardarin;
	}

	public FabProductoFoto getFabproductofoto() {
		return fabproductofoto;
	}

	public void setFabproductofoto(FabProductoFoto fabproductofoto) {
		this.fabproductofoto = fabproductofoto;
	}

	public FabProducto getFabprod() {
		return fabprod;
	}

	public void setFabprod(FabProducto fabprod) {
		this.fabprod = fabprod;
	}

	public boolean isVerhorario() {
		return verhorario;
	}

	public void setVerhorario(boolean verhorario) {
		this.verhorario = verhorario;
	}

	public Integer getCati_idhijo() {
		return cati_idhijo;
	}

	public void setCati_idhijo(Integer cati_idhijo) {
		this.cati_idhijo = cati_idhijo;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public String getProdser_id() {
		return prodser_id;
	}

	public void setProdser_id(String prodser_id) {
		this.prodser_id = prodser_id;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public Integer getCati_id() {
		return cati_id;
	}

	public void setCati_id(Integer cati_id) {
		this.cati_id = cati_id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaitem() {
		return categoriaitem;
	}

	public void setCategoriaitem(String categoriaitem) {
		this.categoriaitem = categoriaitem;
	}

	public String getPro_cod_barras() {
		return pro_cod_barras;
	}

	public void setPro_cod_barras(String pro_cod_barras) {
		this.pro_cod_barras = pro_cod_barras;
	}

	public String getPro_tipo() {
		return pro_tipo;
	}

	public void setPro_tipo(String pro_tipo) {
		this.pro_tipo = pro_tipo;
	}

	public String getPro_nombre() {
		return pro_nombre;
	}

	public void setPro_nombre(String pro_nombre) {
		this.pro_nombre = pro_nombre;
	}

	public String getPro_descripcion() {
		return pro_descripcion;
	}

	public void setPro_descripcion(String pro_descripcion) {
		this.pro_descripcion = pro_descripcion;
	}

	public BigDecimal getPro_costo() {
		return pro_costo;
	}

	public void setPro_costo(BigDecimal pro_costo) {
		this.pro_costo = pro_costo;
	}

	public BigDecimal getPro_precio() {
		return pro_precio;
	}

	public void setPro_precio(BigDecimal pro_precio) {
		this.pro_precio = pro_precio;
	}

	public Integer getPro_stock() {
		return pro_stock;
	}

	public void setPro_stock(Integer pro_stock) {
		this.pro_stock = pro_stock;
	}

	public String getPro_estado() {
		return pro_estado;
	}

	public void setPro_estado(String pro_estado) {
		this.pro_estado = pro_estado;
	}

	public String getPro_estado_funcional() {
		return pro_estado_funcional;
	}

	public void setPro_estado_funcional(String pro_estado_funcional) {
		this.pro_estado_funcional = pro_estado_funcional;
	}

	public List<FabProducto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<FabProducto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<FabProductoFoto> getListaProductofoto() {
		return listaProductofoto;
	}

	public void setListaProductofoto(List<FabProductoFoto> listaProductofoto) {
		this.listaProductofoto = listaProductofoto;
	}

	/**
	 * accion para cargar los datos en el formulario
	 * 
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
	public String cargarProducto(FabProducto prod) {
		try {
			prodser_id = prod.getProId();
			pro_descripcion = prod.getProDescripcion();
			pro_nombre = prod.getProNombre();
			pro_cod_barras = prod.getProCodigoBarras();
			pro_tipo = prod.getProTipo();
			pro_descripcion = prod.getProDescripcion();
			pro_costo = prod.getProCosto();
			pro_precio = prod.getProPrecio();
			pro_stock = prod.getProStock();
			System.out.println(pro_tipo);
			pro_estado = prod.getProEstado();
			pro_estado_funcional = prod.getProEstadoFuncional();
			FabCatalogo fab = managercat.CatalogoByID(prod.getFabCatalogoitem()
					.getFabCatalogo().getCatId());
			FabCatalogoitem fabcati = managercat.CatalogoItemsByID(prod
					.getFabCatalogoitem().getCatiIdPadre());
			cat_id = fab.getCatId();
			cati_id = fabcati.getCatiId();
			cati_idhijo = prod.getFabCatalogoitem().getCatiId();
			System.out.println("este es categoria id " + cati_id);
			System.out.println("este es nombre id" + pro_nombre);
			edicion = true;
			if (pro_tipo.equals("P")) {
				ediciontipo = false;
				verhorario = false;
			} else if (pro_tipo.equals("S")) {
				ediciontipo = true;
				verhorario = true;
			}

			// images = new ArrayList<String>();
			// for (int i = 1; i <= 12; i++) {
			// FabProductoFoto fabprodfoto;
			// fabprodfoto =
			// managerprod.productofotoByID(Integer.parseInt(prodser_id));
			// images.add(""+fabprodfoto.getProfDireccion());
			// }

			getListaProductofoto().clear();
			getListaProductofoto().addAll(
					managerprod.productoFotoByNombre(pro_nombre));
			return "compra?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	
	

	// ------ traslados--------

	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<FabProductoFoto> getListaProdFoto() {
		List<FabProductoFoto> a = managerprod.findProdFoto();
		List<FabProductoFoto> l1 = new ArrayList<FabProductoFoto>();
		for (FabProductoFoto t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * Cancela la accion de irHorario
	 * 
	 * @return
	 */
	public String irLogin() {
		return "login?faces-redirect=true";
	}

	/**
	 * Cancela la accion de irHorario
	 * 
	 * @return
	 */
	public String irComprar() {
		try {
			prod = mc.ProductoByprodfoto(fabproductofoto.getFabProducto()
					.getProId());
			prodser_id = prod.getProId();
			pro_descripcion = prod.getProDescripcion();
			pro_nombre = prod.getProNombre();
			pro_cod_barras = prod.getProCodigoBarras();
			pro_tipo = prod.getProTipo();
			pro_descripcion = prod.getProDescripcion();
			pro_costo = prod.getProCosto();
			pro_precio = prod.getProPrecio();
			pro_stock = prod.getProStock();
			pro_estado = prod.getProEstado();
			pro_estado_funcional = prod.getProEstadoFuncional();
			System.out.println(prod.getFabCatalogoitem());
			FabCatalogoitem fabcati = managercat.CatalogoItemsByID(prod
					.getFabCatalogoitem());
			cati_id = fabcati.getCatiId();
			categoria = fabcati.getCatiNombre();
			cati_idhijo = prod.getFabCatalogoitem();
			System.out.println("este es categoria id " + cati_id);
			System.out.println("este es nombre id" + pro_nombre);
			edicion = true;
			if (pro_tipo.equals("P")) {
				ediciontipo = false;
				verhorario = false;
			} else if (pro_tipo.equals("S")) {
				ediciontipo = true;
				verhorario = true;
			}
			
			getListaHorario().clear();
			getListaHorario().addAll(
					managerprod.horarioByProdId(prodser_id));
			
			for (int i = 1; i <= 12; i++) {
	        	List<FabProductoFoto> cond;
				try {
					cond = managerprod.productoFotoByNombre(prod.getProNombre());
					for (FabProductoFoto y : cond) {
						System.out.println(y.getProfDireccion());
						images.add(y.getProfDireccion());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "compra?faces-redirect=true";
	}

	public void abrirDialog() {
		RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		pro_descripcion = "";
		prodser_id = "";
		pro_nombre = "";
		pro_cod_barras = "";
		pro_tipo = "";
		pro_descripcion = "";
		pro_costo = null;
		pro_precio = null;
		pro_stock = null;
		pro_estado = "A";
		pro_estado_funcional = "";
		edicion = false;
		guardarin = false;
		ediciontipo = false;
		verhorario = false;
		getImages().clear();
		getListaProdFoto().clear();
		getListaProdFoto().addAll(managerprod.findProdFoto());
		return "index?faces-redirect=true";
	}

	public List<String> getImages() {
		return images;
	}
}
