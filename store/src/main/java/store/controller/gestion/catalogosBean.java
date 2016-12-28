package store.controller.gestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import store.model.dao.entities.FabCatalogo;
import store.model.dao.entities.FabCatalogoitem;
import store.model.generic.Funciones;
import store.model.manager.ManagerCatalogos;
import store.model.generic.Mensaje;

@SessionScoped
@ManagedBean
public class catalogosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCatalogos managercat;

	// CATALOGO
	private Integer cat_id;// para la seleccion de categoria
	private Integer cati_id;// para la seleccion de categoria
	private Integer cati_idhijo;// para la seleccion de categoriahijo
	private Integer cati_idpadre;// para la seleccion de categoriahijo

	private String cati_nombre;
	private String cati_estado;

	private boolean mostrarpro_id;
	private boolean edicion;
	private boolean ediciontipo;

	// fabprodfoto imagenes
	private String imagen;
	private UploadedFile file;
	private String g;
	private boolean imagencat;

	private FabCatalogo fabcat;
	private FabCatalogoitem fabcati;

	private List<FabCatalogoitem> listaCatalogoItems;

	public catalogosBean() {
	}

	@PostConstruct
	public void ini() {
		imagen = "300.jpg";
		cat_id = 0;
		cati_nombre = "";
		cati_estado = "A";
		edicion = false;
		ediciontipo = false;
		imagencat = false;
		listaCatalogoItems = managercat.findAllCatalogoItems();
	}

	public FabCatalogo getFabcat() {
		return fabcat;
	}

	public void setFabcat(FabCatalogo fabcat) {
		this.fabcat = fabcat;
	}

	public FabCatalogoitem getFabcati() {
		return fabcati;
	}

	public void setFabcati(FabCatalogoitem fabcati) {
		this.fabcati = fabcati;
	}

	public Integer getCati_idpadre() {
		return cati_idpadre;
	}

	public void setCati_idpadre(Integer cati_idpadre) {
		this.cati_idpadre = cati_idpadre;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public String getCati_nombre() {
		return cati_nombre;
	}

	public void setCati_nombre(String cati_nombre) {
		this.cati_nombre = cati_nombre;
	}

	public String getCati_estado() {
		return cati_estado;
	}

	public void setCati_estado(String cati_estado) {
		this.cati_estado = cati_estado;
	}

	public boolean isImagencat() {
		return imagencat;
	}

	public void setImagencat(boolean imagencat) {
		this.imagencat = imagencat;
	}

	public List<FabCatalogoitem> getListaCatalogoItems() {
		return listaCatalogoItems;
	}

	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
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

	public Integer getCati_id() {
		return cati_id;
	}

	public void setCati_id(Integer cati_id) {
		this.cati_id = cati_id;
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

	// CATALOGO y CATALOGO ITEMS

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoCatalogoitem() {
		edicion = false;
		cati_nombre = "";
		cati_estado = "A";
		cati_id = 0;
		imagen = "300.jpg";
		return "ncatalogo?faces-redirect=true";
	}

	/**
	 * accion para invocar el manager y crear producto o editar el producto
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
	public String crearCatalogo() {
		String r = "";
		try {
			if (edicion) {
				managercat.editarCatalogoItems(cati_id, cati_nombre.trim(),
						cati_estado, imagen, cati_idpadre);
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				r = "catalogos?faces-redirect=true";
				getListaCatalogoItems().clear();
				getListaCatalogoItems().addAll(
						managercat.findAllCatalogoItems());
			} else {
				managercat.insertarCatalogoItems(cati_nombre, imagen.trim(),
						cati_idpadre);
				Mensaje.crearMensajeINFO("Registrado - Creado");
				imagencat = false;
				getListaCatalogoItems().clear();
				getListaCatalogoItems().addAll(
						managercat.findAllCatalogoItems());
			}
			r = "catalogos?faces-redirect=true";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear catalogo item", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return r;
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
	public String cargarCatalogoItem(FabCatalogoitem cati) {
		try {
			cati_id = cati.getCatiId();
			cati_nombre = cati.getCatiNombre();
			cati_estado = cati.getCatiEstado();
			cat_id = cati.getFabCatalogo().getCatId();
			cati_idhijo = cati.getFabCatalogo().getCatId();
			cati_idpadre = cati.getCatiIdPadre();
			asignarCat();
			asignarCatItemitem();
			asignarNombreImagen();
			asignarCatItem();
			imagen = cati.getCatiImagen();
			edicion = true;
			imagencat = false;
			return "ncatalogo?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * metodo para mostrar los Catalogositems en productos
	 * 
	 */
	public List<SelectItem> getListaCategoria() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		if (cat_id != 0) {
			listadoSI.add(new SelectItem(0, "Seleccionar"));
			for (FabCatalogoitem t : managercat
					.findCatalogoItemsByCatalogo(cat_id)) {
				listadoSI.add(new SelectItem(t.getCatiId(), t.getCatiNombre()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los Catalogositems en productos
	 * 
	 */
	public List<SelectItem> getListaCatalogoitem() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		listadoSI.add(new SelectItem(0, "Seleccionar"));
		for (FabCatalogoitem t : managercat.findCatalogoItemsByCatalogo(cat_id)) {
			listadoSI.add(new SelectItem(t.getCatiId(), t.getCatiNombre()));
		}
		return listadoSI;
	}

	/**
	 * metodo para mostrar los Catalogositemsitems en productos
	 * 
	 */
	public List<SelectItem> getListaCatalogoitemitems() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		if (cat_id != 0) {
			listadoSI.add(new SelectItem(0, "Seleccionar"));
			for (FabCatalogoitem t : managercat
					.findCatalogoItemshijosByCatalogo(cati_idpadre)) {
				listadoSI.add(new SelectItem(t.getCatiId(), t.getCatiNombre()));
			}
		}
		return listadoSI;
	}

	/**
	 * metodo para asignar el catalogo al producto
	 * 
	 */
	public String asignarCat() {
		managercat.asignarcatalogo(cat_id);
		return "";
	}

	/**
	 * metodo para asignar el catalogoitem al producto
	 * 
	 */
	public String asignarCatItem() {
		managercat.asignarcati(cati_idpadre);
		return "";
	}

	/**
	 * metodo para asignar el catalogoitem al producto
	 * 
	 */
	public String asignarCatItemitem() {
		managercat.asignarcati(cati_idhijo);
		return "";
	}

	/**
	 * activar y desactivar estado producto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String cambiarEstado() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managercat
							.cambioEstadocati(getFabcati().getCatiId())));
			getListaCatalogoItems().clear();
			getListaCatalogoItems().addAll(managercat.findAllCatalogoItems());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadocati(FabCatalogoitem cat) {
		setFabcati(cat);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * metodo para conocer el prodid si esta usado
	 * 
	 */
	public boolean averiguarCatId(String nombreid) {
		Integer t = 0;
		boolean r = false;
		List<FabCatalogoitem> pro = managercat.findAllCatalogoItems();
		for (FabCatalogoitem y : pro) {
			if (y.getCatiId().equals(nombreid)) {
				System.out.println("si entra1");
				t = 1;
				r = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"El nombre del catalogo del producto existe.",
								null));
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	// ESTADOS

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
	 * Lista de tipocata
	 * 
	 * @return lista de items de tiposcata
	 */
	public List<SelectItem> getlistTipo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoProducto,
				Funciones.estadoProducto + " : "
						+ Funciones.valorEstadoProducto));
		lista.add(new SelectItem(Funciones.estadoServicio,
				Funciones.estadoServicio + " : "
						+ Funciones.valorEstadoServicio));
		return lista;
	}

	// IMAGEN

	/**
	 * metodo para guardar la imagen en el servidor
	 * 
	 */
	public void ImagenServ(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		System.out.println(cati_nombre);
		if (cati_nombre != null && cati_nombre != "") {
			asignarNombreImagencargado(cati_nombre);
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
							carpetaImagenes + File.separatorChar + getImagen()));
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
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error:", "no se pudo subir la imagen"));
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
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagen() {
		if (getCati_nombre().trim().isEmpty()) {
			System.out.println("Vacio");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
							"debe crear o guardar primero un catalogoItem"));
		} else {
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
			g = "img_" + getCati_nombre() + dateFormat.format(new Date())
					+ ".jpg";
			System.out.println(g);
		}
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagencargado(String pro_nombre) {
		if (getCati_nombre().trim().isEmpty()) {
			System.out.println("Vacio");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:",
							"debe crear o guardar primero un catalogoItem"));
		} else {
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
			g = "img_" + pro_nombre + dateFormat.format(new Date()) + ".jpg";
			System.out.println(g);
		}
	}

	// metodo para poner el nombre a la imagen
	public void nombreImagen(String n) {
		List<FabCatalogoitem> li = managercat.findAllCatalogoItems();
		for (FabCatalogoitem e : li) {
			if (e.getCatiImagen().contains(n)) {
				g = e.getCatiImagen();
			}
		}
		System.out.println(g);
	}

	/**
	 * eliminar un fotoproducto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String eliminarfoto() {
		try {
			managercat.eliminarcatalogoitem_foto(cati_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		imagen = "300.jpg";
		edicion = false;
		ediciontipo = false;
		getListaCatalogoItems().clear();
		getListaCatalogoItems().addAll(managercat.findAllCatalogoItems());
		return "catalogos?faces-redirect=true";
	}

	public void abrirDialog() {
		if (edicion == true) {
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		} else if (!averiguarCatId(cati_nombre))
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}
}
