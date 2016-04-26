package store.model.dao.entities;

import java.io.Serializable;



public class ProductoFoto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer profId;
	private String profDireccion;
	private String profEstado;
	private String profNombre;
	private FabProducto fabProducto;

	public ProductoFoto() {}
	
	public ProductoFoto(Integer profId){
		this.profId = profId;
	}
	
	public ProductoFoto(Integer profId, String profDireccion, String profEstado,
			String profNombre, FabProducto fabProducto) {
		this.profId = profId;
		this.profDireccion = profDireccion;
		this.profEstado = profEstado;
		this.profNombre = profNombre;
		this.fabProducto = fabProducto;
	}

	public Integer getProfId() {
		return this.profId;
	}

	public void setProfId(Integer profId) {
		this.profId = profId;
	}

	public String getProfDireccion() {
		return this.profDireccion;
	}

	public void setProfDireccion(String profDireccion) {
		this.profDireccion = profDireccion;
	}

	public String getProfEstado() {
		return this.profEstado;
	}

	public void setProfEstado(String profEstado) {
		this.profEstado = profEstado;
	}

	public String getProfNombre() {
		return this.profNombre;
	}

	public void setProfNombre(String profNombre) {
		this.profNombre = profNombre;
	}

	public FabProducto getFabProducto() {
		return this.fabProducto;
	}

	public void setFabProducto(FabProducto fabProducto) {
		this.fabProducto = fabProducto;
	}

}