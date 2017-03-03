package fr.esiea.puig.gnondoli.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.word.Word;

public class testmotalettre {
	plateau pot=new plateau();
	Word mottest=new Word(pot.getCommunPot(),"mot");
	
	@Test
	public void testPassageChar(){
		mottest.mottoliste();
		assertTrue("le mot est en bien transformé en char",mottest.getCharactermot().get(mottest.getWord().length()-1)==(Character) mottest.getWord().charAt(mottest.getWord().length()-1));
		assertFalse("le mot n'est transformé en char",mottest.getCharactermot().get(mottest.getWord().length()-1)!=(Character) mottest.getWord().charAt(mottest.getWord().length()-1));

	}

}
