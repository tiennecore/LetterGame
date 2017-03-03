package fr.esiea.gnondoli.puig.EngineGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.esiea.gnondoli.puig.gameboard.plateau;
import fr.esiea.gnondoli.puig.player.Joueur;

public class Frame {
	private JFrame frame;
	plateau potcommun=new plateau();
	Joueur p1=new Joueur(potcommun,"p1");
	Joueur p2=new Joueur(potcommun,"p2");
	List<Joueur> players = Arrays.asList(p1,p2);
	Jeu game=new Jeu(potcommun,players);
	
	JLabel textpot=new JLabel();	
	JLabel text1=new JLabel();
	JLabel blanc=new JLabel();
	JLabel blanc2=new JLabel();
	JLabel text2=new JLabel();
	JLabel joueuractuel=new JLabel();
	JTextArea wordwrite=new JTextArea();
	
	
	public Frame() {
		
		this.frame = new JFrame();
		this.frame.setTitle("Scrable"); 
		this.frame.setSize(800,600); 
		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildContentPane();
		game.choixJoueur();
		majframe();
		game.run();
	}
	
	
	private void buildContentPane() {
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.gray);
		GridLayout position = new GridLayout(3, 4, 20, 20);
		pan.setLayout(position);
		
		JButton passer=new JButton("Passer son tour");
		passer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  game.getSemaphore().release();
		    	  try {
					game.getSemaphore2().acquire();
					majframe();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 	  

		      }
		    });
		
		JButton nouveau=new JButton("nouveau mot");
		nouveau.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  game.getEventAction().set(0,true);
		        
		      }
		    });
		
		JButton modifier=new JButton("modifier (voler) mot");
		modifier.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  game.getEventAction().set(4,true);
		      }
		    });
		
		JButton valider=new JButton("valider");
		valider.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){	
		    	  if(game.getEventAction().get(0)==true){
		    		  game.nouveaumot(wordwrite.getText());
		    		  game.getEventAction().set(0,false);
		    	  }
		    	  else if(game.getEventAction().get(4)==true){
		    		  game.recupererword(wordwrite.getText());
		    		  game.getEventAction().set(4,false);
				  }
		    	  else if(game.getEventAction().get(5)==true){
		    		  game.modifiermot(wordwrite.getText());
		    		  game.getEventAction().set(5,false);
		    		  
		    	  }
		    	  majframe();
		        
		      }
		    });
		
		pan.add(passer);
		pan.add(nouveau);
		pan.add(modifier);
		pan.add(blanc);
		pan.add(text1);
		pan.add(textpot);
		pan.add(text2);
		pan.add(joueuractuel);
		pan.add(blanc2);
		pan.add(wordwrite);
		pan.add(valider);
		//potcommuntext.setContentType("text/html"); 
		majframe();
		frame.setContentPane(pan);
	}
	
	public void majframe(){
		joueuractuel.setText(game.getActuel().getNom());
		String motplayer1="<html>player 1:<br> ";
		String motplayer2="<html>player 2:<br> ";
		for (int i=0;i<10;i++){
			if(players.get(0).getMots().get(i).getWord()!=""){
				motplayer1+=players.get(0).getMots().get(i).getWord()+"<br> ";
			}
			if(players.get(1).getMots().get(i).getWord()!=""){
				motplayer2+=players.get(1).getMots().get(i).getWord()+"<br> ";
			}
		}
		text1.setText(motplayer1);
		text2.setText(motplayer2);
		
		textpot.setText(potcommun.listToSTring());
	}
	

}
