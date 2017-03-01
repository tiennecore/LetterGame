package fr.esiea.puig.gnondoli.Front;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.esiea.puig.gnondoli.Joueurs.Player;
import fr.esiea.puig.gnondoli.Plateau.GameEngine;
import fr.esiea.puig.gnondoli.Plateau.LettresPlateau;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;


public class Fenetre implements IFrame{

	private JFrame frame;
	private String printletter;
	LettresPlateau potcommun=new LettresPlateau();
	Player p1=new Player(potcommun,"P1");
	Player p2=new Player(potcommun,"P2");
	List<Player> players = Arrays.asList(p1,p2);
	GameEngine OrgaJeu=new GameEngine(players,potcommun);
	
	JLabel potcommuntext=new JLabel("liste de lettre:");	
	JLabel textplayer1=new JLabel();
	JLabel action=new JLabel();
	JLabel reponse=new JLabel();
	JLabel textplayer2=new JLabel();
	JLabel currentuplayer=new JLabel();
	JTextArea motecrit=new JTextArea();
	
	
	public Fenetre() {
		
		this.frame = new JFrame();
		this.frame.setTitle("Scrable"); 
		this.frame.setSize(600,300); 
		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false); 
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildContentPane();
		OrgaJeu.run();
	}
	
	
	private void buildContentPane() {
		
		JPanel pan = new JPanel();
		pan.setBackground(Color.gray);
		GridLayout position = new GridLayout(2, 4, 20, 20);
		pan.setLayout(position);
		
		JButton passer=new JButton("Passer");
		passer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  OrgaJeu.getEventAction().set(0,false);
		    	  OrgaJeu.getEventAction().set(3,false);

		      }
		    });
		
		JButton nouveau=new JButton("nouveau");
		nouveau.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  OrgaJeu.getEventAction().set(3,true);
		        
		      }
		    });
		
		JButton voler=new JButton("voler");
		voler.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        
		      }
		    });
		
		JButton modifier=new JButton("modifier");
		modifier.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        
		      }
		    });
		
		JButton valider=new JButton("ok");
		valider.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){	
		    	  if(OrgaJeu.getEventAction().get(3)==true){
		    		  OrgaJeu.nouveaumotused(motecrit.getText());
		    		  OrgaJeu.getEventAction().set(3,false);
		    	  }
		        
		      }
		    });
		
		pan.add(passer);
		pan.add(nouveau);
		pan.add(voler);
		pan.add(modifier);
		pan.add(action);
		pan.add(motecrit);
		pan.add(valider);
		
		
		frame.setContentPane(pan);
	}

	@Override
	public void printListOfLetter() {
		printletter="liste de lettre:";
	    for(int i=0;i<potcommun.getCommunPot().size();i++){
			printletter+= potcommun.getCommunPot().get(i) +", " ;
			System.out.println(printletter);
		}
	    potcommuntext.setText(printletter);
	}

}
