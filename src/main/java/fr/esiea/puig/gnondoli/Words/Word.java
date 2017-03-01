package fr.esiea.puig.gnondoli.Words;

import java.util.logging.Logger;

public class Word implements IWord{
	
	private String WordPlayer;

	public Word(){
		this.setWordPlayer("");
		
	}

	
	@Override
	public int ActualWord() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void DeleteWord() {
		setWordPlayer("");
		
	}

	@Override
	public void DeleteWordPlayer(int PlaceWord) {
		// TODO Auto-generated method stub
		
	}


	public String getWordPlayer() {
		return WordPlayer;
	}


	public void setWordPlayer(String wordPlayer) {
		//Logger.info("mot modifier");
		WordPlayer = wordPlayer;
	}


	

	

}
