package store.controller.gestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import store.model.dao.entities.FabCatalogo;
import store.model.dao.entities.FabCatalogoitem;
import store.model.dao.entities.FabProducto;
import store.model.dao.entities.FabProductoFoto;
import store.model.generic.Funciones;
import store.model.manager.ManagerCatalogos;
import store.model.manager.ManagerProductosServicios;
import store.model.generic.Mensaje;


@SessionScoped
@ManagedBean
public class RecursosServiciosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerProductosServicios managerprod;
	@EJB
	private ManagerCatalogos managercat;

	
	private String prodser_id;
	private Integer cat_id;//para la seleccion de categoria
	private Integer cati_id;//para la seleccion de categoria
	private String categoria;
	private String categoriaitem;
	private String pro_cod_barras;
	private String pro_tipo;//servicio o producto
	private String pro_nombre;
	private String pro_descripcion;
	private BigDecimal pro_costo;
	private BigDecimal pro_precio;
	private Integer pro_stock;
	private String pro_estado;
	private String pro_estado_funcional;
	
	private boolean edicion;
	private boolean ediciontipo;

	//fabprodfoto imagenes
	private String imagen;
	private UploadedFile file;
	private String g;
	private boolean imagenprod;
	
	private List<FabProducto> listaProducto;
	private List<FabProductoFoto> listaProductofoto;
	
	public RecursosServiciosBean(){	
	}
		
	@PostConstruct
	public void ini() {
		imagen="300.jpg";
		pro_stock=1;
		pro_estado_funcional="A";
		cat_id=0;
		pro_costo=BigDecimal.ZERO;
		pro_precio=BigDecimal.ZERO;
		edicion = false;
		ediciontipo = false;
		imagenprod=false;
		listaProducto = managerprod.findAllProductos();
		listaProductofoto= new ArrayList<FabProductoFoto>();
	}
	
	public boolean isImagenprod() {
		return imagenprod;
	}

	public void setImagenprod(boolean imagenprod) {
		this.imagenprod = imagenprod;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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

	//accion para invocar el manager y crear producto
	public String crearProducto(){
		String r = "";
		try {
			if (edicion) {
				System.out.println("edicion :"+prodser_id+":::::"+ pro_nombre+"::::::"+ pro_cod_barras+"::::::"+ cati_id+"::::::"+
						pro_tipo+"::::::"+ pro_descripcion+"::::::"+ pro_costo+"::::::"+ pro_precio+"::::::"+ pro_stock);
				managerprod.editarproducto(prodser_id, pro_nombre, pro_cod_barras,
								pro_tipo, pro_descripcion, pro_costo,
								pro_precio, pro_stock, pro_estado,
								pro_estado_funcional);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				
//				r = "productos?faces-redirect=true";
//				// limpiamos los datos
//				pro_descripcion = "";
//				prodser_id = "";
//				pro_nombre = "";
//				pro_cod_barras = "";
//				pro_tipo = "";
//				pro_descripcion = "";
//				pro_costo = BigDecimal.ZERO;
//				pro_precio = BigDecimal.ZERO;
//				pro_stock = 1;
//				pro_estado = "A";
//				pro_estado_funcional = "";
//				imagen = "300.jpg";
//				edicion = false;
//				imagenprod=true;
//				getListaProducto().clear();
//				getListaProducto().addAll(managerprod.findAllProductos());
			} else {
				
				managerprod.insertarProducto(prodser_id, pro_nombre,
						pro_cod_barras, pro_tipo, pro_descripcion, pro_costo,
						pro_precio, pro_stock);
				Mensaje.crearMensajeINFO("Registrado - Creado");
				imagenprod = false;
				asignarprofoto();
//				r = "productos?faces-redirect=true";
//		     	reiniciamos datos (limpiamos el formulario)
//				pro_descripcion = "";
//				prodser_id = "";
//				pro_nombre = "";
//				pro_cod_barras = "";
//				pro_tipo = "";
//				pro_descripcion = "";
//				pro_costo = BigDecimal.ZERO;
//				pro_precio = BigDecimal.ZERO;
//				pro_stock = 1;
//				pro_estado = "A";
//				pro_estado_funcional = "";
//				imagen = "300.jpg";
//				edicion = false;
//				cat_id=0;
//				getListaProducto().clear();
//				getListaProducto().addAll(managerprod.findAllProductos());
			}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear producto",null));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),null));
			};
		//}
		return r;
	}

	//metodo para mostrar los Catalogositems en productos
	public List<SelectItem> getListaCatalogo(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0,"Seleccionar"));
		for(FabCatalogo t : managercat.findAllCatalogos()){
			listadoSI.add(new SelectItem(t.getCatId(),t.getCatNombre()));
		}
		return listadoSI;
	}
	
	//metodo para mostrar los Catalogositems en productos
	public List<SelectItem> getListaCatalogoitem(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		if(cat_id!=0){
			listadoSI.add(new SelectItem(0,"Seleccionar"));
			for(FabCatalogoitem t : managercat.findCatalogoItemsByCatalogo(cat_id)){
				listadoSI.add(new SelectItem(t.getCatiId(),t.getCatiNombre()));
			}
		}
		return listadoSI;
	}
	
	// metodo para asignar el catalogo al producto
	public String asignarCat() {
		System.out.println(cat_id);
		managercat.asignarcatalogo(cat_id);
		return "";
	}
	
	// metodo para asignar el catalogoitem al producto
	public String asignarCatItem() {
		System.out.println(cati_id);
	    managerprod.asignarcati(cati_id);
		return "";
	}
	
	// metodo para asignar el catalogoitem al producto
	public String asignarprofoto() {
		System.out.println(prodser_id);
	    managerprod.asignarprod(prodser_id);
		return "";
	}
	
//	// metodo para mostrar los catalogositem en Producto
//	public List<SelectItem> getListacatalogositems() {
//		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
//		List<FabCatalogoitem> listadoRecursos = managercat.findAllCatalogoItems();
//
//		for (Recursodisponible t : listadoRecursos) {
//			SelectItem item = new SelectItem(t.getIdRecdisponible(),
//					t.getDisponible());
//			listadoSI.add(item);
//		}
//		return listadoSI;
//	}
	
	//accion para cargar los datos en el formulario
		public String cargarProducto(FabProducto prod){
			try {
				prodser_id= prod.getProId();
				pro_descripcion=prod.getProDescripcion();
				pro_nombre=prod.getProNombre();
				pro_cod_barras=prod.getProCodigoBarras();
				pro_tipo=prod.getProTipo();
				pro_descripcion=prod.getProDescripcion();
				pro_costo=prod.getProCosto();
				pro_precio=prod.getProPrecio();
				if(pro_tipo=="P")
				{
				ediciontipo=true;	
				pro_stock=prod.getProStock();
				}
				pro_estado=prod.getProEstado();
				pro_estado_funcional=prod.getProEstadoFuncional();
				FabCatalogo fab = managercat.CatalogoByID(prod.getFabCatalogoitem().getFabCatalogo().getCatId());
				cat_id= fab.getCatId();
				cati_id=prod.getFabCatalogoitem().getCatiId();//sacar el catalogoitem
				cati_id=prod.getFabCatalogoitem().getCatiId();//sacar el catalogoitem
//				FabCatalogo fcat = managerprod.getcatalogo(prodser_id);//sacxar el tipo catalogo
//				cat_id=fcat.getCatId();
				//FabProductoFoto prodfoto = managerprod.getprodfoto(prodser_id);//sacar las fotos
				asignarNombreImagen();
				edicion = true;
				imagenprod=false;
				
				getListaProductofoto().clear();
				getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
				return "nproducto?faces-redirect=true";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "";
		}
	
	//activar y desactivar estado producto
	public String cambiarEstado(FabProducto prod){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("INFORMACION",managerprod.cambioEstadoprod(prod.getProId())));
	        getListaProducto().clear();
			getListaProducto().addAll(managerprod.findAllProductos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	//activar y desactivar estado fotoproducto
	public String cambiarEstadofoto(FabProductoFoto prod){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("INFORMACION",managerprod.cambioEstadoprodfoto(prod.getProfId())));
	        getListaProductofoto().clear();
			getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	// activar y desactivar estado fotoproducto
	public String eliminarfoto(FabProductoFoto prod) {
		try {
			managerprod.eliminarproducto_foto(prod.getProfId());
			getListaProductofoto().clear();
			getListaProductofoto().addAll(managerprod.productoFotoByNombre(prod.getProfNombre()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	// activar y desactivar estado fotoproducto
	public String guardarimagen() {
		try {
			managerprod.insertarproducto_foto(pro_nombre, imagen);
			getListaProductofoto().clear();
			getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
				
	
	
	//------ traslados--------
	
	public String irRecurso(){
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado!", "Actualizacion Cancelada"));
      //limpiamos los datos
        pro_descripcion="";
		prodser_id="";
		pro_nombre="";
		pro_cod_barras="";
		pro_tipo="";
		pro_descripcion="";
		pro_costo=BigDecimal.ZERO;
		pro_precio=BigDecimal.ZERO;
		pro_stock=1;
		pro_estado="A";
		pro_estado_funcional="";
		imagen = "300.jpg";
		edicion=false;
		return "";					
	}
	
	public String irRec(){
		return "recurso";
	}
	
	public String irTrecurso(){
		return "rectipo?faces-redirect=true";
	}
	public String irEvento(){
		System.out.println("SI");
		return "eventos";
	}			

	// metodo para guardar la imagen en el servidor
	public void ImagenServ(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (file != null) {
			try {
				// Tomar PAD REAL
				ServletContext servletContext = (ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext();
				String carpetaImagenes = (String) servletContext
						.getRealPath(File.separatorChar + "imgevent");
				setImagen(g);
				System.out.println("PAD------> " + carpetaImagenes);
				System.out.println("name------> " + getImagen());
				outputStream = new FileOutputStream(new File(carpetaImagenes
						+ File.separatorChar + getImagen()));
				inputStream = file.getInputstream();

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				System.out.println(imagen);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Correcto:", "Carga correcta"));
				guardarimagen();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
								"no se pudo subir la imagen"));
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
							"no se pudo seleccionar la imagen"));
		}
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagen() {
		if (getProdser_id().trim().isEmpty()) {
			System.out.println("Vacio");
		} else {
			System.out.println(getProdser_id());
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmm");
			g = "img_" + getProdser_id() + dateFormat.format(new Date())
					+ ".jpg";
			System.out.println(g);
		}
	}

	// metodo para poner el nombre a la imagen
	public void nombreImagen(String n) {
		List<FabProductoFoto> li = managerprod.findAllprodfotos();
		for (FabProductoFoto e : li) {
			if (e.getProfDireccion().contains(n)) {
				g = e.getProfDireccion();
			}
		}
		System.out.println(g);
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoProducto() {
		edicion = false;
		imagenprod=true;
		listaProductofoto.clear();
		return "nproducto?faces-redirect=true";
	}
	
	/**
	 * Cancela la accion de modificar o crear Institucion
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
		pro_costo = BigDecimal.ZERO;
		pro_precio = BigDecimal.ZERO;
		pro_stock = 1;
		pro_estado = "A";
		pro_estado_funcional = "";
		imagen = "300.jpg";
		edicion = false;
		ediciontipo=true;
		getListaProducto().clear();
		getListaProducto().addAll(managerprod.findAllProductos());
		return "productos?faces-redirect=true";
	}
	
	/**
	 * Lista de estados
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistEstados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}
	
	/**
	 * Lista de tipoprodser
	 * 
	 * @return lista de items de tiposprodser
	 */
	public List<SelectItem> getlistTipo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoProducto, Funciones.estadoProducto
				+ " : " + Funciones.valorEstadoProducto));
		lista.add(new SelectItem(Funciones.estadoServicio,
				Funciones.estadoServicio + " : "
						+ Funciones.valorEstadoServicio));
		return lista;
	}
	
	/**
	 * Lista de estadosfuncionales
	 * 
	 * @return lista de items de estadosfuncionales
	 */
	public List<SelectItem> getlistEstadosfuncional() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}

	public void tipoest() {
		System.out.println(getPro_tipo());
		if (getPro_tipo().equals("S")) {
			setPro_stock(0);
			ediciontipo=true;
		} else {
			ediciontipo=false;
		}
	}
	
	public void imagenprod() {
		System.out.println(getProdser_id());
		if (getProdser_id().equals("")) {
			ediciontipo=false;
		} else {
			ediciontipo=true;
		}
	}
	
	
}
