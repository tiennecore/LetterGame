package fr.esiea.puig.gnondoli.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fr.esiea.gnondoli.puig.EngineGame.Jeu;
import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.player.Joueur;

public class testverifmot {
	plateau potcommun=new plateau();
	Joueur p1=new Joueur(potcommun,"p1");
	Joueur p2=new Joueur(potcommun,"p2");
	List<Joueur> players = Arrays.asList(p1,p2);
	Jeu game=new Jeu(potcommun,players);
	
	@Test
	public void testmot(){
		p1.getMots().get(0).setWord("lolo");
		assertTrue("",game.existingword("lolo")==true);
		assertFalse("",game.existingword("lolo")!=true);
	}
	@Test
	public void testcreationmot(){
		game.setActuel(players.get(0));
		p1.getMots().get(0).setWord("lolo");
		game.nouveaumot("lolo");
		assertTrue("",game.getActuel().getMots().get(1).getWord()!="lolo");
		assertFalse("",game.getActuel().getMots().get(1).getWord()=="lolo");
	}
	@Test
	public void testfinishgame(){
		p1.getMots().get(9).setWord("lolo");
		game.jeuGagner();
		assertTrue("",game.getEventAction().get(9)==true);
		assertFalse("",game.getEventAction().get(9)!=true);
	}
	/*@Test
	public void testrun(){
		p1.getMots().get(9).setWord("");
		game.getSemaphore().release();
		game.run();
		p1.getMots().get(9).setWord("la");
		assertTrue("",game.getEventAction().get(9)==true);
		assertFalse("",game.getEventAction().get(9)!=true);
		
		
	}*/
	
	
}
