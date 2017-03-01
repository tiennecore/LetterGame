package fr.esiea.puig.gnondoli.Joueurs;


import fr.esiea.puig.gnondoli.Plateau.IActionPlayer;
import fr.esiea.puig.gnondoli.Plateau.LettresPlateau;
import fr.esiea.puig.gnondoli.PotCommun.Bag;
import fr.esiea.puig.gnondoli.Words.IWord;
import fr.esiea.puig.gnondoli.Words.Word;
import fr.esiea.puig.gnondoli.Words.WordWrite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class Player implements IActionPlayer,IWord{
	
	private Bag LettresPlayer;
	private List<Word> MesMots;
	private String Name;
	
	public Player(LettresPlateau pot,String Nom){
		this.LettresPlayer= new Bag(pot);
		this.setMesMots(new ArrayList<Word>());
		for (int i=0;i< 10;i++){
			Word lala;
			getMesMots().add(lala=new Word());
		}
		this.Name=Nom;
	}
	
	//setter & getter du nom du player
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	//taille de la liste du player
	public int getSizeMesMots() {
		return getMesMots().size();
	}
	
	//ajouter une lettre au potcommun
	
	public void Piocher(){
		LettresPlayer.getNextLetter(getRandomPlayer());
	}
	
	

	@Override
	public int ActualWord() {
		int i=0;
		while(getMesMots().get(i).getWordPlayer()!=("") && i<getMesMots().size()){
			i++;
		};
		
		return i;
	}
	
	
	//fonction de word
	@Override
	public void DeleteWord() {
		// TODO Auto-generated method stub
		
	}
	
	//suppression d'un mot de la liste du player
	@Override
	public void DeleteWordPlayer(int PlaceWord) {
		Word ThisWord=getMesMots().get(PlaceWord);
		ThisWord.DeleteWord();
		// TODO Auto-generated method stub
		
	}
	
	//valeur random pour determiner la lettre en int
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

	@Override
	public int getRandomPlayer() {
		Random r = new Random();
		int val=r.nextInt(5) +0;
		int typelettre=r.nextInt(100);
		if(typelettre<55){
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
	

	public List<Word> getMesMots() {
		return MesMots;
	}

	public void setMesMots(List<Word> mesMots) {
		MesMots = mesMots;
	}
		

}
