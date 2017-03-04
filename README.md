*** Letter Game ***
*********************

> Design patern
* Iterator ( creation d'un iterator pour player)
* Regex( matcher lettres et mots )
* Singleton (classe plateau)
<!-- -->
> Architecture du programme
Les packages sont séparés par :  
*Application(lancement du jeu)
*Enginegame (frame et Application des classes)
*Player( attribution des capacités du joueur)
*Word( classe du mot et attribution des différentes actions du mot)
*plateau ( Singleton de une liste)
  
Chacun des packages comporte une ou deux classe avec parfois une interface.  
Nous n'avons pas eu le temps de finir l'IA car nous avons préférer favoriser la propreté du code.  
Mais l'IA est déjà avancé pour permettre de matcher des mot à partir de la liste du pot commun il ne faut plus qu'attribuer le player p2 comme IA dans la liste de player.  
Nous avons créer un iterator infini qui reviens au début de la liste lorsque  hasnext() est nulle.  
Le player possède une liste de 10 objet word.  
Enginegame possède les fonction de création et de modification de mot dans la liste du player.  
Vu que nous utilisons une fenêtre, nous avons du mettre en place des sémaphore pour faire attendre le programme.  
Nous avons rencontrer un problème au niveau du git et avons du changer de gît dont l'adresse.  
https://github.com/etiennepuig/scrable_PUIG_GNONDOLI - automatic!

execution sudo java -cp target/-0.0.1-SNAPSHOT.jar fr.esiea.gnondoli.puig.App.lancement
