package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the fab_producto database table.
 * 
 */
@Entity
@Table(name="fab_producto")
@NamedQuery(name="FabProducto.findAll", query="SELECT f FROM FabProducto f")
public class FabProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pro_id", length=20)
	private String proId;

	@Column(name="pro_codigo_barras", length=100)
	private String proCodigoBarras;

	@Column(name="pro_costo")
	private BigDecimal proCosto;

	@Column(name="pro_descripcion", length=255)
	private String proDescripcion;

	@Column(name="pro_estado", columnDefinition="bpchar", length=1)
	private String proEstado;

	@Column(name="pro_estado_funcional",columnDefinition="bpchar", length=1)
	private String proEstadoFuncional;

	@Column(name="pro_nombre", length=200)
	private String proNombre;

	@Column(name="pro_precio")
	private BigDecimal proPrecio;

	@Column(name="pro_stock")
	private Integer proStock;

	@Column(name="pro_tipo",columnDefinition="bpchar", length=1)
	private String proTipo;

	//bi-directional many-to-one association to FabHorario
	@OneToMany(mappedBy="fabProducto")
	private List<FabHorario> fabHorarios;

	//bi-directional many-to-one association to FabHorarioNodisponible
	@OneToMany(mappedBy="fabProducto")
	private List<FabHorarioNodisponible> fabHorarioNodisponibles;

	//bi-directional many-to-one association to FabPedidoDet
	@OneToMany(mappedBy="fabProducto")
	private List<FabPedidoDet> fabPedidoDets;

	//bi-directional many-to-one association to FabCatalogoitem
	@ManyToOne
	@JoinColumn(name="cati_id")
	private FabCatalogoitem fabCatalogoitem;

	//bi-directional many-to-one association to FabProductoFoto
	@OneToMany(mappedBy="fabProducto")
	private List<FabProductoFoto> fabProductoFotos;

	public FabProducto() {
	}

	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProCodigoBarras() {
		return this.proCodigoBarras;
	}

	public void setProCodigoBarras(String proCodigoBarras) {
		this.proCodigoBarras = proCodigoBarras;
	}

	public BigDecimal getProCosto() {
		return this.proCosto;
	}

	public void setProCosto(BigDecimal proCosto) {
		this.proCosto = proCosto;
	}

	public String getProDescripcion() {
		return this.proDescripcion;
	}

	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}

	public String getProEstado() {
		return this.proEstado;
	}

	public void setProEstado(String proEstado) {
		this.proEstado = proEstado;
	}

	public String getProEstadoFuncional() {
		return this.proEstadoFuncional;
	}

	public void setProEstadoFuncional(String proEstadoFuncional) {
		this.proEstadoFuncional = proEstadoFuncional;
	}

	public String getProNombre() {
		return this.proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public BigDecimal getProPrecio() {
		return this.proPrecio;
	}

	public void setProPrecio(BigDecimal proPrecio) {
		this.proPrecio = proPrecio;
	}

	public Integer getProStock() {
		return this.proStock;
	}

	public void setProStock(Integer proStock) {
		this.proStock = proStock;
	}

	public String getProTipo() {
		return this.proTipo;
	}

	public void setProTipo(String proTipo) {
		this.proTipo = proTipo;
	}

	public List<FabHorario> getFabHorarios() {
		return this.fabHorarios;
	}

	public void setFabHorarios(List<FabHorario> fabHorarios) {
		this.fabHorarios = fabHorarios;
	}

	public FabHorario addFabHorario(FabHorario fabHorario) {
		getFabHorarios().add(fabHorario);
		fabHorario.setFabProducto(this);

		return fabHorario;
	}

	public FabHorario removeFabHorario(FabHorario fabHorario) {
		getFabHorarios().remove(fabHorario);
		fabHorario.setFabProducto(null);

		return fabHorario;
	}

	public List<FabHorarioNodisponible> getFabHorarioNodisponibles() {
		return this.fabHorarioNodisponibles;
	}

	public void setFabHorarioNodisponibles(List<FabHorarioNodisponible> fabHorarioNodisponibles) {
		this.fabHorarioNodisponibles = fabHorarioNodisponibles;
	}

	public FabHorarioNodisponible addFabHorarioNodisponible(FabHorarioNodisponible fabHorarioNodisponible) {
		getFabHorarioNodisponibles().add(fabHorarioNodisponible);
		fabHorarioNodisponible.setFabProducto(this);

		return fabHorarioNodisponible;
	}

	public FabHorarioNodisponible removeFabHorarioNodisponible(FabHorarioNodisponible fabHorarioNodisponible) {
		getFabHorarioNodisponibles().remove(fabHorarioNodisponible);
		fabHorarioNodisponible.setFabProducto(null);

		return fabHorarioNodisponible;
	}

	public List<FabPedidoDet> getFabPedidoDets() {
		return this.fabPedidoDets;
	}

	public void setFabPedidoDets(List<FabPedidoDet> fabPedidoDets) {
		this.fabPedidoDets = fabPedidoDets;
	}

	public FabPedidoDet addFabPedidoDet(FabPedidoDet fabPedidoDet) {
		getFabPedidoDets().add(fabPedidoDet);
		fabPedidoDet.setFabProducto(this);

		return fabPedidoDet;
	}

	public FabPedidoDet removeFabPedidoDet(FabPedidoDet fabPedidoDet) {
		getFabPedidoDets().remove(fabPedidoDet);
		fabPedidoDet.setFabProducto(null);

		return fabPedidoDet;
	}

	public FabCatalogoitem getFabCatalogoitem() {
		return this.fabCatalogoitem;
	}

	public void setFabCatalogoitem(FabCatalogoitem fabCatalogoitem) {
		this.fabCatalogoitem = fabCatalogoitem;
	}

	public List<FabProductoFoto> getFabProductoFotos() {
		return this.fabProductoFotos;
	}

	public void setFabProductoFotos(List<FabProductoFoto> fabProductoFotos) {
		this.fabProductoFotos = fabProductoFotos;
	}

	public FabProductoFoto addFabProductoFoto(FabProductoFoto fabProductoFoto) {
		getFabProductoFotos().add(fabProductoFoto);
		fabProductoFoto.setFabProducto(this);

		return fabProductoFoto;
	}

	public FabProductoFoto removeFabProductoFoto(FabProductoFoto fabProductoFoto) {
		getFabProductoFotos().remove(fabProductoFoto);
		fabProductoFoto.setFabProducto(null);

		return fabProductoFoto;
	}

}