package frac;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args){
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,500);
		fenetre.setLocationRelativeTo(null);
		
		fenetre.setContentPane(new Panneau());
		
		fenetre.setVisible(true);

	}
	
	
}
