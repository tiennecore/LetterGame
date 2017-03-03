package fr.esiea.gnondoli.puig.EngineGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.player.Joueur;
import fr.esiea.gnondoli.puig.player.JoueurIterator;
import fr.esiea.gnondoli.puig.word.Word;

public class Jeu {
	private List<Joueur> joueurs;
	private Joueur actuel;
	private plateau potcommun;
	private Semaphore semaphore ;
	private Semaphore semaphore2;
	private Iterator<Joueur> tour;
	private List<Boolean> eventAction;
	private int matchword;
	private Word lastword;
	
	
	public Jeu(plateau pot,List<Joueur> players){
		this.setJoueurs(players);
		this.setPotcommun(pot);
		this.setTour(new JoueurIterator<Joueur>(joueurs));
		this.actuel= getTour().next();
		this.lastword=new Word(potcommun.getCommunPot(),"");
		this.setSemaphore(new Semaphore(0));
		this.setSemaphore2(new Semaphore(0));
		this.setEventAction(new ArrayList<Boolean>(Arrays.asList(new Boolean[10])));
		Collections.fill(getEventAction(), Boolean.FALSE);
	}
	
	public void run(){
		while(getEventAction().get(9)==false){
			try {
				getSemaphore().acquire() ;
				getActuel().piocher();
				getActuel().piocher();
				setActuel(tour.next());
				jeuGagner();
				getSemaphore2().release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void jeuGagner(){
		joueurs.forEach( player ->{
			if(player.getMots().get(9).getWord().length()!=0){
				getEventAction().set(9, true);
			}
		});
	}
	
	public void choixJoueur(){
		int val1,val2;
		val1=getActuel().valrandom();
		setActuel(tour.next());
		val2=getActuel().valrandom();
		if(val1<=val2){
			setActuel(tour.next());
		}
		joueurs.forEach( player ->{
			player.piocher();
			player.piocher();
		});
		
	}
	
	public boolean existingword(String word){
		getEventAction().set(1, false);
		String regex="(["+word+"]+)";
		Pattern regex_pattern = Pattern.compile(regex);
		matchword=0;
		out:
		{
			for(Joueur player: getJoueurs()){
				for(int i=0; i<player.getMots().size();i++){
					Matcher regex_matches = regex_pattern.matcher(player.getMots().get(i).getWord());
					boolean regex_matched = regex_matches.matches();
					
					if(regex_matched){
						getEventAction().set(1,true);
						break out;
					}
					matchword++;
				}
				
			}
		}
		
		return getEventAction().get(1);
	}
	
	public void nouveaumot(String wordused){
		Word nowone=new Word(potcommun.getCommunPot(),wordused);
		if( existingword(wordused)==false && nowone.motVerifier()==true){
			getActuel().getMots().get(getActuel().motsuivant()).setWord(wordused);;
			potcommun.setCommunPot(nowone.getListelettrecache());
			getActuel().piocher();
		}
		
	}
	public void recupererword(String wordused){
		lastword.setWord(wordused);
		getEventAction().set(5, true);
	}
	
	public void modifiermot(String wordused){
		if(getEventAction().get(5)==true){
			if(existingword(wordused)==true){
				Word newone=new Word(potcommun.getCommunPot(),wordused);
				if( newone.motmodifier(lastword)==true ){
					if(matchword<10){
						getJoueurs().get(0).getMots().get(matchword).setWord("");
					}
					else{
						getJoueurs().get(1).getMots().get(matchword-10).setWord("");
					}
					getActuel().getMots().get(getActuel().motsuivant()).setWord(wordused);
					potcommun.setCommunPot(newone.getListelettrecache());
					getActuel().piocher();
					
				}
				
			}
			
		}
	}
	
	
	
	public List<Joueur> getJoueurs() {
		return joueurs;
	}


	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}


	public Joueur getActuel() {
		return actuel;
	}


	public void setActuel(Joueur actuel) {
		this.actuel = actuel;
	}


	public plateau getPotcommun() {
		return potcommun;
	}


	public void setPotcommun(plateau potcommun) {
		this.potcommun = potcommun;
	}


	public Iterator<Joueur> getTour() {
		return tour;
	}


	public void setTour(Iterator<Joueur> tour) {
		this.tour = tour;
	}


	public Semaphore getSemaphore() {
		return semaphore;
	}


	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public List<Boolean> getEventAction() {
		return eventAction;
	}

	public void setEventAction(List<Boolean> eventAction) {
		this.eventAction = eventAction;
	}

	public Semaphore getSemaphore2() {
		return semaphore2;
	}

	public void setSemaphore2(Semaphore semaphore2) {
		this.semaphore2 = semaphore2;
	}

}
