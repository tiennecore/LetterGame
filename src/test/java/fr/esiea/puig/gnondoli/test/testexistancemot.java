package fr.esiea.puig.gnondoli.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.player.Joueur;

public class testexistancemot {
	plateau pot=new plateau();
	Joueur player1=new Joueur(pot,"j1");
	@Test
	public void testmotexistant(){
		player1.getMots().get(0).setWord("lala");
		//assertTrue("",player1.motexistant("lala")==true);
		//assertFalse("",player1.motexistant("lala")!=true);
	}

}
