package fr.esiea.XIE.RAVI.Words;

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
	private Character[] mycharCache;
	
	
	public WordWrite(List<Character> list,String word){
		this.wordwrite=word;
		this.setMychar(new Character[wordwrite.length()]);
		this.setMycharCache(new Character[wordwrite.length()]);
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
			getMychar()[i] = (Character) wordwrite.charAt(i);
			}
		NoAccent();	
	}

	@Override
	public boolean MotVerifier() {
		int mycharlenghtester=0;
		int i=0;
		if(getMychar().length!=0){
			do {
				int lengh=getListCache().size();
				out:{
					for (int j=0; j< lengh ;j++) 
					{
						if(getMychar()[i]==getListCache().get(j)){
							(getListCache()).remove(j);
							mycharlenghtester++;
							break out;
						}
					}				
				}
				i++;
			}while(i<getMychar().length);
		}
		
			return getMychar().length==mycharlenghtester;
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
			while ((ligne = br.readLine()) != null) {
				chaine = ligne;
				if (chaine.equals(MotTest)) {
					MotVerif = true;
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		if (MotVerif==false){
			System.out.println("le mot n'existe pas");
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

	@Override
	public void NoAccent() {
		for( char lettre: getMychar()){
			switch(lettre){
				case 'é' :
					lettre='e';
				case 'è' :
					lettre='e';
				case 'ê' :
					lettre='e';
				case 'û' :
					lettre='u';
				case 'à' :
					lettre='a';
				case 'â' :
					lettre='a';
				case 'æ' :
					lettre='a';
				case 'ç' :
					lettre='c';
				case 'î' :
					lettre='i';
				case 'ô' :
					lettre='o';
				default :
			}
		}
		
	}

	public Character[] getMychar() {
		return mychar;
	}

	public void setMychar(Character[] mychar) {
		this.mychar = mychar;
	}

	public Character[] getMycharCache() {
		return mycharCache;
	}

	public void setMycharCache(Character[] mycharCache) {
		this.mycharCache = mycharCache;
	}
	
	

	
		
	

}
