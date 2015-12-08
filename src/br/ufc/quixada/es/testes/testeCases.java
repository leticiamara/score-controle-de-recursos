package br.ufc.quixada.es.testes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ufc.quixada.es.DAOs.ReservaDAO;
import br.ufc.quixada.es.modelos.Reserva;

public class testeCases {
	
	private Reserva reserva;
	
	@Mock
	private ReservaDAO reservaDAO;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		reserva = new Reserva();
	}
	
	@Test
	public void cadastro(){
		when(reservaDAO.cadastrar(reserva)).thenReturn(true);
		boolean resposta = reservaDAO.cadastrar(reserva);
		
		assertEquals(true, resposta);
	}

}
