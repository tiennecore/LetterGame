package fr.esiea.gnondoli.puig.word;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word implements IWord{
	
	private String word;
	private List<Character> listelettrecache;
	private List<Character> charactermot;
	private List<Boolean> lettreused;
	private List<Boolean> verif;
	
	public Word(List<Character> listelettre,String motecrit){
		this.setWord(motecrit);
		this.setListelettrecache(listelettre);
		this.setCharactermot(new ArrayList<Character>());
		this.lettreused=(new ArrayList<Boolean>(Arrays.asList(new Boolean[getWord().length()])));
		Collections.fill(lettreused, Boolean.TRUE);
		this.verif=(new ArrayList<Boolean>(Arrays.asList(new Boolean[5])));
		Collections.fill(verif, Boolean.FALSE);
		
	}
	
	public void mottoliste() {
		for(int i = 0 ; i < getWord().length() ; i ++){
			getCharactermot().add((Character) getWord().charAt(i));
			}
	}
	
	public boolean lettreVerifier() {
		int mycharlenghtester=0;
		int i=0;
		int lengh;
		do {
			out:{
				lengh=getListelettrecache().size();
				for (int j=0; j< lengh ;j++) 
				{
					if(getCharactermot().get(i)==getListelettrecache().get(j)){
						getListelettrecache().remove(j);
						mycharlenghtester++;
						break out;
					}
				}				
			}
			i++;
		}while(i<getCharactermot().size());
		
		if(getCharactermot().size()==mycharlenghtester){
			return true;
		}else{
			return false;
		}
		
	}
		
	public boolean dicoVerifier() {
		String fichier = "src/main/resources/dico.txt";
		verif.set(1, false);
		try (InputStream ips = new FileInputStream(fichier)) {
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (ligne.equals(getWord())) {
					verif.set(1, true);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return verif.get(1);
	}
	public String dicoVerifierIA() {
		String fichier = "src/main/resources/dico.txt";
		verif.set(1, false);
		String valdic="";
		String regex="(["+getListelettrecache().toString()+"]+)";
		Pattern regex_pattern = Pattern.compile(regex);
		try (InputStream ips = new FileInputStream(fichier)) {
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			while ((ligne = br.readLine()) != null) {
				Matcher regex_matches = regex_pattern.matcher(ligne);
				boolean regex_matched = regex_matches.matches();
				if (regex_matched) {
					valdic=ligne;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return valdic;
	}
	
	public boolean motVerifier(){
		mottoliste();
		if (dicoVerifier()==true &&lettreVerifier()==true ){
			return true;
		}
		return false;
	}
	
	public boolean motmodifier(Word wordued){
		if (dicoVerifier()==true ){
			mottoliste();
			supprimerlettreancienmot(wordued);
			if (lettreVerifier()==true ){
				return true;
			}
		}
		return false;
	}
	
	public void supprimerlettreancienmot(Word wordused){
			
		wordused.mottoliste();
		int i=0;
		int lengh;
		do {
			String regex="(["+wordused.getCharactermot().get(i).toString()+"]+)";
			Pattern regex_pattern = Pattern.compile(regex);
			out:{
				lengh=getCharactermot().get(i);
				for (int j=0; j<lengh  ;j++) 
				{
					Matcher regex_matches = regex_pattern.matcher(getCharactermot().get(j).toString());
					boolean regex_matched = regex_matches.matches();
					if(regex_matched){
						getCharactermot().remove(j);
						break out;
					}
				}				
			}
			i++;
		}while(i<wordused.getCharactermot().size());
		
	}
	
	
	

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Character> getListelettrecache() {
		return listelettrecache;
	}

	public void setListelettrecache(List<Character> listelettrecache) {
		this.listelettrecache = listelettrecache;
	}

	public List<Character> getCharactermot() {
		return charactermot;
	}

	public void setCharactermot(List<Character> charactermot) {
		this.charactermot = charactermot;
	}

	

}
