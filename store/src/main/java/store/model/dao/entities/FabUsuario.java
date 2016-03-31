package store.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fab_usuario database table.
 * 
 */
@Entity
@Table(name="fab_usuario")
@NamedQuery(name="FabUsuario.findAll", query="SELECT f FROM FabUsuario f")
public class FabUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usr_id", length=20)
	private String usrId;

	@Column(name="usr_apellido", length=100)
	private String usrApellido;

	@Column(name="usr_correo", length=200)
	private String usrCorreo;

	@Column(name="usr_direccion", length=255)
	private String usrDireccion;

	@Column(name="usr_estado", columnDefinition="bpchar", length=1)
	private String usrEstado;

	@Column(name="usr_estado_funcional", columnDefinition="bpchar", length=1)
	private String usrEstadoFuncional;

	@Temporal(TemporalType.DATE)
	@Column(name="usr_fecha_nacimiento")
	private Date usrFechaNacimiento;

	@Column(name="usr_nombre", length=100)
	private String usrNombre;

	@Column(name="usr_password", length=200)
	private String usrPassword;

	@Column(name="usr_telefono", length=20)
	private String usrTelefono;

	@Column(name="usr_tipo_usuario", columnDefinition="bpchar", length=1)
	private String usrTipoUsuario;

	//bi-directional many-to-one association to FabPedidoCab
	@OneToMany(mappedBy="fabUsuario")
	private List<FabPedidoCab> fabPedidoCabs;

	public FabUsuario() {
	}

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrApellido() {
		return this.usrApellido;
	}

	public void setUsrApellido(String usrApellido) {
		this.usrApellido = usrApellido;
	}

	public String getUsrCorreo() {
		return this.usrCorreo;
	}

	public void setUsrCorreo(String usrCorreo) {
		this.usrCorreo = usrCorreo;
	}

	public String getUsrDireccion() {
		return this.usrDireccion;
	}

	public void setUsrDireccion(String usrDireccion) {
		this.usrDireccion = usrDireccion;
	}

	public String getUsrEstado() {
		return this.usrEstado;
	}

	public void setUsrEstado(String usrEstado) {
		this.usrEstado = usrEstado;
	}

	public String getUsrEstadoFuncional() {
		return this.usrEstadoFuncional;
	}

	public void setUsrEstadoFuncional(String usrEstadoFuncional) {
		this.usrEstadoFuncional = usrEstadoFuncional;
	}

	public Date getUsrFechaNacimiento() {
		return this.usrFechaNacimiento;
	}

	public void setUsrFechaNacimiento(Date usrFechaNacimiento) {
		this.usrFechaNacimiento = usrFechaNacimiento;
	}

	public String getUsrNombre() {
		return this.usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public String getUsrTelefono() {
		return this.usrTelefono;
	}

	public void setUsrTelefono(String usrTelefono) {
		this.usrTelefono = usrTelefono;
	}

	public String getUsrTipoUsuario() {
		return this.usrTipoUsuario;
	}

	public void setUsrTipoUsuario(String usrTipoUsuario) {
		this.usrTipoUsuario = usrTipoUsuario;
	}

	public List<FabPedidoCab> getFabPedidoCabs() {
		return this.fabPedidoCabs;
	}

	public void setFabPedidoCabs(List<FabPedidoCab> fabPedidoCabs) {
		this.fabPedidoCabs = fabPedidoCabs;
	}

	public FabPedidoCab addFabPedidoCab(FabPedidoCab fabPedidoCab) {
		getFabPedidoCabs().add(fabPedidoCab);
		fabPedidoCab.setFabUsuario(this);

		return fabPedidoCab;
	}

	public FabPedidoCab removeFabPedidoCab(FabPedidoCab fabPedidoCab) {
		getFabPedidoCabs().remove(fabPedidoCab);
		fabPedidoCab.setFabUsuario(null);

		return fabPedidoCab;
	}

}