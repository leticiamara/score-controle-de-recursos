package br.ufc.quixada.es.controladores;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;


import br.ufc.quixada.es.DAOs.UsuarioDAO;

import br.ufc.quixada.es.modelos.Usuario;

@ManagedBean(name="usuarioController")
public class UsuarioController {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public String cadastrar(){
		
		if(usuarioDAO.insert(usuario)){
			  addMessage("Dados salvos"); 
		}else{
			addMessage("Dados não salvos"); 
		}
	
	    usuario = new Usuario();
	    
	    return "/Tecnico/usuarios.xhtml";
		
	}
	
	public void remover(){
		
		if(usuarioDAO.delete(usuario)){
			
			addMessage("Usuario removido");
		}else{
		
			addMessage("Usuario não pode ser removido");
		}
		
		 
	}
	
	public String atualizar(){
		
		if(usuarioDAO.update(usuario)){
			addMessage("Dados Atualizados"); 
			//
		}else{
			addMessage("Dados Não Atualizados"); 
		}
		
		usuario = new Usuario();
		
		 return "/Tecnico/usuarios.xhtml";
	}
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public List<Usuario> getUsuarios() {
		usuarios = usuarioDAO.selectAll();
		return usuarios;
	}

	public void addMessage(String summary) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }
	
}
