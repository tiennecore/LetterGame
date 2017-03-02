package fr.esiea.XIE.RAVI.Plateau;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import fr.esiea.XIE.RAVI.Joueurs.Player;
import fr.esiea.XIE.RAVI.Words.WordWrite;


public class GameEngine {
	final int NOMBRE_DE_MOTS_POUR_GAGNER = 10;
	private List<Player> players;
	private Player currentPlayer;
	private LettresPlateau lettersplate;
	private PlayerIterator<Player> tours;
	private List<Boolean> eventAction;
	private Semaphore semaphore ;
	private String motsecrits ;
	private WordWrite MotInitial;
	
	public GameEngine(List<Player> players,LettresPlateau plate) {
		this.players = players;
		this.lettersplate=plate;
		this.motsecrits="";
		this.MotInitial= new WordWrite(lettersplate.getCommunPot(),"");
		this.setSemaphore(new Semaphore(0)) ;
		this.setEventAction(new ArrayList<Boolean>(Arrays.asList(new Boolean[10])));
		Collections.fill(getEventAction(), Boolean.FALSE);
		this.setTours(new PlayerIterator<Player>(players));
		this.setCurrentPlayer((Player) getTours().next());
	}
	/*liste de boolean
	 * 0 -> pause du jeux pour piocher les cartes
	 * 1 -> pour le premier tour initier le player qui commence
	 * 2 -> verification du d'éxistance du mot dans les liste de player
	 * 3 -> actionner la création du nouveau mot
	 * 4 -> action modifier un mot
	 * 5 -> valider le mot a modifier
	 * 6 -> 
	 * 9 -> jeu fini
	 * 
	 * 
	 * */
	
	public void run(){
		while (!isGameFinish() ){
			if(getEventAction().get(0)==false){
				
				if ( getEventAction().get(1)==false){
					players.forEach( elem -> elem.Piocher());
					players.forEach( elem -> elem.Piocher());
					getEventAction().set(1, true);
					getEventAction().set(0, true);
					if (Init()==false){
						setCurrentPlayer(getTours().next());
	 		 		}
	 		 	}
	 		 	else{
	 		 		getEventAction().set(0, true);
	 		 		setCurrentPlayer(getTours().next());
	 		 		players.forEach( elem -> elem.Piocher());
	 		 	}
				printAll();
	        }
			else{
				try {
					getSemaphore().acquire() ;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }
	}
	
	//verification d'un gagnant

	private boolean isGameFinish() {
		
		players.forEach( player ->{
			//verifie la taille de la liste mot 
			player.getMesMots().forEach(mot -> {
				if(mot.getWordPlayer()!="" ){
					getEventAction().set(9, true);
					
				}
				else{
					getEventAction().set(9, false);
					return;
				}
			});
		});
		return getEventAction().get(9);
	}
	
	//determination du premier joueur
	
	private boolean Init() {
		List<Integer> val=new ArrayList<Integer>();
		int lettermin=25;
		int firstplayer=0;
		for(int i=0;i<players.size();i++){
			val.add(players.get(i).getRandomPlayer());
			System.out.println(players.get(i).getName()+ " : " +(val.get(i)));
		}
		for(int i=0;i<val.size();i++){
			if(val.get(i)<lettermin){
				lettermin=val.get(i);
				firstplayer=i;
			}
			
		}
		System.out.println(players.get(firstplayer).getName() +" commence ");
		if (firstplayer==0){
			return true;
		}
		return false;
		
	}
	
	//afficher les valeurs
	public void printAll(){
		clearConsole();
		System.out.println(getCurrentPlayer().getName() + " joue :");
		System.out.println("liste de lettre : ");
		String lettres="";
		for(int elem=0;elem<lettersplate.getCommunPot().size();elem++){
			lettres+= lettersplate.getCommunPot().get(elem) +", ";
		  }
		System.out.println(lettres);
		motsecrits="";
		players.forEach(those ->{
			System.out.println("liste de mot de : " + those.getName());
			those.getMesMots().forEach(that -> {
				if( that.getWordPlayer()!= ""){
					motsecrits+= that.getWordPlayer()+ ", ";
				}
			});
			System.out.println(motsecrits);
		});
	}
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	
	public boolean verificationMotExisteDeja(String wordused){
		getEventAction().set(2, false);
		
		players.forEach(player -> {
			player.getMesMots().forEach(word ->{
				if(word.getWordPlayer() != wordused){					
				}else{
					System.out.println("passé");
					getEventAction().set(2,true);
				}
			});
		});
		if(getEventAction().get(2)==true ){
			System.out.println("le mot existe déjà");
		}
		return getEventAction().get(2);
	}
	
	public void nouveaumotused(String wordused) {
		WordWrite word = new WordWrite(lettersplate.getCommunPot(),wordused);
		if( verificationMotExisteDeja(word.getWordwrite())==false && word.NouveauWord()==true){
			getCurrentPlayer().getMesMots().get(getCurrentPlayer().ActualWord()).setWordPlayer(word.getWordwrite());;
			lettersplate.setCommunPot(word.getListCache());
			getCurrentPlayer().Piocher();
			printAll();
		}
	}
	// en redaction
	/*
	public void modifiermot(String wordused){
		if(getEventAction().get(5)==false){
			 MotInitial.setWordwrite(wordused);
			 MotInitial.ChangeWordToListChar();
			getCurrentPlayer().getMesMots().forEach(mot -> {
				if(mot.getWordPlayer()==wordused){
					getEventAction().set(5, true);
					return;
				}
			});
		}
		else{
			WordWrite MotModifier = new WordWrite(lettersplate.getCommunPot(),wordused);
			MotModifier.ChangeWordToListChar();
			MotModifier.setMycharCache(MotModifier.getMychar());
			int taille=0;
			for(int i=0; i<MotModifier.getMychar().length;i++){
				for(int j=0; j<MotModifier.getMychar().length;j++){
					if(MotModifier.getMycharCache()[i] == MotInitial.getMychar()[j])
					taille++;
					
					
				}
			}
			if(taille==MotInitial.getMychar().length){
				for (int i=0; i<MotModifier.getMychar().length;i++){
					for (int j=0; j<MotInitial.getMychar().length;j++){
						if(val.get(j))
					}
				}
			}
			else{
				System.out.println("vous n'avez pas mis toutes les lettres dans le mot");
			}
			
		}
	}*/
	

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public PlayerIterator<Player> getTours() {
		return tours;
	}

	public void setTours(PlayerIterator<Player> tours) {
		this.tours = tours;
	}

	public List<Boolean> getEventAction() {
		return eventAction;
	}

	public void setEventAction(List<Boolean> eventAction) {
		this.eventAction = eventAction;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	

}
