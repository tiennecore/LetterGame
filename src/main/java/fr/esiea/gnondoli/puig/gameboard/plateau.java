package fr.esiea.gnondoli.puig.gameboard;

import java.util.ArrayList;
import java.util.List;

public class plateau {
	
	private List<Character> CommunPot ;
	
	public plateau() {
		this.setCommunPot(new ArrayList<Character>());
		
	}
	
	public String listToSTring(){
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		
		for(int i=0;i<CommunPot.size();i++){
			if(i==20||i==40||i==60||i==80){
				sb.append("<br>");
			}
			sb.append(CommunPot.get(i));
		}
		sb.append("<p></html>");
		
		return sb.toString();
	}

	public List<Character> getCommunPot() {
		return CommunPot;
	}

	public void setCommunPot(List<Character> communPot) {
		CommunPot = communPot;
	}

}
