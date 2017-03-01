package fr.esiea.puig.gnondoli.Plateau;

import java.util.ArrayList;

import java.util.List;

public class LettresPlateau{
	
	//classe du pot commun avec les lettres 
	private List<Character> CommunPot ;
	
	public LettresPlateau() {
		this.CommunPot=(new ArrayList<Character>());
		
	}

	public List<Character> getCommunPot() {
		return CommunPot;
	}

	public void setCommunPot(List<Character> communPot) {
		CommunPot = communPot;
	}

	
}
