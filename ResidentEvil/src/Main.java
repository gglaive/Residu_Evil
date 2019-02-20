import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * La classe Main permet d'instancier le jeu, et de le lancée. 
 */
class Main {
    public static void main(String args[]){
    	
    //Jframe
    Fenetre fenetre = new Fenetre();
    
    //Jpanel
    Panneau p = new Panneau();
    JPanel south = new JPanel();
    south.setPreferredSize(new Dimension(1000, 400));
    
    p.setPreferredSize(new Dimension(1000, 400));
    //Label
    JLabel l = new JLabel("input here");
    south.add(l);
    
    //Input
    JTextField i = new JTextField();
    i.setPreferredSize(new Dimension(150, 30));
    south.add(i);
    //north.add(p);
    
    fenetre.add(south, BorderLayout.SOUTH);
    fenetre.add(p, BorderLayout.NORTH);
    
    fenetre.setVisible(true);
	
	Game jeu = new Game();
	jeu.play();

    }
}
