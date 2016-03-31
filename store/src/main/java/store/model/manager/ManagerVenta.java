package store.model.manager;

import store.model.dao.entities.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerVenta{

	@EJB
	private ManagerDAO mDAO;

	private static FabUsuario fab_usu;
	private static FabPedidoCab fab_pedc;
	String h="";		
				
	public ManagerVenta() {
	}
	
	// PEDIDOCABECERA
	
	/**
	 * buscar todos los FabPedidoCab
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabPedidoCab> findPedidoCab() {
		return mDAO.findWhere(FabPedidoCab.class, "1=1", null);
	}

	/**
	 * listar todos los FabPedidoCab
	 * @param pedcab_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabPedidoCab> findAllPedidoCab() {
		return mDAO.findAll(FabPedidoCab.class);
	}

	/**
	 * buscar FabPedidoCab por ID
	 * @param pedcab_id
	 * @throws Exception
	 */
	public FabPedidoCab Ped_CabByID(Integer pedcab_id) throws Exception {
		return (FabPedidoCab) mDAO.findById(FabPedidoCab.class, pedcab_id);
	}
	
	/**
	 * buscar Usuario por ID
	 * @param pedcab_id
	 * @throws Exception
	 */
	public FabUsuario UsarioByID(String usu_id) throws Exception {
		return (FabUsuario) mDAO.findById(FabUsuario.class, usu_id);
	}
	
	/**
	 * Agrega Pedidocabecera
	 * @param fecha
	 * @param fecha_aprobacion
	 * @param archivo_pago
	 * @param observacion
	 * @param estado
	 * @param usuario
	 * @throws Exception
	 */
	public void insertarPedidoCab(Date fecha, Timestamp fecha_aprobacion, String archivo_pago, String observacion) throws Exception {
		FabPedidoCab pedcab = new FabPedidoCab();
		pedcab.setPedcFecha(fecha);
		pedcab.setFabUsuario(fab_usu);
		pedcab.setPedcFechaAprobacion(fecha_aprobacion);
		pedcab.setPedcArchivoPago(archivo_pago);
		pedcab.setPedcObservacion(observacion);
		pedcab.setPedcEstado("P");
		mDAO.insertar(pedcab);
	}

	/**
	 * Cambiar datos de pedcabalogo
	 * @param fecha
	 * @param fecha_aprobacion
	 * @param archivo_pago
	 * @param observacion
	 * @param estado
	 * @param usuario
	 * @throws Exception
	 */	
	public void editarPedidoCab(Integer pedc_id,Date fecha, Timestamp fecha_aprobacion, String archivo_pago, String observacion, String estado) throws Exception {
		FabPedidoCab pedcab = this.Ped_CabByID(pedc_id);
		pedcab.setPedcFecha(fecha);
		pedcab.setFabUsuario(fab_usu);
		pedcab.setPedcFechaAprobacion(fecha_aprobacion);
		pedcab.setPedcArchivoPago(archivo_pago);
		pedcab.setPedcObservacion(observacion);
		pedcab.setPedcEstado(estado);
		mDAO.actualizar(pedcab);
	}
	
	/**
	 * Cambiar estado pedcab
	 * @param id_pedcab
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadopedcab(Integer pedcab_id) throws Exception{
		String h="";
		FabPedidoCab pedcab = Ped_CabByID(pedcab_id);						
		
		if(pedcab.getPedcEstado().equals("A")){
			pedcab.setPedcEstado("D");
			h="Estado del pedcabalogo Modificado";
			}
		else if(pedcab.getPedcEstado().equals("D")){
			pedcab.setPedcEstado("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(pedcab);
		return h;
		}		
	
	/**
	 * Verifica si el pedcabucto esta activado
	 * @param u pedcabucto a analizar
	 * @return true o false
	 */
	public boolean espedcabActivo(FabPedidoCab u){
		boolean  resp = false;
		if(u.getPedcEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	/**
	 * metodo para asignar el usuario al pedcab
	 * 
	 * @param u
	 * pedcab a analizar
	 * @return true o false
	 */
	public FabUsuario asignarusucab(String usu_id) {
		try {
			fab_usu = UsarioByID(usu_id);
		} catch (Exception e) {
			// TODO Auto-generated pedcabch block
			e.printStackTrace();
		}
		return fab_usu;
	}
	
	
	// PEDIDOCABECERADETALLE

	/**
	 * buscar todos los FabPedidoDet
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabPedidoDet> findPedidoDet() {
		return mDAO.findWhere(FabPedidoDet.class, " ", null);
	}

	/**
	 * listar todos los FabPedidoDet
	 * @param pedcab_id
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabPedidoDet> findAllPedidoDet() {
		return mDAO.findAll(FabPedidoDet.class);
	}

	/**
	 * buscar FabPedidoDet por ID
	 * @param pedcab_id
	 * @throws Exception
	 */
	public FabPedidoDet Ped_DetByID(Integer peddet_id) throws Exception {
		return (FabPedidoDet) mDAO.findById(FabPedidoDet.class, peddet_id);
	}
		
	/**
	 * Agrega Pedidodetalle
	 * @param cantidad
	 * @param costo
	 * @param precio
	 * @param precio
	 * @param impuesto
	 * @param descuento
	 * @throws Exception
	 */
	public void insertarPedidoDet(Integer cantidad, BigDecimal costo,BigDecimal precio, BigDecimal impuesto,BigDecimal descuento) throws Exception {
		FabPedidoDet peddet = new FabPedidoDet();
		peddet.setFabPedidoCab(fab_pedc);
		peddet.setPeddCantidad(cantidad);
		peddet.setPeddCosto(costo);
		peddet.setPeddPrecio(precio);
		peddet.setPeddImpuesto(impuesto);
		peddet.setPeddDescuento(descuento);
		mDAO.insertar(peddet);
		//agregarlista de productos
	}

	/**
	 * Cambiar datos de peddet
	 * @param cantidad
	 * @param costo
	 * @param precio
	 * @param precio
	 * @param impuesto
	 * @param descuento
	 * @throws Exception
	 */	
	public void editarPedidoDet(Integer pedd_id,Integer cantidad, BigDecimal costo,boolean precio, BigDecimal impuesto,BigDecimal descuento) throws Exception {
		FabPedidoDet peddet = this.Ped_DetByID(pedd_id);
		peddet.setFabPedidoCab(fab_pedc);
		peddet.setPeddCantidad(cantidad);
		peddet.setPeddCosto(costo);
		peddet.setPeddImpuesto(impuesto);
		peddet.setPeddDescuento(descuento);
		mDAO.actualizar(peddet);
		//agregar lista de productos
	}
	
	/**
	 * metodo para asignar el pedcab al peddet
	 * 
	 * @param u
	 * pedcab a analizar
	 * @return true o false
	 */
	public FabPedidoCab asignarcab_det(Integer pedc_id) {
		try {
			fab_pedc = Ped_CabByID(pedc_id);
		} catch (Exception e) {
			// TODO Auto-generated pedcabch block
			e.printStackTrace();
		}
		return fab_pedc;
	}
}
