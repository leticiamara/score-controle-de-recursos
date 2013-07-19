package br.ufc.quixada.es.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recurso {
	
	@Id
	@GeneratedValue
	private long idRecurso;
	
	@Column
	private String nome;
	private int numeroRecurso;
	private boolean disponibilidade;
	private int quantidade;
	
	@OneToMany(mappedBy="recurso")
	private List<Reserva> reserva = new ArrayList<Reserva>();
	
	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long idRecurso) {
		this.idRecurso = idRecurso;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroRecurso() {
		return numeroRecurso;
	}

	public void setNumeroRecurso(int numeroRecurso) {
		this.numeroRecurso = numeroRecurso;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

}
