package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelos.Recurso;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class RecursoDAO {
	
	public boolean insert(Recurso recurso){
		boolean inserir = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			sessao.save(recurso);
			trasaction.commit();
			inserir = true;
			
		} catch (HibernateException e) {
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
		}
		finally{
			sessao.close();
			return inserir;
		}
	}

	public boolean delete(Recurso recurso) {
		
		boolean deletar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			
			Recurso recursoCarregado = (Recurso) sessao.load(Recurso.class, recurso.getIdRecurso());
			
			sessao.delete(recursoCarregado);
			
			trasaction.commit();
			deletar = true;
		}catch (HibernateException e) {
			
		}
		finally{

			sessao.close();
			return deletar;
		}
		
	}

	public boolean update(Recurso novoRecurso) {
		
		boolean atualizar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{Transaction trasaction = sessao.beginTransaction();
		
		Recurso novoRecursoCarregado = (Recurso) sessao.load(Recurso.class, novoRecurso.getIdRecurso());
				
		novoRecursoCarregado = novoRecurso;
		
		sessao.update(novoRecursoCarregado);
		
		trasaction.commit();
		atualizar = true;
		
		}catch (HibernateException e) {
			
		}
		finally{
			sessao.close();
			return atualizar;
		}
		
	}

	public Recurso selectUnicoRecurso(int numeroRecurso){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Recurso.class);
		criteria.add(Restrictions.eq("numeroRecurso", numeroRecurso));
		
		Recurso recurso = (Recurso) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return recurso;
	}
	
	public List<Recurso> select() {
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Recurso.class);
		ArrayList<Recurso> recursos = (ArrayList<Recurso>) criteria.list();

		trasaction.commit();
		
		sessao.close();
		
		return recursos;
	}
	
	public List<Recurso> selectDisponibilidade(){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Recurso.class);
		criteria.add(Restrictions.eq("disponibilidade", false));
		
		ArrayList<Recurso> recursos = (ArrayList<Recurso>) criteria.list();
		
		trasaction.commit();
		
		sessao.close();
		
		return recursos; 
	}
	
	public void modificarDisponibilidade(Recurso recurso){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		recurso.setDisponibilidade(false);
		Recurso novoRecursoCarregado = (Recurso) sessao.load(Recurso.class, recurso.getIdRecurso());
				
		novoRecursoCarregado = recurso;
		
		sessao.update(novoRecursoCarregado);
		
		trasaction.commit();
		sessao.close();
		
	}	
}
