package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fab_catalogoitems database table.
 * 
 */
@Entity
@Table(name="fab_catalogoitems")
@NamedQuery(name="FabCatalogoitem.findAll", query="SELECT f FROM FabCatalogoitem f")
public class FabCatalogoitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_CATALOGOITEMS_CATIID_GENERATOR", sequenceName="SEQ_FAB_CATALOGOITEMS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_CATALOGOITEMS_CATIID_GENERATOR")
	@Column(name="cati_id")
	private Integer catiId;

	@Column(name="cati_estado", columnDefinition="bpchar", length=1)
	private String catiEstado;

	@Column(name="cati_id_padre")
	private Integer catiIdPadre;

	@Column(name="cati_imagen", length=200)
	private String catiImagen;

	@Column(name="cati_nombre", length=100)
	private String catiNombre;

	//bi-directional many-to-one association to FabCatalogo
	@ManyToOne
	@JoinColumn(name="cat_id")
	private FabCatalogo fabCatalogo;

	//bi-directional many-to-one association to FabProducto
	@OneToMany(mappedBy="fabCatalogoitem")
	private List<FabProducto> fabProductos;

	public FabCatalogoitem() {
	}

	public Integer getCatiId() {
		return this.catiId;
	}

	public void setCatiId(Integer catiId) {
		this.catiId = catiId;
	}

	public String getCatiEstado() {
		return this.catiEstado;
	}

	public void setCatiEstado(String catiEstado) {
		this.catiEstado = catiEstado;
	}

	public Integer getCatiIdPadre() {
		return this.catiIdPadre;
	}

	public void setCatiIdPadre(Integer catiIdPadre) {
		this.catiIdPadre = catiIdPadre;
	}

	public String getCatiImagen() {
		return this.catiImagen;
	}

	public void setCatiImagen(String catiImagen) {
		this.catiImagen = catiImagen;
	}

	public String getCatiNombre() {
		return this.catiNombre;
	}

	public void setCatiNombre(String catiNombre) {
		this.catiNombre = catiNombre;
	}

	public FabCatalogo getFabCatalogo() {
		return this.fabCatalogo;
	}

	public void setFabCatalogo(FabCatalogo fabCatalogo) {
		this.fabCatalogo = fabCatalogo;
	}

	public List<FabProducto> getFabProductos() {
		return this.fabProductos;
	}

	public void setFabProductos(List<FabProducto> fabProductos) {
		this.fabProductos = fabProductos;
	}

	public FabProducto addFabProducto(FabProducto fabProducto) {
		getFabProductos().add(fabProducto);
		fabProducto.setFabCatalogoitem(this);

		return fabProducto;
	}

	public FabProducto removeFabProducto(FabProducto fabProducto) {
		getFabProductos().remove(fabProducto);
		fabProducto.setFabCatalogoitem(null);

		return fabProducto;
	}

}