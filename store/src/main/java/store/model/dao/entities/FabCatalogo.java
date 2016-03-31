package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fab_catalogo database table.
 * 
 */
@Entity
@Table(name="fab_catalogo")
@NamedQuery(name="FabCatalogo.findAll", query="SELECT f FROM FabCatalogo f")
public class FabCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_CATALOGO_CATID_GENERATOR", sequenceName="SEQ_FAB_CATALOGO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_CATALOGO_CATID_GENERATOR")
	@Column(name="cat_id")
	private Integer catId;

	@Column(name="cat_nombre", length=200)
	private String catNombre;

	@Column(name="cat_valor", columnDefinition="bpchar", length=1)
	private String catValor;

	//bi-directional many-to-one association to FabCatalogoitem
	@OneToMany(mappedBy="fabCatalogo")
	private List<FabCatalogoitem> fabCatalogoitems;

	public FabCatalogo() {
	}

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatNombre() {
		return this.catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public String getCatValor() {
		return this.catValor;
	}

	public void setCatValor(String catValor) {
		this.catValor = catValor;
	}

	public List<FabCatalogoitem> getFabCatalogoitems() {
		return this.fabCatalogoitems;
	}

	public void setFabCatalogoitems(List<FabCatalogoitem> fabCatalogoitems) {
		this.fabCatalogoitems = fabCatalogoitems;
	}

	public FabCatalogoitem addFabCatalogoitem(FabCatalogoitem fabCatalogoitem) {
		getFabCatalogoitems().add(fabCatalogoitem);
		fabCatalogoitem.setFabCatalogo(this);

		return fabCatalogoitem;
	}

	public FabCatalogoitem removeFabCatalogoitem(FabCatalogoitem fabCatalogoitem) {
		getFabCatalogoitems().remove(fabCatalogoitem);
		fabCatalogoitem.setFabCatalogo(null);

		return fabCatalogoitem;
	}

}