package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fab_pedido_cab database table.
 * 
 */
@Entity
@Table(name="fab_pedido_cab")
@NamedQuery(name="FabPedidoCab.findAll", query="SELECT f FROM FabPedidoCab f")
public class FabPedidoCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_PEDIDO_CAB_PEDCID_GENERATOR", sequenceName="SEQ_FAB_PEDIDO_CAB", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_PEDIDO_CAB_PEDCID_GENERATOR")
	@Column(name="pedc_id")
	private Integer pedcId;

	@Column(name="pedc_archivo_pago", length=255)
	private String pedcArchivoPago;

	@Column(name="pedc_estado", columnDefinition="bpchar", length=1)
	private String pedcEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="pedc_fecha")
	private Date pedcFecha;

	@Column(name="pedc_fecha_aprobacion")
	private Timestamp pedcFechaAprobacion;

	@Column(name="pedc_observacion", length=255)
	private String pedcObservacion;

	//bi-directional many-to-one association to FabUsuario
	@ManyToOne
	@JoinColumn(name="usr_id")
	private FabUsuario fabUsuario;

	//bi-directional many-to-one association to FabPedidoDet
	@OneToMany(mappedBy="fabPedidoCab")
	private List<FabPedidoDet> fabPedidoDets;

	public FabPedidoCab() {
	}

	public Integer getPedcId() {
		return this.pedcId;
	}

	public void setPedcId(Integer pedcId) {
		this.pedcId = pedcId;
	}

	public String getPedcArchivoPago() {
		return this.pedcArchivoPago;
	}

	public void setPedcArchivoPago(String pedcArchivoPago) {
		this.pedcArchivoPago = pedcArchivoPago;
	}

	public String getPedcEstado() {
		return this.pedcEstado;
	}

	public void setPedcEstado(String pedcEstado) {
		this.pedcEstado = pedcEstado;
	}

	public Date getPedcFecha() {
		return this.pedcFecha;
	}

	public void setPedcFecha(Date pedcFecha) {
		this.pedcFecha = pedcFecha;
	}

	public Timestamp getPedcFechaAprobacion() {
		return this.pedcFechaAprobacion;
	}

	public void setPedcFechaAprobacion(Timestamp pedcFechaAprobacion) {
		this.pedcFechaAprobacion = pedcFechaAprobacion;
	}

	public String getPedcObservacion() {
		return this.pedcObservacion;
	}

	public void setPedcObservacion(String pedcObservacion) {
		this.pedcObservacion = pedcObservacion;
	}

	public FabUsuario getFabUsuario() {
		return this.fabUsuario;
	}

	public void setFabUsuario(FabUsuario fabUsuario) {
		this.fabUsuario = fabUsuario;
	}

	public List<FabPedidoDet> getFabPedidoDets() {
		return this.fabPedidoDets;
	}

	public void setFabPedidoDets(List<FabPedidoDet> fabPedidoDets) {
		this.fabPedidoDets = fabPedidoDets;
	}

	public FabPedidoDet addFabPedidoDet(FabPedidoDet fabPedidoDet) {
		getFabPedidoDets().add(fabPedidoDet);
		fabPedidoDet.setFabPedidoCab(this);

		return fabPedidoDet;
	}

	public FabPedidoDet removeFabPedidoDet(FabPedidoDet fabPedidoDet) {
		getFabPedidoDets().remove(fabPedidoDet);
		fabPedidoDet.setFabPedidoCab(null);

		return fabPedidoDet;
	}

}