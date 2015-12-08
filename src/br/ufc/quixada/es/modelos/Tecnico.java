package br.ufc.quixada.es.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Tecnico implements Serializable
{
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=false)
	private String nome;
	
	@Column(unique=true)
	private String login;
	
	@Column(unique=false)
	private String senha;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=false)
	private String funcao;
	
	@Column(unique=true)
	private String codigoAdministrador;
	
	@OneToMany(mappedBy="tecnico")
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getCodigoAdministrador() {
		return codigoAdministrador;
	}
	public void setCodigoAdministrador(String codigoAdministrador) {
		this.codigoAdministrador = codigoAdministrador;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
