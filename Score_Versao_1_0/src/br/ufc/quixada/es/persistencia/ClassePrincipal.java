package br.ufc.quixada.es.persistencia;

import java.util.List;

import br.ufc.quixada.es.DAOs.ReservaDAO;
import br.ufc.quixada.es.DAOs.TecnicoDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelos.Reserva;
import br.ufc.quixada.es.modelos.Tecnico;
import br.ufc.quixada.es.modelos.Usuario;

public class ClassePrincipal {


	public static void main(String[] args) {
//		
		CriarTabelas.prepararSessao();
		CriarTabelas.reiniciaBanco();
		
//		UsuarioDAO dao = new UsuarioDAO();
//		
//		Usuario u = new Usuario();
//		u.setEmail("leticia@gmail.com");
//		u.setLogin("leti");
//		u.setNome("Leticia");
//		u.setSenha("123");
//		
//		dao.insert(u);
//		ReservaDAO reservaDAO = new ReservaDAO();
//		
//		
//		Reserva r =  reservaDAO.buscarReservaPorUsuario(u);
//		System.out.println(r.getUsuario().getNome());
		
//		//Cadastrar um usuario
//		UsuarioDAO dao = new UsuarioDAO();
//		
//		Usuario u = new Usuario();
//		u.setEmail("let@gmail.com");
//		u.setLogin("let");
//		u.setNome("Leticia");
//		u.setSenha("123");
//		
//		dao.insert(u);
		
		//Cadastrar Tecnico
		TecnicoDAO daoTecnico = new TecnicoDAO();
		
		Tecnico tecnico = new Tecnico();
		tecnico.setCodigoAdministrador("12345");
		tecnico.setEmail("venicios@ufc.br");
		tecnico.setFuncao("Secretario");
		tecnico.setLogin("venicio");
		tecnico.setNome("Venicio");
		tecnico.setSenha("123");
		
		daoTecnico.insert(tecnico);
		
//		Usuario u = dao.selectlogin("su", "098");
//		
//		ReservaDAO daoR = new ReservaDAO();
//		Reserva r = daoR.buscarReservaPor(new Long(5));
//		
//		daoR.associarUsuarioAReserva(r.getId(), u);

//		Tecnico tecnico = new Tecnico();

//		tecnico.setCodigoAdministrador("13");
//		tecnico.setEmail("suele@gmail.com");
//		tecnico.setFuncao("secretario");
//		tecnico.setLogin("suele");
//		tecnico.setNome("suelhy");
//		tecnico.setSenha("123");
//		usuario.setEmail("leticia");
//		usuario.setLogin("leticia");
//		usuario.setNome("Leticia");
//		usuario.setSenha("123455");
		
		
//		TecnicoDAO dao = new TecnicoDAO();
//    	tecnico = dao.select("12");
////		dao.cadastrarNovoTecnico(tecnico);
//    	tecnico.setSenha("12");
//		dao.update(tecnico);
		//dao.removerTecnico(tecnico);
		
//		List<Tecnico> tecnicos = dao.selectAll();
//		
//		for (Tecnico t : tecnicos)
//		{
//			System.out.println(t.getNome());
//		}
			
		
}
}
