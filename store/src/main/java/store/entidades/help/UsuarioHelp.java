package store.entidades.help;

public class UsuarioHelp {
	private String idUsr;
	private String apellido;
	private String correo;
	private String nombre;
	
	public UsuarioHelp(String idUsr, String apellido,
			String correo, String nombre) {
		super();
		this.idUsr = idUsr;
		this.apellido = apellido;
		this.correo = correo;
		this.nombre = nombre;
	}

	public String getIdUsr() {
		return idUsr;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public String getNombre() {
		return nombre;
	}
}
