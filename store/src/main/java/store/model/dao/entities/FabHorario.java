package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the fab_horario database table.
 * 
 */
@Entity
@Table(name="fab_horario")
@NamedQuery(name="FabHorario.findAll", query="SELECT f FROM FabHorario f")
public class FabHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_HORARIO_HORID_GENERATOR", sequenceName="SEQ_FAB_HORARIO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_HORARIO_HORID_GENERATOR")
	@Column(name="hor_id")
	private Integer horId;

	@Column(name="hor_dia", length=50)
	private String horDia;

	@Column(name="hor_estado", columnDefinition="bpchar", length=1)
	private String horEstado;

	@Column(name="hor_hora_final")
	private Time horHoraFinal;

	@Column(name="hor_hora_inicio")
	private Time horHoraInicio;

	//bi-directional many-to-one association to FabProducto
	@ManyToOne
	@JoinColumn(name="pro_id")
	private FabProducto fabProducto;

	public FabHorario() {
	}

	public Integer getHorId() {
		return this.horId;
	}

	public void setHorId(Integer horId) {
		this.horId = horId;
	}

	public String getHorDia() {
		return this.horDia;
	}

	public void setHorDia(String horDia) {
		this.horDia = horDia;
	}

	public String getHorEstado() {
		return this.horEstado;
	}

	public void setHorEstado(String horEstado) {
		this.horEstado = horEstado;
	}

	public Time getHorHoraFinal() {
		return this.horHoraFinal;
	}

	public void setHorHoraFinal(Time horHoraFinal) {
		this.horHoraFinal = horHoraFinal;
	}

	public Time getHorHoraInicio() {
		return this.horHoraInicio;
	}

	public void setHorHoraInicio(Time horHoraInicio) {
		this.horHoraInicio = horHoraInicio;
	}

	public FabProducto getFabProducto() {
		return this.fabProducto;
	}

	public void setFabProducto(FabProducto fabProducto) {
		this.fabProducto = fabProducto;
	}

}