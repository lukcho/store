package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fab_dias database table.
 * 
 */
@Entity
@Table(name="fab_dias")
@NamedQuery(name="FabDia.findAll", query="SELECT f FROM FabDia f")
public class FabDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dia_id")
	private Integer diaId;

	@Column(name="dia_estado", columnDefinition="bpchar", length=1)
	private String diaEstado;

	@Column(name="dia_nombre", length=20)
	private String diaNombre;

	//bi-directional many-to-one association to FabHorario
	@OneToMany(mappedBy="fabDia")
	private List<FabHorario> fabHorarios;

	public FabDia() {
	}

	public Integer getDiaId() {
		return this.diaId;
	}

	public void setDiaId(Integer diaId) {
		this.diaId = diaId;
	}

	public String getDiaEstado() {
		return this.diaEstado;
	}

	public void setDiaEstado(String diaEstado) {
		this.diaEstado = diaEstado;
	}

	public String getDiaNombre() {
		return this.diaNombre;
	}

	public void setDiaNombre(String diaNombre) {
		this.diaNombre = diaNombre;
	}

	public List<FabHorario> getFabHorarios() {
		return this.fabHorarios;
	}

	public void setFabHorarios(List<FabHorario> fabHorarios) {
		this.fabHorarios = fabHorarios;
	}

	public FabHorario addFabHorario(FabHorario fabHorario) {
		getFabHorarios().add(fabHorario);
		fabHorario.setFabDia(this);

		return fabHorario;
	}

	public FabHorario removeFabHorario(FabHorario fabHorario) {
		getFabHorarios().remove(fabHorario);
		fabHorario.setFabDia(null);

		return fabHorario;
	}

}