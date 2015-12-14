package br.ufc.quixada.es.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufc.quixada.es.DAOs.RecursoDAO;
import br.ufc.quixada.es.modelos.Recurso;

@ManagedBean(name="recursoController")
@SessionScoped
public class ControllerRecursos {

	private RecursoDAO recursoDAO = new RecursoDAO();
	private Recurso recurso = new Recurso();
	private List<Recurso> listaDeRecursos = new ArrayList<Recurso>();
	private List<Recurso> listaDeRecursosDisponiveis = new ArrayList<Recurso>();
	
	public String adicionar(){
		if(recursoDAO.insert(recurso)){
			recurso = new Recurso();
			addMessage("Recurso Adicionado");
			
		}else{
			addMessage("Recurso Nao Adicionado");
		}
		
		return "/Tecnico/listarRecurso.xhtml";
	}
	
	public void remover(){
		
		if(recursoDAO.delete(recurso)){
			addMessage("Recurso Removido");
		}
		else{
			addMessage("Recurso Nao Removido");
		}
	}
	
	public String editar(){
		if(recursoDAO.update(recurso)){
			addMessage("Recurso Editado");
		}
		else{
			addMessage("Recurso Nao Editado");
		}
		return "/Tecnico/listarRecurso.xhtml";
	}
	
	//Getters & Setters
	public RecursoDAO getRecursoDAO() {
		return recursoDAO;
	}

	public void setRecursoDAO(RecursoDAO recursoDAO) {
		this.recursoDAO = recursoDAO;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public List<Recurso> getListaDeRecursos() {
		listaDeRecursos = recursoDAO.select();
		return listaDeRecursos;
	}

	public void setListaDeRecursos(List<Recurso> listaDeRecursos) {
		this.listaDeRecursos = listaDeRecursos;
	}

	public List<Recurso> getListaDeRecursosDisponiveis() {
		listaDeRecursosDisponiveis = recursoDAO.selectDisponibilidade();
		return listaDeRecursosDisponiveis;
	}

	public void setListaDeRecursosDisponiveis(List<Recurso> listaDeRecursosDisponiveis) {
		this.listaDeRecursosDisponiveis = listaDeRecursosDisponiveis;
	}
	
	public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
}
