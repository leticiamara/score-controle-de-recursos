package br.ufc.quixada.es.controladores;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="idiomaController")
@SessionScoped
public class IdiomaController {
	
	private Locale correnteLocale = new Locale("pt", "BR");
	
	public void setLocalePortugues(){
		correnteLocale = new Locale("pt", "BR");
	}
	public void setLocaleIngles(){
		correnteLocale = new Locale("en", "ES");
	}
	public void setLocaleEspanhol(){
		correnteLocale = new Locale("es", "SN");
	}
	public Locale getCorrenteLocale() {
		return correnteLocale;
	}
	public void setCorrenteLocale(Locale correnteLocale) {
		this.correnteLocale = correnteLocale;
	}

}
