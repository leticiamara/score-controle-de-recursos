package br.ufc.quixada.es.DAOs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelos.Tecnico;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class TecnicoDAO 
{
public  boolean insert(Tecnico tecnico) {
		
		//Cria uma sessao
		boolean inserir = false;
		Session sessao = CriarTabelas.prepararSessao();
		try{
			//Abre uma transacao
			Transaction trasaction = sessao.beginTransaction();
			
			//Salva o usuario no banco
			sessao.save(tecnico);
			
			//Fecha a transacao
			trasaction.commit();
			inserir = true;
			
		}catch (HibernateException e) {
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
		}
		//Fecha a sessao
		finally{
		sessao.close();
		return inserir;
		}
	}
	
	public boolean delete(Tecnico tecnico) {
		
		boolean deletar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			
			Tecnico tecnicoCarregado = (Tecnico) sessao.load(Tecnico.class, tecnico.getId());
			
			sessao.delete(tecnicoCarregado);
			
			trasaction.commit();
			deletar = true;
		}catch (HibernateException e) {
			
		}
		finally{
			sessao.close();
			return deletar;
		}
		
		
		
	}
	
	public  Tecnico select(String codigoAdministrador) {
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("codigoAdministrador", codigoAdministrador));
		
		Tecnico tecnico = (Tecnico) criteria.uniqueResult();
		//Tratar erro de usuario
		trasaction.commit();
		
		sessao.close();
		
		return tecnico;
	}
	
	public List<Tecnico> selectAll()
	{
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Tecnico.class);
		
		List<Tecnico> tecnico = (List<Tecnico>) criteria.list();
		//Tratar erro de usuario
		trasaction.commit();
		
		sessao.close();
		
		return tecnico;
	}
	@SuppressWarnings("finally")
	public Tecnico selectlogin(String login,String senha){
		
		Session session = CriarTabelas.prepararSessao();
		Tecnico tecnico = new Tecnico();
	    try{
//	    	Transaction trasaction = session.beginTransaction();
//	    	Criteria criteria = session.createCriteria(Usuario.class);
//			criteria.add(Restrictions.eq("login", login));
//			criteria.add(Restrictions.eq("login", login));
//			usuario = (Usuario) criteria.uniqueResult();
//			trasaction.commit();
	    	String hql = "from Tecnico tecnico where tecnico.login like ? and tecnico.senha like ?";
	    	Query query = session.createQuery(hql).setString(0, login).setString(1, senha);
	    	tecnico = (Tecnico) query.uniqueResult();
	    }
	    catch(HibernateException e){
	    	System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
	    }
	    finally{
	    	session.close();
	    	return tecnico;
	    }
	}
	
	public boolean update(Tecnico novoTecnico) {
		
		boolean atualizar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			
			Tecnico novoTecnicoCarregado = (Tecnico) sessao.load(Tecnico.class, novoTecnico.getId());
					
			novoTecnicoCarregado = novoTecnico;
			
			sessao.update(novoTecnicoCarregado);
			
			trasaction.commit();
			atualizar = true;
		}catch (HibernateException e) {
			// TODO: handle exception
		}
		finally{
			sessao.close();
			return atualizar;
		}
		
	}

}

