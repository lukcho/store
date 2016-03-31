package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the fab_horario_nodisponible database table.
 * 
 */
@Entity
@Table(name="fab_horario_nodisponible")
@NamedQuery(name="FabHorarioNodisponible.findAll", query="SELECT f FROM FabHorarioNodisponible f")
public class FabHorarioNodisponible implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAB_HORARIO_NODISPONIBLE_HORNID_GENERATOR", sequenceName="SEQ_FAB_HORARIO_NODISPONIBLE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAB_HORARIO_NODISPONIBLE_HORNID_GENERATOR")
	@Column(name="horn_id")
	private Integer hornId;

	@Temporal(TemporalType.DATE)
	@Column(name="hornodis_dia")
	private Date hornodisDia;

	@Column(name="hornodis_estado", columnDefinition="bpchar", length=1)
	private String hornodisEstado;

	@Column(name="hornodis_hfinal")
	private Time hornodisHfinal;

	@Column(name="hornodis_hinicial")
	private Time hornodisHinicial;

	//bi-directional many-to-one association to FabProducto
	@ManyToOne
	@JoinColumn(name="pro_id")
	private FabProducto fabProducto;

	public FabHorarioNodisponible() {
	}

	public Integer getHornId() {
		return this.hornId;
	}

	public void setHornId(Integer hornId) {
		this.hornId = hornId;
	}

	public Date getHornodisDia() {
		return this.hornodisDia;
	}

	public void setHornodisDia(Date hornodisDia) {
		this.hornodisDia = hornodisDia;
	}

	public String getHornodisEstado() {
		return this.hornodisEstado;
	}

	public void setHornodisEstado(String hornodisEstado) {
		this.hornodisEstado = hornodisEstado;
	}

	public Time getHornodisHfinal() {
		return this.hornodisHfinal;
	}

	public void setHornodisHfinal(Time hornodisHfinal) {
		this.hornodisHfinal = hornodisHfinal;
	}

	public Time getHornodisHinicial() {
		return this.hornodisHinicial;
	}

	public void setHornodisHinicial(Time hornodisHinicial) {
		this.hornodisHinicial = hornodisHinicial;
	}

	public FabProducto getFabProducto() {
		return this.fabProducto;
	}

	public void setFabProducto(FabProducto fabProducto) {
		this.fabProducto = fabProducto;
	}

}