package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.Query;

import br.ufc.quixada.es.modelos.Usuario;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class UsuarioDAO {

	@SuppressWarnings("finally")
	public boolean insert(Usuario usuario){
	
		Session session = CriarTabelas.prepararSessao();
		boolean cadastro = false;
		
		try{
			Transaction trasaction = session.beginTransaction();
			session.save(usuario);
			trasaction.commit();
			cadastro = true;
		}
		catch(HibernateException e){
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
		}
		finally{
			session.close();
			return cadastro;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean delete(Usuario usuario){
		
		Session session = CriarTabelas.prepararSessao();
		boolean excluir = false;
		
		try{
			Transaction trasaction = session.beginTransaction();
			Usuario usuarioCarregado = (Usuario) session.load(Usuario.class, usuario.getId());
			session.delete(usuarioCarregado);
			
			trasaction.commit();
			excluir = true;
		}
		catch(HibernateException e){
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
		}
		finally{
			session.close();
			return excluir;
		}
	}
	
	@SuppressWarnings("finally")
	public Usuario selectlogin(String login,String senha){
		
		Session session = CriarTabelas.prepararSessao();
		Usuario usuario = new Usuario();
	    try{
//	    	Transaction trasaction = session.beginTransaction();
//	    	Criteria criteria = session.createCriteria(Usuario.class);
//			criteria.add(Restrictions.eq("login", login));
//			criteria.add(Restrictions.eq("login", login));
//			usuario = (Usuario) criteria.uniqueResult();
//			trasaction.commit();
	    	String hql = "from Usuario usuario where usuario.login like ? and usuario.senha like ?";
	    	Query query = session.createQuery(hql).setString(0, login).setString(1, senha);
	    	usuario = (Usuario) query.uniqueResult();
	    }
	    catch(HibernateException e){
	    	System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
	    }
	    finally{
	    	session.close();
	    	return usuario;
	    }
	}
	
	@SuppressWarnings("finally")
	public List<Usuario> selectAll(){
		
		Session session = CriarTabelas.prepararSessao();
		List<Usuario> usuarios = new ArrayList<Usuario>();
	    try{
	    	Transaction trasaction = session.beginTransaction();
	    	@SuppressWarnings("unchecked")
			ArrayList<Usuario> list = (ArrayList<Usuario>)session.createCriteria(Usuario.class).list();
			usuarios = list;
			trasaction.commit();
	    }
	    catch(HibernateException e){
	    	System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
	    }
	    finally{
	    	session.close();
	    	return usuarios;
	    }
	}
	
//	public List<Reserva> listarTodasReservasUsuario(Usuario usuario){
//		
////		Session sessao = CriarTabelas.prepararSessao();
////		Transaction trasaction = sessao.beginTransaction();
////			
////		Criteria criteria = sessao.createCriteria(Usuario.class);
////		criteria.add(Restrictions.eq("id", id));
////		
////		List<Reserva> reservas =  (List<Reserva>) criteria.list();
////		
////		trasaction.commit();
////		sessao.close();
////		
////		return reservas;
//	}
	
	@SuppressWarnings("finally")
	public boolean update(Usuario usuario){
		Session session = CriarTabelas.prepararSessao();
		boolean atualizar = false;
		
		try{
			Transaction trasaction = session.beginTransaction();
			
			Usuario novoUsuarioCarregado = (Usuario) session.load(Usuario.class, usuario.getId());
			novoUsuarioCarregado = usuario;
					
			session.update(novoUsuarioCarregado);
			trasaction.commit();
			
			atualizar = true;
		}
		catch(HibernateException e){
	    	System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
	    }
		finally{
			session.close();
			return atualizar;
		}
	}
}

