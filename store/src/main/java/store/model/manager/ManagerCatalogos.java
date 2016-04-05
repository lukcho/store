package store.model.manager;

import store.model.dao.entities.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerCatalogos{

	@EJB
	private ManagerDAO mDAO;

	private static FabCatalogo fab_cat;
	public String tipo;
	String h="";		
				
	public ManagerCatalogos() {
	}
	
	// CATALOGO
	
	/**
	 * buscar todos catalogos
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabCatalogo> findCatalogo() {
		return mDAO.findWhere(FabCatalogo.class, "1=1", null);
	}

	/**
	 * listar todos los catalogos
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabCatalogo> findAllCatalogos() {
		return mDAO.findAll(FabCatalogo.class);
	}

	/**
	 * buscar Catalogos por ID
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public FabCatalogo CatalogoByID(Integer cat_id) throws Exception {
		return (FabCatalogo) mDAO.findById(FabCatalogo.class, cat_id);
	}
	
	/**
	 * Agrega Catalogo
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarCatalogos(Integer cat_id, String nombre, String valor) throws Exception {
		FabCatalogo cat = new FabCatalogo();
		cat.setCatNombre(nombre);
		cat.setCatValor(valor);
		mDAO.insertar(cat);
	}

	/**
	 * Cambiar datos de Catalogo
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	public void editarCatalogo(Integer cat_id, String nombre, String valor) throws Exception {
		FabCatalogo cat = this.CatalogoByID(cat_id);
		cat.setCatNombre(nombre);
		cat.setCatValor(valor);
		mDAO.actualizar(cat);
	}
	
	/**
	 * Cambiar estado Catalogo
	 * @param id_cat
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadocat(Integer cat_id) throws Exception{
		String h="";
		FabCatalogo cat = CatalogoByID(cat_id);						
		
		if(cat.getCatValor().equals("A")){
			cat.setCatValor("D");
			h="Estado del Catalogo Modificado";
			}
		else if(cat.getCatValor().equals("D")){
			cat.setCatValor("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(cat);
		return h;
		}		
	
		/**
	 * Verifica si el Catalogo esta activado
	 * @param u Catalogo a analizar
	 * @return true o false
	 */
	public boolean escatActivo(FabCatalogo u){
		boolean  resp = false;
		if(u.getCatValor().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	
	
	// CATALOGOITEMS
	/**
	 * buscar todos catalogosItems
	 * @param catId
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabCatalogoitem> findCatalogoItemsByCatalogo(Integer catId) {
		return mDAO.findWhere(FabCatalogoitem.class, "o.fabCatalogo.catId= "+catId+ " and cati_id_padre= 0", null);
	}
	
	
	/**
	 * buscar todos catalogosItemsitems
	 * @param catId
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabCatalogoitem> findCatalogoItemshijosByCatalogo(Integer cati_Id) {
		return mDAO.findWhere(FabCatalogoitem.class, "o.catiIdPadre = "+cati_Id , null);
	}
	

	/**
	 * listar todos los catalogositems
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabCatalogoitem> findAllCatalogoItems() {
		return mDAO.findAll(FabCatalogoitem.class);
	}

	/**
	 * buscar Catalogositems por ID
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public FabCatalogoitem CatalogoItemsByID(Integer cati_id) throws Exception {
		return (FabCatalogoitem) mDAO.findById(FabCatalogoitem.class, cati_id);
	}
	
	/**
	 * Agrega Catalogoitems
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarCatalogoItems(Integer cati_id, String nombre,String imagen, Integer id_padre) throws Exception {
		FabCatalogoitem cati = new FabCatalogoitem();
		if(id_padre==null){
		cati.setCatiNombre(nombre);
		cati.setCatiImagen(imagen);
		cati.setFabCatalogo(fab_cat);
		cati.setCatiEstado("A");
		mDAO.insertar(cati);
		}		
		else
		cati.setCatiNombre(nombre);
		cati.setCatiImagen(imagen);
		cati.setCatiIdPadre(id_padre);
		cati.setFabCatalogo(fab_cat);
		cati.setCatiEstado("A");
		mDAO.insertar(cati);		
	}

	/**
	 * Cambiar datos de CatalogoItems
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */	
	public void editarCatalogoItems(Integer cati_id, String nombre, String valor, String imagen, Integer id_padre) throws Exception {
		FabCatalogoitem cati = this.CatalogoItemsByID(cati_id);
		cati.setCatiNombre(nombre);
		cati.setCatiImagen(imagen);
		cati.setCatiIdPadre(id_padre);
		cati.setFabCatalogo(fab_cat);
		cati.setCatiEstado("A");
		mDAO.actualizar(cati);
	}
	
	/**
	 * Cambiar estado CatalogoItems
	 * @param id_cat
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadocati(Integer cati_id) throws Exception{
		String h="";
		FabCatalogoitem cati = CatalogoItemsByID(cati_id);						
		
		if(cati.getCatiEstado().equals("A")){
			cati.setCatiEstado("D");
			h="Estado del Catalogo Modificado";
			}
		else if(cati.getCatiEstado().equals("D")){
			cati.setCatiEstado("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(cati);
		return h;
		}		
	
	/**
	 * Verifica si el CatalogoItems esta activado
	 * @param u Catalogo a analizar
	 * @return true o false
	 */
	public boolean escatiActivo(FabCatalogoitem u){
		boolean  resp = false;
		if(u.getCatiEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	/**
	 * metodo para asignar el catalogo al un item
	 * @param u
	 * Catalogo a analizar
	 * @return true o false
	 */
	public FabCatalogo asignarcatalogo(Integer cat_id) {
		try {
			fab_cat = CatalogoByID(cat_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fab_cat;
	}
}
