package br.ufc.quixada.es.controladores;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



import br.ufc.quixada.es.DAOs.TecnicoDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelos.Tecnico;
import br.ufc.quixada.es.modelos.Usuario;


@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {

	private String login;
	private String senha;
	FacesMessage msg = null; 
	private UsuarioDAO dao = new UsuarioDAO();
	private TecnicoDAO daoTecnico = new TecnicoDAO();
	private Usuario usuario = new Usuario();
	private Tecnico tecnico = new Tecnico();
	private String tipo;
	  
	
	public String logout(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		
		login = "";
		senha = "";
		usuario = new Usuario();
		tecnico = new Tecnico();
		
		return "/login.xhtml";
	}
	
	public String login() throws IOException{
		
		if(tipo.equals("tecnico")){
			tecnico = daoTecnico.selectlogin(login, senha);
			
			if(tecnico != null){
				  
				// usuario = new Usuario();
				 return "/Tecnico/homeTecnico.xhtml";

			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Error", "Invalid credentials");
				FacesContext.getCurrentInstance().addMessage(null, msg); 
				tecnico = new Tecnico();
				return "";
			}
			
		}else if(tipo.equals("usuario")){
			
			usuario = dao.selectlogin(login, senha);
			if(usuario != null){
				  
				//usuario = new Usuario();
				 return "/Usuario/homeUsuario.xhtml";
	
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login/Senha Error", "Invalid credentials");
				FacesContext.getCurrentInstance().addMessage(null, msg); 
				usuario = new Usuario();
				return "";
			}
	}
		return "";
	
}

	public UsuarioDAO getDao() {
		return dao;
	}


	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public FacesMessage getMsg() {
		return msg;
	}

	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}

	public TecnicoDAO getDaoTecnico() {
		return daoTecnico;
	}

	public void setDaoTecnico(TecnicoDAO daoTecnico) {
		this.daoTecnico = daoTecnico;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
	
}
