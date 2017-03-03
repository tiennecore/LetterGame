package fr.esiea.gnondoli.puig.word;

import fr.esiea.gnondoli.puig.gameboard.plateau;


public class sac {
	
	private plateau potcommun;
	
	public sac(plateau pot){
		this.potcommun=pot;
	}
	
	public void getNextLetter(int randomvaleur) {
		char lettre=(char)('a' + randomvaleur );
		potcommun.getCommunPot().add(lettre);
	}

}
