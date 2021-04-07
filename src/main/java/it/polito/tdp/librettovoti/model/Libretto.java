package it.polito.tdp.librettovoti.model;

import java.util.*;

public class Libretto {
	
	private List<Voto> voti;
	private Map<String, Voto> votiMap; //identity map: nome esame -> oggetto Voto
	private Map<String, Voto> votiMigliorati;
 	
	public Libretto() {
		this.voti = new ArrayList<Voto>(); //Si può omettere il Voto perché già lo capisce
		this.votiMap = new TreeMap<>();
		this.votiMigliorati = new TreeMap<>();
	}
	
	public void add(Voto v) {
		this.voti.add(v);
		this.votiMap.put(v.getNome(), v);
		Voto vCorretto = new Voto(v);
		if (vCorretto.getVoto()>23) {
			vCorretto.setVoto(vCorretto.getVoto()+2);
		} else if (v.getVoto() > 17) {
			vCorretto.setVoto(vCorretto.getVoto()+1);
		}
		if (vCorretto.getVoto()>30) {
			vCorretto.setVoto(30);
		}
		this.votiMigliorati.put(vCorretto.getNome(), vCorretto);
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
				//risultato.voti.add(v) meglio di no perché add fa più cose
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
		/*Voto risultato = null;
		for (Voto v : this.voti) {
			if (v.getNome().equals(nomeCorso)) {
				risultato = v;
				break;
			}
		}
		return risultato;*/
		return this.votiMap.get(nomeCorso);
	}
	
	/**
	 * Verifica se nel libretto c'è già un voto con lo stesso esame e la
	 * stessa votazione.
	 * @param v Il voto da controllare
	 * @return True se c'è duplicato
	 */
	public boolean esisteDuplicato(Voto v) {
		/*boolean trovato = false;
		for (Voto voto : this.voti) {
			if (voto.getNome().equals(v.getNome()) && voto.getVoto()==v.getVoto()) {
				trovato = true;
				break;
			}
		}
		return trovato; */
		
		Voto trovato = this.votiMap.get(v.getNome());
		if (trovato == null) {
			return false;
		}
		if (trovato.getVoto()==v.getVoto()) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Verifica se nel libretto c'è gia un voto con lo stesso esame ma 
	 * votazione diversa.
	 * @param v Il voto da controllare
	 * @return True se c'è conflitto
	 */
	public boolean esisteConflitto(Voto v) {
		/*boolean trovato = false;
		for (Voto voto : this.voti) {
			if (voto.getNome().equals(v.getNome()) && voto.getVoto()!=v.getVoto()) {
				trovato = true;
				break;
			}
		}
		return trovato; */
		
		Voto trovato = this.votiMap.get(v.getNome());
		if (trovato == null) {
			return false;
		}
		if (trovato.getVoto()!=v.getVoto()) {
			return true;
		}
		else
			return false;
	}
	
	public void cancellaSotto24() {
		
		for (Voto v : this.votiMap.values()) {
			if (v.getVoto()<24) {
				votiMap.remove(v.getNome());
			}
		}
		
	}
	
	public String toString() {
		String s = "";
		for (Voto v : this.votiMap.values()) {
			s = s + v.toString() + "\n";
		}
		return s;
	}
	
	public String toStringMigliorato() {
		String s = "";
		for (Voto v : this.votiMigliorati.values()) {
			s = s + v.toString() + "\n";
		}
		return s;
	}

}
