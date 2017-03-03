package fr.esiea.puig.gnondoli.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.word.sac;

public class testaddletter {
	plateau potcommun=new plateau();
	sac bag=new sac(potcommun);
	
	@Test
	public void ajouterlettre(){
		bag.getNextLetter(4);
		assertTrue("valeur ajouter à la liste", potcommun.getCommunPot().size()!=0);
		assertFalse("valeur ajouter à la liste", potcommun.getCommunPot().size()==0);
	}

}
