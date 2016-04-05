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

	//PRODUCTOS Y SERVICIOS
	private String prodser_id;
	private Integer cat_id;//para la seleccion de categoria
	private Integer cati_id;//para la seleccion de categoria
	private Integer cati_idhijo;//para la seleccion de categoriahijo
	private String categoria;
	private String categoriaitem;
	private String pro_cod_barras;
	private String pro_tipo;//servicio o producto
	private String pro_nombre;
	private String pro_descripcion;
	private String pro_costo;
	private String pro_precio;
	private String pro_stock;
	private String pro_estado;
	private String pro_estado_funcional;
	
	private boolean mostrarpro_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;

	//fabprodfoto imagenes
	private String imagen;
	private UploadedFile file;
	private String g;
	private boolean imagenprod;
	
	private List<FabProducto> listaProducto;
	private List<FabProductoFoto> listaProductofoto;
	
	
	//horario 
	
	
	
	
	public RecursosServiciosBean(){	
	}
		
	@PostConstruct
	public void ini() {
		imagen="300.jpg";
		pro_stock=null;
		pro_estado_funcional="A";
		cat_id=0;
		pro_costo=null;
		pro_precio=null;
		edicion = false;
		ediciontipo = false;
		imagenprod=false;
		verhorario=false;
		mostrarpro_id=false;
		listaProducto = managerprod.findAllProductos();
		listaProductofoto= new ArrayList<FabProductoFoto>();
	}
	
	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
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

	public String getPro_costo() {
		return pro_costo;
	}

	public void setPro_costo(String pro_costo) {
		this.pro_costo = pro_costo;
	}

	public String getPro_precio() {
		return pro_precio;
	}

	public void setPro_precio(String pro_precio) {
		this.pro_precio = pro_precio;
	}

	public String getPro_stock() {
		return pro_stock;
	}

	public void setPro_stock(String pro_stock) {
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

	/**
	 * accion para invocar el manager y crear producto
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
	public String crearProducto(){
		String r = "";
		try {
			BigDecimal procosto = new BigDecimal(pro_costo.replace(",", ".")); 
			BigDecimal proprecio =	new BigDecimal(pro_precio.replace(",", "."));
			Integer prostock =  Integer.parseInt(pro_stock);
			if (edicion) {
				managerprod.editarproducto(prodser_id, pro_nombre, pro_cod_barras,
								pro_tipo, pro_descripcion, procosto,
								proprecio, prostock, pro_estado,
								pro_estado_funcional);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				if(pro_tipo.equals("S"))
				{
				verhorario = true;
				}else 
					verhorario=false;
				asignarprofoto();
			} else {
					if(!averiguarproductoid(prodser_id)){
						managerprod.insertarProducto(prodser_id, pro_nombre,
								pro_cod_barras, pro_tipo, pro_descripcion, procosto,
								proprecio, prostock);
						Mensaje.crearMensajeINFO("Registrado - Creado");
						imagenprod = false;
						if(pro_tipo.equals("S"))
						{
						verhorario = true;
						}else
							verhorario=false;
						asignarprofoto();
					}
			}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear producto",null));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),null));
			};
		return r;
	}

	/**
	 * accion para cargar los datos en el formulario
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
		public String cargarProducto(FabProducto prod){
			try {
				prodser_id= prod.getProId();
				pro_descripcion=prod.getProDescripcion();
				pro_nombre=prod.getProNombre();
				pro_cod_barras=prod.getProCodigoBarras();
				pro_tipo=prod.getProTipo();
				pro_descripcion=prod.getProDescripcion();
				pro_costo=prod.getProCosto().toString();
				pro_precio=prod.getProPrecio().toString();
				pro_stock=prod.getProStock().toString();
				pro_estado=prod.getProEstado();
				pro_estado_funcional=prod.getProEstadoFuncional();
				FabCatalogo fab = managercat.CatalogoByID(prod.getFabCatalogoitem().getFabCatalogo().getCatId());
				FabCatalogoitem fabcati = managercat.CatalogoItemsByID(prod.getFabCatalogoitem().getCatiIdPadre());
				cat_id= fab.getCatId();
				cati_id = fabcati.getCatiId();
				cati_idhijo = prod.getFabCatalogoitem().getCatiId();
				asignarCatItem();
				edicion = true;
				imagenprod=false;
				mostrarpro_id=true;
				if(pro_tipo.equals("P"))
				{
				ediciontipo=false;	
				verhorario=false;
				}
				else if(pro_tipo.equals("S"))
				{
				ediciontipo=true;
				verhorario=true;
				}
				getListaProductofoto().clear();
				getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
				return "nproducto?faces-redirect=true";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "";
		}
	
	/**
	 * activar y desactivar estado producto
	 * @param pro_id
	 * @throws Exception
	 */
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
	
	//prodfoto
	
	/**
	 * activar y desactivar estado fotoproducto
	 * @param pro_id
	 * @throws Exception
	 */
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

	/**
	 * eliminar un fotoproducto
	 * @param pro_id
	 * @throws Exception
	 */
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

	/**
	 * guardar una imagen fotoproducto
	 * @param pro_id
	 * @throws Exception
	 */
	public String guardarimagen() {
		try {
			asignarprofoto();
			managerprod.insertarproducto_foto(pro_nombre, imagen);
			getListaProductofoto().clear();
			getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	/**
	 * metodo para asignar el producto al productofoto
	 * 
	 */
	public String asignarprofoto() {
	    managerprod.asignarprod(prodser_id);
		return "";
	}
	
	/**
	 * metodo para conocer el prodid si esta usado
	 * 
	 */
    public boolean averiguarproductoid(String proserid){
 		Integer t=0;
 		boolean r=false;
 		List<FabProducto> pro = managerprod.findAllProductos();
 		for (FabProducto y :pro){
 			if (y.getProId().equals(prodser_id)){
 				System.out.println("si entra1");	  						  							
 				t=1;
 				r= true;
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El codigo del producto existe.",null));
 			}
 		}
 		if (t==0){ 			
 			r= false;
 		}
		return r; 		
 	}
	
				
	//CATALOGO y CATALOGO ITEMS
	
	/**
	 * metodo para mostrar los Catalogositems en productos
	 *  
	 */
	public List<SelectItem> getListaCategoria(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		if(cat_id!=0){
			listadoSI.add(new SelectItem(0,"Seleccionar"));
			for(FabCatalogoitem t : managercat.findCatalogoItemsByCatalogo(cat_id)){
				listadoSI.add(new SelectItem(t.getCatiId(),t.getCatiNombre()));
			}
		}
		return listadoSI;
	}
	
	/**
	 * metodo para mostrar los Catalogositems en productos
	 * 
	 */
	public List<SelectItem> getListaCatalogo(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0,"Seleccionar"));
		for(FabCatalogo t : managercat.findAllCatalogos()){
			listadoSI.add(new SelectItem(t.getCatId(),t.getCatNombre()));
		}
		return listadoSI;
	}
	
	/**
	 * metodo para mostrar los Catalogositems en productos
	 *  
	 */
	public List<SelectItem> getListaCatalogoitem(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
			listadoSI.add(new SelectItem(0,"Seleccionar"));
			for(FabCatalogoitem t : managercat.findCatalogoItemsByCatalogo(cat_id)){
				listadoSI.add(new SelectItem(t.getCatiId(),t.getCatiNombre()));
			}
		return listadoSI;
	}
	
	/**
	 * metodo para mostrar los Catalogositemsitems en productos
	 *  
	 */
	public List<SelectItem> getListaCatalogoitemitems(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		if(cat_id!= 0){
			listadoSI.add(new SelectItem(0,"Seleccionar"));
			for(FabCatalogoitem t : managercat.findCatalogoItemshijosByCatalogo(cati_id)){
				listadoSI.add(new SelectItem(t.getCatiId(),t.getCatiNombre()));
			}
		}
		return listadoSI;
	}
	
	
	//CATALOGO CATALOGOTITEM
	/**
	 * metodo para asignar el catalogo al producto
	 * 
	 */
	public String asignarCat() {
		managercat.asignarcatalogo(cat_id);
		return "";
	}
	
	/**
	 * 	metodo para asignar el catalogoitem al producto
	 * 
	 */
	public String asignarCatItem() {
	    managerprod.asignarcati(cati_idhijo);
		return "";
	}

	/**
	 * metodo para guardar la imagen en el servidor
	 * 
	 */
	public void ImagenServ(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		System.out.println(pro_nombre);
		if (pro_nombre != null && pro_nombre !="") {
			asignarNombreImagencargado(pro_nombre);
			int i = listaProductofoto.size()+1;
			System.out.println(i);
			if (i < 6) {
				if (file != null) {
					try {
						// Tomar PAD REAL
						ServletContext servletContext = (ServletContext) FacesContext
								.getCurrentInstance().getExternalContext()
								.getContext();
						String carpetaImagenes = (String) servletContext
								.getRealPath(File.separatorChar + "imgevent");
						setImagen(g);
						System.out.println("PAD------> " + carpetaImagenes);
						System.out.println("name------> " + getImagen());
						outputStream = new FileOutputStream(new File(
								carpetaImagenes + File.separatorChar
										+ getImagen()));
						inputStream = file.getInputstream();

						int read = 0;
						byte[] bytes = new byte[1024];

						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Correcto:", "Carga correcta"));
						guardarimagen();
					} catch (Exception e) {
						FacesContext.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_ERROR,
												"Error:",
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
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error:",
									"no se pudo seleccionar la imagen"));
				}
			}else
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
							"Sólo puede subir 5 imagenes"));
		}else
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
						"debe crear o guardar primero un producto o servicio"));
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagen() {
		if (getProdser_id().trim().isEmpty()) {
			System.out.println("Vacio");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
							"debe crear o guardar primero un producto o servicio"));
		} else {
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
			g = "img_" + getProdser_id() + dateFormat.format(new Date())
					+ ".jpg";
			System.out.println(g);
		}
	}
		
	// metodo para poner el nombre a la imagen
		public void asignarNombreImagencargado(String pro_nombre) {
			if (getProdser_id().trim().isEmpty()) {
				System.out.println("Vacio");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
								"debe crear o guardar primero un producto o servicio"));
			} else {
				DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
				g = "img_" + pro_nombre + dateFormat.format(new Date())
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
		verhorario=false;
		mostrarpro_id=false;
		edicion = false;
		imagenprod=true;
		listaProductofoto.clear();
		return "nproducto?faces-redirect=true";
	}
	
	//horarios
	
	
	
	
	
	//ESTADOS
	
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
		if (getPro_tipo().equals("S")) {
			cat_id=2;
			getListaCategoria();
			setPro_stock("1");
			ediciontipo=true;
			verhorario=false;
		} else {
			cat_id=1;
			getListaCategoria();
			ediciontipo=false;
			verhorario=true;
		}
	}
	
	public void imagenprod() {
		if (getProdser_id().equals("")) {
			ediciontipo=false;
		} else {
			ediciontipo=true;
		}
	}
	
	//------ traslados--------
	
	/**
	 * ir nuevo producto o servicio
	 * 
	 * @return
	 */
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
		pro_costo=null;
		pro_precio=null;
		pro_stock="1";
		pro_estado="A";
		pro_estado_funcional="";
		imagen = "300.jpg";
		edicion=false;
		return "";					
	}
	
	/**
	 * limpia la informacion de horario
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String volverproducto() throws Exception {
		// limpiar datos
		getListaProductofoto().clear();
		getListaProductofoto().addAll(managerprod.productoFotoByNombre(pro_nombre));
		return "nproducto?faces-redirect=true";
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
		pro_stock = "1";
		pro_estado = "A";
		pro_estado_funcional = "";
		imagen = "300.jpg";
		edicion = false;
		ediciontipo=false;
		verhorario=false;
		getListaProducto().clear();
		getListaProducto().addAll(managerprod.findAllProductos());
		return "productos?faces-redirect=true";
	}
	
	/**
	 * Cancela la accion de irHorario
	 * 
	 * @return
	 */
	public String irHorario() {
		String r="";
		if(pro_tipo.equals("S"))
		{
		r= "horariodisponible?faces-redirect=true";
		}
		else
		{
			Mensaje.crearMensajeINFO("El tipo producto no contiene horario");
		}
		return r;
	}
}
