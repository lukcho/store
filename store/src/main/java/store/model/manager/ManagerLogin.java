package store.model.manager;

import store.model.dao.entities.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerLogin{

	@EJB
	private ManagerDAO mDAO;
	
	String h="";		
				
	public ManagerLogin() {
	}

	// USUARIOS
	/**
	 * buscar todos los usuarios
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	
	@SuppressWarnings("unchecked")
	public List<FabUsuario> findUsuario() {
		return mDAO.findWhere(FabUsuario.class, "1=1", null);
	}

	/**
	 * listar todos los usuarios
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked") 
	public List<FabUsuario> findAllUsuarios() {
		return mDAO.findAll(FabUsuario.class);
	}

	/**
	 * buscar usuarios por ID
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public FabUsuario UsuarioByID(String id_usr) throws Exception {
		return (FabUsuario) mDAO.findById(FabUsuario.class, id_usr);
	}
	
	/**
	 * Agrega usuario
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public void insertarusuarios(String usr_id, String nombre, String apellido,
			String correo, String password, String direccion,
			Date fecha_nacimiento, String telefono) throws Exception {
		FabUsuario usr = new FabUsuario();
		usr.setUsrId(usr_id);
		usr.setUsrNombre(nombre);
		usr.setUsrApellido(apellido);
		usr.setUsrCorreo(correo);
		usr.setUsrPassword(password);
		usr.setUsrDireccion(direccion);
		usr.setUsrFechaNacimiento(fecha_nacimiento);
		usr.setUsrTelefono(telefono);
		usr.setUsrEstado("A");
		usr.setUsrEstadoFuncional("A");
		usr.setUsrTipoUsuario("C");
		mDAO.insertar(usr);
	}

	/**
	 * Cambiar datos de perfil usuario
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public void editarusuario(String usr_id, String nombre, String apellido,
			String correo, String password, String direccion,
			Date fecha_nacimiento, String telefono, String estado,
			String estado_funcional, String tipo_usr) throws Exception {
		FabUsuario usr = this.UsuarioByID(usr_id);
		usr.setUsrNombre(nombre);
		usr.setUsrApellido(apellido);
		usr.setUsrCorreo(correo);
		usr.setUsrPassword(password);
		usr.setUsrDireccion(direccion);
		usr.setUsrFechaNacimiento(fecha_nacimiento);
		usr.setUsrTelefono(telefono);
		mDAO.actualizar(usr);
	}
	
	/**
	 * Cambiar estado usuario
	 * @param id_usr
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */	
	public String cambioEstadousr(String usr_id) throws Exception{
		String h="";
		FabUsuario usr = UsuarioByID(usr_id);						
		
		if(usr.getUsrEstado().equals("A")){
			usr.setUsrEstado("D");
			h="Estado del Usuario Modificado";
			}
		else if(usr.getUsrEstado().equals("D")){
			usr.setUsrEstado("A");
			h="Estado del Registro Modificado";
			}
		mDAO.actualizar(usr);
		return h;
		}		
	
	
	/**
	 * Cambiar pass usuario
	 * @param id_usr
	 * @param pass
	 * @throws Exception
	 */
	public void cambiarPassUSR(String id_usr, String pass) throws Exception{
		FabUsuario usr = this.UsuarioByID(id_usr);
		usr.setUsrPassword(pass);
		mDAO.actualizar(usr);
	}
	
		
	/**
	 * Método que busca un usuario respecto a su nick y contraseña
	 * @param nick 
	 * @param pass
	 * @return Usuario o null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public FabUsuario findUserByusr_idAndPass(String usr_id, String pass)throws Exception{
		try {
			List<FabUsuario> listado = (List<FabUsuario>) mDAO.findByParam(FabUsuario.class, "o.usr_id", usr_id, null);
			if(listado == null || listado.isEmpty()){
				throw new Exception("No se encuentra el usuario."); 
			}
			FabUsuario u = listado.get(0);
			if(u.getUsrEstado().equals("D")){
				throw new Exception("Su usuario ha sido desactivado.");
			}
			if (u.getUsrPassword().equals(getMD5(pass))) {//MD5 PASS
				return u;
			}else{
				throw new Exception("Usuario o contraseña invalidos");
			}		
		} catch (Exception e) {	
			throw new Exception("Error al intentar ingresar al sistema, "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param input entrada de cadena para convertirla en MD5
	 * @return String MD5
	 */
	public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * Verifica si el usuario esta activado
	 * @param u Usuario a analizar
	 * @return true o false
	 */
	public boolean esUsrActivo(FabUsuario u){
		boolean  resp = false;
		if(u.getUsrEstado().equals("A")){
			resp = true;
		}
		return resp;
	}
	
	/**
	 * Busca el usuario por alias
	 * @param alias nombre del usuario especifico para el acceso
	 * @return
	 */
	public FabUsuario findUserByusr_id(String usr_id) {
		FabUsuario u = null;
		List<FabUsuario> usuarios = findAllUsuarios();
		for (FabUsuario usuario : usuarios) {
			if (usuario.getUsrId().equals(usr_id)) {
				u = usuario;
			}
		}
		return u;
	}
}
