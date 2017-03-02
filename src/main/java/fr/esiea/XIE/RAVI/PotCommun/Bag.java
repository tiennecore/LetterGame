package fr.esiea.XIE.RAVI.PotCommun;

import java.util.Collections;

import fr.esiea.XIE.RAVI.Plateau.LettresPlateau;

public class Bag implements ILetterBag{

	private LettresPlateau plateau;
	
	public Bag(LettresPlateau pot){
		this.plateau=pot;
	}
	@Override
	public void getNextLetter(int valrand) {
		char lettre=(char)('a' + valrand );
		plateau.getCommunPot().add(lettre);
		plateau.getCommunPot().removeAll(Collections.singleton(null));
	}
	
}
