package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fab_producto_fotos database table.
 * 
 */
@Entity
@Table(name="fab_producto_fotos")
@NamedQuery(name="FabProductoFoto.findAll", query="SELECT f FROM FabProductoFoto f")
public class FabProductoFoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_PRODUCTO_FOTOS_PROFID_GENERATOR", sequenceName="SEQ_FAB_PRODUCTO_FOTOS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_PRODUCTO_FOTOS_PROFID_GENERATOR")
	@Column(name="prof_id")
	private Integer profId;

	@Column(name="prof_direccion", length=200)
	private String profDireccion;

	@Column(name="prof_estado", columnDefinition="bpchar", length=1)
	private String profEstado;

	@Column(name="prof_mostrar")
	private Boolean profMostrar;

	@Column(name="prof_nombre", length=200)
	private String profNombre;

	//bi-directional many-to-one association to FabProducto
	@ManyToOne
	@JoinColumn(name="pro_id")
	private FabProducto fabProducto;

	public FabProductoFoto() {
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

	public Boolean getProfMostrar() {
		return this.profMostrar;
	}

	public void setProfMostrar(Boolean profMostrar) {
		this.profMostrar = profMostrar;
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