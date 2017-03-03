package fr.esiea.puig.gnondoli.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.word.Word;

public class testveriflettre {
	
	plateau pot=new plateau();
	Word mottest=new Word(pot.getCommunPot(),"de");
	
	@Test
	public void testlettresliste(){
		mottest.mottoliste();
		pot.getCommunPot().add('d');
		assertTrue("la liste ne possede bien pas de e",mottest.lettreVerifier()==false);
		assertFalse("problem",mottest.lettreVerifier()==true);
	}

}
