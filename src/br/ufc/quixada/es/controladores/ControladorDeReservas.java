package br.ufc.quixada.es.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufc.quixada.es.DAOs.RecursoDAO;
import br.ufc.quixada.es.DAOs.ReservaDAO;
import br.ufc.quixada.es.email.EnviarEmail;
import br.ufc.quixada.es.modelos.Recurso;
import br.ufc.quixada.es.modelos.Reserva;

@ManagedBean(name="controladorReserva")
@SessionScoped
public class ControladorDeReservas {
	
	private Reserva reserva = new Reserva();
	private ReservaDAO dao = new ReservaDAO();
	private RecursoDAO daorec = new RecursoDAO();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private List<Reserva> reservasUsuario = new ArrayList<Reserva>();
	private ArrayList<Reserva> todasReservas = new ArrayList<Reserva>();
	private ArrayList<Reserva> todasReservasAprovadas = new ArrayList<Reserva>();
	
	public String reservar(){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController = (LoginController)session.getAttribute("loginController"); 
		System.out.println(loginController.getUsuario().getNome());
		
		ExternalContext externalContext2 = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session2 = (HttpSession) externalContext2.getSession(true);  
		ControllerRecursos controllerRecursos = (ControllerRecursos) session2.getAttribute("recursoController"); 
		
		reserva.setUsuario(loginController.getUsuario());
		
		System.out.println(loginController.getUsuario().getNome());
		
		
		if(dao.cadastrar(reserva)){
			addMessage("Dados Salvos");
		}
		else{
			addMessage("Dados nao Salvos");
		}
		return "/Usuario/listarReservasUsuario.xhtml";
	}
	
	public String deletarReserva(){
		if(dao.deletar(reserva)){
			addMessage("Reserva Deletada");
		}
		else{
			addMessage("Reserva nao deletada");
		}
		return "/Usuario/listarReservasUsuario.xhtml";
	}
	
	public String editar() {
			if(dao.atualizar(reserva)){
				addMessage("Reserva Editada");
			}
			else{
				addMessage("Reserva Nao Editada");
			}
		return "/Usuario/listarReservasUsuario.xhtml";
	}
	
	public ArrayList<Reserva> listarTodasReservas(){
		return reservas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public ArrayList<Reserva> getReservas() {
		reservas = dao.listarReservasNaoAprovadas();
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
	public ArrayList<Reserva> getTodasReservasAprovadas() {
		return todasReservasAprovadas = dao.listarReservasAprovadas();
	}

	public void setTodasReservasAprovadas(ArrayList<Reserva> todasReservasAprovadas) {
		this.todasReservasAprovadas = todasReservasAprovadas;
	}

	public void aprovarReserva(){ 
		
		//Pegar Sessao do Tecnico
		ExternalContext externalContext2 = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession session = (HttpSession) externalContext2.getSession(true); 
		LoginController loginController = (LoginController) session.getAttribute("loginController");
		
		if(dao.aprovar(reserva,loginController.getTecnico())){
			
			EnviarEmail email = new EnviarEmail();
			
			email.enviar(reserva.getUsuario().getEmail(), "Reserva de Recurso de Aprovada", "A sua reserva foi aprovada");
			
			Recurso rec = reserva.getRecurso();
			int qntd = rec.getQuantidade();
			rec.setQuantidade(qntd-1);
			
			daorec.update(rec);
			
			addMessage("Reserva Aprovada");
		}
		else{
			addMessage("Reserva Nao Aprovada");
		}
	}
	
	public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

	public List<Reserva> getReservasUsuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpSession session = (HttpSession) externalContext.getSession(true);  
		LoginController loginController = (LoginController)session.getAttribute("loginController"); 
		
		return reservasUsuario =  dao.buscarReservaPorUsuario(loginController.getUsuario());
	}

	public void setReservasUsuario(List<Reserva> reservasUsuario) {
		this.reservasUsuario = reservasUsuario;
	}

	public ArrayList<Reserva> getTodasReservas() {
		todasReservas = dao.listarTodas();
		return todasReservas;
	}

	public void setTodasReservas(ArrayList<Reserva> todasReservas) {
		this.todasReservas = todasReservas;
	}
	
	public void recusarReserva(){  
		if(dao.deletar(reserva)){
			
			addMessage("Reserva Recusada");
		}
		else{
			addMessage("Reserva Nao Recusada");
		}
	}
	
	public void devolverReserva(){
		if(dao.deletar(reserva)){
			Recurso rec = reserva.getRecurso();
			int qntd = rec.getQuantidade();
			rec.setQuantidade(qntd+1);
			
			daorec.update(rec);
			
			addMessage("Recurso Devolvido");
		}
		else{
			addMessage("Recurso Nao pode ser Devolvido");
		}
	}

}
