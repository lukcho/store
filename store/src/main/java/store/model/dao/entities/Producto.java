package store.model.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String proId;
	private String proCodigoBarras;
	private BigDecimal proCosto;
	private String proDescripcion;
	private String proEstado;
	private String proEstadoFuncional;
	private String proNombre;
	private BigDecimal proPrecio;
	private Integer proStock;
	private String proTipo;
	private List<FabHorario> fabHorarios;
	private List<FabHorarioNodisponible> fabHorarioNodisponibles;
	private List<FabPedidoDet> fabPedidoDets;
	private Integer fabCatalogoitem;
	private List<FabProductoFoto> fabProductoFotos;

	public Producto() {
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

	public List<FabHorarioNodisponible> getFabHorarioNodisponibles() {
		return this.fabHorarioNodisponibles;
	}

	public void setFabHorarioNodisponibles(
			List<FabHorarioNodisponible> fabHorarioNodisponibles) {
		this.fabHorarioNodisponibles = fabHorarioNodisponibles;
	}

	public List<FabPedidoDet> getFabPedidoDets() {
		return this.fabPedidoDets;
	}

	public void setFabPedidoDets(List<FabPedidoDet> fabPedidoDets) {
		this.fabPedidoDets = fabPedidoDets;
	}

	public Integer getFabCatalogoitem() {
		return fabCatalogoitem;
	}

	public void setFabCatalogoitem(Integer fabCatalogoitem) {
		this.fabCatalogoitem = fabCatalogoitem;
	}

	public List<FabProductoFoto> getFabProductoFotos() {
		return this.fabProductoFotos;
	}

	public void setFabProductoFotos(List<FabProductoFoto> fabProductoFotos) {
		this.fabProductoFotos = fabProductoFotos;
	}
}