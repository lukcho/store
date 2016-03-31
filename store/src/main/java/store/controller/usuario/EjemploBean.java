package store.controller.usuario;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import store.model.manager.ManagerLogin;

@ViewScoped
@ManagedBean
public class EjemploBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 596767839617090375L;

	private String hola;
	
	@EJB
	private ManagerLogin m; 
	
	public EjemploBean() {
		hola = "HOLA";
	}
	
	
	
	
	public String getHola() {
		return hola;
	}
	
	public void setHola(String hola) {
		this.hola = hola;
	}
	
}
