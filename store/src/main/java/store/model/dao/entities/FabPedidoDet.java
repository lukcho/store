package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fab_pedido_det database table.
 * 
 */
@Entity
@Table(name="fab_pedido_det")
@NamedQuery(name="FabPedidoDet.findAll", query="SELECT f FROM FabPedidoDet f")
public class FabPedidoDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_PEDIDO_DET_PEDDID_GENERATOR", sequenceName="SEQ_FAB_PEDIDO_DET", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_PEDIDO_DET_PEDDID_GENERATOR")
	@Column(name="pedd_id")
	private Integer peddId;

	@Column(name="pedd_cantidad")
	private Integer peddCantidad;

	@Column(name="pedd_costo")
	private BigDecimal peddCosto;

	@Column(name="pedd_descuento")
	private BigDecimal peddDescuento;

	@Column(name="pedd_impuesto")
	private BigDecimal peddImpuesto;

	@Column(name="pedd_precio")
	private BigDecimal peddPrecio;

	//bi-directional many-to-one association to FabPedidoCab
	@ManyToOne
	@JoinColumn(name="pedc_id")
	private FabPedidoCab fabPedidoCab;

	//bi-directional many-to-one association to FabProducto
	@ManyToOne
	@JoinColumn(name="pro_id")
	private FabProducto fabProducto;

	public FabPedidoDet() {
	}

	public Integer getPeddId() {
		return this.peddId;
	}

	public void setPeddId(Integer peddId) {
		this.peddId = peddId;
	}

	public Integer getPeddCantidad() {
		return this.peddCantidad;
	}

	public void setPeddCantidad(Integer peddCantidad) {
		this.peddCantidad = peddCantidad;
	}

	public BigDecimal getPeddCosto() {
		return this.peddCosto;
	}

	public void setPeddCosto(BigDecimal peddCosto) {
		this.peddCosto = peddCosto;
	}

	public BigDecimal getPeddDescuento() {
		return this.peddDescuento;
	}

	public void setPeddDescuento(BigDecimal peddDescuento) {
		this.peddDescuento = peddDescuento;
	}

	public BigDecimal getPeddImpuesto() {
		return this.peddImpuesto;
	}

	public void setPeddImpuesto(BigDecimal peddImpuesto) {
		this.peddImpuesto = peddImpuesto;
	}

	public BigDecimal getPeddPrecio() {
		return this.peddPrecio;
	}

	public void setPeddPrecio(BigDecimal peddPrecio) {
		this.peddPrecio = peddPrecio;
	}

	public FabPedidoCab getFabPedidoCab() {
		return this.fabPedidoCab;
	}

	public void setFabPedidoCab(FabPedidoCab fabPedidoCab) {
		this.fabPedidoCab = fabPedidoCab;
	}

	public FabProducto getFabProducto() {
		return this.fabProducto;
	}

	public void setFabProducto(FabProducto fabProducto) {
		this.fabProducto = fabProducto;
	}

}