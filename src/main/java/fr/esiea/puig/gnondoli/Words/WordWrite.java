package fr.esiea.puig.gnondoli.Words;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordWrite implements IWordWrite {
	
	private String wordwrite;
	private Character[] mychar;
	private List<Character> ListCache ;
	private boolean MotVerif;
	
	
	public WordWrite(List<Character> list,String word){
		this.wordwrite=word;
		this.mychar= new Character[wordwrite.length()];
		this.setListCache(new ArrayList<Character>());
		getListCache().addAll(list);
		
		this.MotVerif=false;
		
	}

	public String getWordwrite() {
		return wordwrite;
	}

	public void setWordwrite(String wordwrite) {
		this.wordwrite = wordwrite;
	}
	

	@Override
	public void ChangeWordToListChar() {
		for(int i = 0 ; i < wordwrite.length() ; i ++){
			mychar[i] = (Character) wordwrite.charAt(i);
			}		
	
	}

	@Override
	public boolean MotVerifier() {
		int mycharlenghtester=0;
		int i=0;
		if(mychar.length!=0){
			do {
				int lengh=getListCache().size();
				out:{
					for (int j=0; j< lengh ;j++) 
					{
						if(mychar[i]==getListCache().get(j)){
							(getListCache()).remove(j);
							mycharlenghtester++;
							break out;
						}
					}				
				}
				i++;
			}while(i<mychar.length);
		}
		
			return mychar.length==mycharlenghtester;
	}

	

	@Override
	public boolean dicoVerifier(String MotTest) {
		String chaine = "";
		String fichier = "src/main/resources/dico.txt";
		MotVerif = false;
		// lecture du fichier texte
		try (InputStream ips = new FileInputStream(fichier)) {
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			// test de mots
			do {
				while ((ligne = br.readLine()) != null) {
					chaine = ligne;
					if (chaine.equals(MotTest)) {
						MotVerif = true;
						//System.out.println("le mot existe");
					}
				}
			} while (MotVerif == false);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return MotVerif;
	}
	
	@Override
	public boolean NouveauWord() {
		ChangeWordToListChar();
		if (MotVerifier()== true && dicoVerifier(wordwrite)==true){
				return true;			
		}else{
			return false;
		}
		
	}

	public List<Character> getListCache() {
		return ListCache;
	}

	public void setListCache(List<Character> listCache) {
		ListCache = listCache;
	}
	
	

	
		
	

}
