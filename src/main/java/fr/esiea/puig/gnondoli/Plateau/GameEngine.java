package fr.esiea.puig.gnondoli.Plateau;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.esiea.puig.gnondoli.Joueurs.Player;
import fr.esiea.puig.gnondoli.Words.Word;
import fr.esiea.puig.gnondoli.Words.WordWrite;
public class GameEngine {
	final int NOMBRE_DE_MOTS_POUR_GAGNER = 10;
	private List<Player> players;
	private Player currentPlayer;
	private LettresPlateau lettersplate;
	private PlayerIterator<Player> tours;
	private List<Boolean> eventAction;
	
	public GameEngine(List<Player> players,LettresPlateau plate) {
		this.players = players;
		this.lettersplate=plate;
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
	 * 9 -> jeu fini
	 * 
	 * 
	 * */
	
	public void run(){
		players.forEach(theplayer -> {
			System.out.println(theplayer.getMesMots().size());
		});
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
		players.forEach(those ->{
			System.out.println("liste de mot de : " + those.getName());
			those.getMesMots().forEach(that -> {
				System.out.println(that.getWordPlayer());
			});
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
				if(word.getWordPlayer() == wordused){
					getEventAction().set(2,true);
					return;
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
		if(word.NouveauWord()==true && verificationMotExisteDeja(word.getWordwrite())==false){
			getCurrentPlayer().getMesMots().get(getCurrentPlayer().ActualWord()).setWordPlayer(word.getWordwrite());;
			lettersplate.setCommunPot(word.getListCache());
			getCurrentPlayer().Piocher();
			printAll();
		}
	}
	
	

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
	
	

}
