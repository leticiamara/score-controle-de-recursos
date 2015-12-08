package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelos.Reserva;
import br.ufc.quixada.es.modelos.Tecnico;
import br.ufc.quixada.es.modelos.Usuario;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class ReservaDAO {
	
	public ArrayList<Reserva> listarTodas(){
		
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Reserva.class);
		ArrayList<Reserva> reservas =  (ArrayList<Reserva>) criteria.list();
		
		trasaction.commit();
		sessao.close();
		
		return reservas;
	}
	//coment
	
	public ArrayList<Reserva> listarReservasAprovadas(){
	
		Session sessao = CriarTabelas.prepararSessao();
		Transaction transaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("status", "Aprovada"));
		
		ArrayList<Reserva> reservas = (ArrayList<Reserva>) criteria.list();
		
		transaction.commit();
		sessao.close();
		
		return reservas;
	}
	
	
	
	public ArrayList<Reserva> listarReservasNaoAprovadas() {
		Session sessao = CriarTabelas.prepararSessao();
		Transaction transaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("status", "Nao Aprovada"));
		
		ArrayList<Reserva> reservas = (ArrayList<Reserva>) criteria.list();
		
		transaction.commit();
		sessao.close();
		
		return reservas;
		
	}
	
	public boolean deletar(Reserva reserva){
		
		boolean deletar = false;
		
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			Reserva reservaCarregada = (Reserva) sessao.load(Reserva.class, reserva.getId());
			
			sessao.delete(reservaCarregada);
			
			trasaction.commit();
			deletar = true;
		}
		catch (HibernateException e) {
			// TODO: handle exception
		}
		finally{
			sessao.close();
			return deletar;
		}
		
	}
	
	public boolean cadastrar(Reserva reserva){
		
				Session sessao = CriarTabelas.prepararSessao();
				boolean cadastro = false;
				try{
					Transaction trasaction = sessao.beginTransaction();
					reserva.setStatus("Nao Aprovada");
					sessao.save(reserva);
					
					trasaction.commit();
					cadastro = true;
					
				}catch (HibernateException e) {
					System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
							+ e.toString());
				}
				finally{
				sessao.close();
				return cadastro;
				}
	}
	
	public boolean atualizar(Reserva novaReserva){
		boolean atualizar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
		
		Transaction trasaction = sessao.beginTransaction();
		
		Reserva reservaCarregada = (Reserva) sessao.load(Reserva.class, novaReserva.getId());
				
		reservaCarregada = novaReserva;
		
		sessao.update(reservaCarregada);
		atualizar = true;
		
		trasaction.commit();
		}catch (HibernateException e) {
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
				+ e.toString());
		}
			finally{
					sessao.close();
						return atualizar;
			}
	}
	
	public Reserva buscarReservaPor(Long id){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("id", id));
		
		Reserva reserva =  (Reserva) criteria.uniqueResult();
		
		trasaction.commit();
		sessao.close();
		
		return reserva;
	}
	
	public List<Reserva> buscarReservaPorUsuario(Usuario u){
		Session sessao = CriarTabelas.prepararSessao();
		
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("usuario", u));
//		//consulta.createCriteria("id_usuario").add(Expression.eq("id", 1));
//		
//		Criteria c = sessao.createCriteria("id_usuario");
//		c.add(Expression.e);
//		
//		criteria.createCriteria("id_usuario").add(Expression.eq("id", idUsuario));
		
		List<Reserva> reservas = criteria.list();
		
		trasaction.commit();
		sessao.close();
		
		return reservas;
	}

	//Criei
	public boolean aprovar(Reserva reserva, Tecnico tecnico) {
		
		boolean aprovar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
		Transaction trasaction = sessao.beginTransaction();
		
		Reserva reservaCarregada = (Reserva) sessao.load(Reserva.class, reserva.getId());
				
		reservaCarregada = reserva;
		reservaCarregada.setStatus("Aprovada");
		reservaCarregada.setTecnico(tecnico);
		
		sessao.update(reservaCarregada);
		aprovar = true;
		
		trasaction.commit();
		}catch (HibernateException e) {
			
		}
		finally{
		sessao.close();
		return aprovar;
		}
		
	}

	public void associarUsuarioAReserva(Long idReserva, Usuario usuario) {
		
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		Reserva reservaCarregada = (Reserva) sessao.load(Reserva.class, idReserva);
				
		reservaCarregada.setUsuario(usuario);
		
		sessao.update(reservaCarregada);
		
		trasaction.commit();
		sessao.close();
		
	}
	
}
