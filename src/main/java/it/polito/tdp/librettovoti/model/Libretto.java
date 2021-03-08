package it.polito.tdp.librettovoti.model;

import java.util.*;

public class Libretto {
	
	private List<Voto> voti;
	
	public Libretto() {
		this.voti = new ArrayList<Voto>(); //Si può omettere il Voto perché già lo capisce
	}
	
	public void add(Voto v) {
		this.voti.add(v);
	}
	
	/*
	public void stampaVotiUguali (int punteggio) {
		//il libretto stampa da solo i voti
	}
	
	public String votiUguali (int punteggio) {
		//calcola una stringa che contiene i voti
		//il chiamante poi li stamperà
		// Solo il nome o tutto il voto.toString()?
	}
	*/
	public List<Voto> listaVotiUguali (int punteggio) {
		//restituisce solo i voti uguali al criterio
		List<Voto> risultato = new ArrayList<>();
		for (Voto v : this.voti) {
			if (v.getVoto()==punteggio) {
				risultato.add(v);
			}
		}
		return risultato;
	}
	
	public Libretto votiUguali (int punteggio) {
		//creo un nuovo libretto solo con i voti che mi interessano
		Libretto risultato = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == punteggio) {
				risultato.add(v); //risultato.voti.add(v)
			}
		}
		return risultato;
	}
	
	/**
	 * Ricerca un Voto del corso di cui è specificato il nome.
	 * Se il corso non esiste, restituisce null.
	 * @param nomeCorso Nome del corso ricercato
	 * @return Voto del corso ricercato
	 */
	public Voto ricercaCorso(String nomeCorso) {
		Voto risultato = null;
		for (Voto v : this.voti) {
			if (v.getNome().equals(nomeCorso)) {
				risultato = v;
				break;
			}
		}
		return risultato;
	}
	
	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s = s + v.toString() + "\n";
		}
		return s;
	}

}
