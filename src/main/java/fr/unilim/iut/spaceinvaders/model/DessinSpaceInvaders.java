package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;


public class DessinSpaceInvaders implements DessinJeu {
	
	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders j) {
		this.jeu = j;
	}


   @Override
   public void dessiner(BufferedImage im) {
	   if (this.jeu.aUnVaisseau()) {
		   Vaisseau vaisseau = this.jeu.recupererVaisseau();
		   this.dessinerUnVaisseau(vaisseau, im);
	   }
	   if (this.jeu.aUnMissile()) {
		   Missile missile = this.jeu.recupererMissile();
			   this.dessinerUnMissile(missile, im);
		   
	   }
	   if (this.jeu.aUnEnvahisseur()) {
		   List<Envahisseur> envahisseur = this.jeu.recupererEnvahisseur();
		   for (int i = 0;i < envahisseur.size();i++) {
			   this.dessinerUnEnvahisseur(envahisseur.get(i), im);
		   }
		   
	   }
	   
	   if (this.jeu.etreFini()) {
		   this.dessinerPartieFinie(im);
	   }
   }

   private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
	   Graphics2D crayon = (Graphics2D) im.getGraphics();

	   crayon.setColor(Color.gray);
	   crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

   }
   
   private void dessinerUnMissile(Missile missile, BufferedImage im) {
	   Graphics2D crayon = (Graphics2D) im.getGraphics();

	   crayon.setColor(Color.blue);
	   crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());

   }
   
   private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
	   Graphics2D crayon = (Graphics2D) im.getGraphics();

	   crayon.setColor(Color.green);
	   crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(), envahisseur.hauteur());

   }
   
	private void dessinerPartieFinie( BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.GRAY);
		crayon.setFont(new Font("TimesRoman", Font.BOLD, 30));
		crayon.drawString("Partie terminer :/", 40 , 100);
		
	}
   
}
