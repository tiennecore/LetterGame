package fr.esiea.gnondoli.puig.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.word.Word;
import fr.esiea.gnondoli.puig.word.sac;


public class Joueur {
	
	private sac bag;
	private List<Word> mots;
	private String nom;
	
	public Joueur(plateau pot,String Name){
		this.setBag(new sac(pot));
		this.setMots(new ArrayList<Word>());
		for (int i=0;i< 10;i++){
			Word newword=new Word(pot.getCommunPot(),"");
			getMots().add(newword);
		}
		this.setNom(Name);
	}
	
		/*
		 * alphabet
		 * A b c d
		 * E f g h
		 * I j k l m n
		 * O p q r s t
		 * U v w x
		 * Y z
		 * 
		 * on cherche la voyelle ou consonne avec typelettre aleatoire
		 * puis il y un random sur 6 catégories 
		 * si consonne il a un random pour savoir laquel des listes au dessus 
		 * 
		 * */

	public int valrandom() {
		Random r = new Random();
		int val=r.nextInt(5) +0;
		int typelettre=r.nextInt(100);
		if(typelettre<45){
			switch(val) {
			   case 0 :
			      val=1+r.nextInt(2) +0;break;
			   case 1 :
				  val=5+r.nextInt(2) +0;break;
			   case 2 :
				  val=9+r.nextInt(4) +0;break;
			   case 3 :
				  val=15+r.nextInt(4) +0;break;
			   case 4 :
				  val=21+r.nextInt(2) +0;break;
			   case 5 :
				  val=25;break;
			}
		}
		else{
			switch(val) {
			   case 0 :
			      val=0;break;
			   case 1 :
				  val=4;break;
			   case 2 :
				  val=8;break;
			   case 3 :
				  val=14;break;
			   case 4 :
				  val=20;break;
			   case 5 :
				  val=24;break;
			}
		}
		return val;
	}
	
	public int motsuivant() {
		int i=0;
		while(getMots().get(i).getWord()!=("") && i<getMots().size()){
			i++;
		};
		
		return i;
	}
	
	
	public void piocher(){
		
		getBag().getNextLetter(valrandom());
	}

	public sac getBag() {
		return bag;
	}

	public void setBag(sac bag) {
		this.bag = bag;
	}

	public List<Word> getMots() {
		return mots;
	}

	public void setMots(List<Word> mots) {
		this.mots = mots;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	

}
