package br.ufc.quixada.es.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufc.quixada.es.DAOs.TecnicoDAO;
import br.ufc.quixada.es.modelos.Tecnico;

@ManagedBean(name="tecnicoController")
@SessionScoped
public class TecnicoController
{
	private Tecnico tecnico = new Tecnico();
	private List<Tecnico> tecnicos = new ArrayList<Tecnico>();
	private TecnicoDAO tecnicoDao = new TecnicoDAO();
	
	public String salvar()
	{
		if(tecnicoDao.insert(tecnico)){
			tecnico = new Tecnico();
			addMessage("Tecnico Adicionado");
		}
		else{
			addMessage("Tecnico Nao Adicionado");
		}
		
		return "/Tecnico/listagemTecnico.xhtml";
	}
	
	public String atualizar()
	{
		tecnicoDao.update(tecnico);
		return "/Tecnico/listagemTecnico.xhtml";
	}
	
	public String deletar()
	{
		tecnicoDao.delete(tecnico);
		return "/Tecnico/listagemTecnico.xhtml";
	}
	
	public void setTecnico(Tecnico tecnico) 
	{
		this.tecnico = tecnico;
	}
	public Tecnico getTecnico() 
	{
		return tecnico;
	}

	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public List<Tecnico> getTecnicos() 
	{
		tecnicos = tecnicoDao.selectAll();
		return tecnicos;
	}
	
	public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 

}
