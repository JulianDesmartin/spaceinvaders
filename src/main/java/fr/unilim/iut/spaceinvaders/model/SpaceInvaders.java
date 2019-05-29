package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.*;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;

public class SpaceInvaders implements Jeu{
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	List<Envahisseur> envahisseur;
	Direction directionEnvahisseurDeBase = Direction.DROITE;
	public boolean etatPartie = false;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.envahisseur = new ArrayList<Envahisseur>();
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

    private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
				marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
				marque = Constante.MARQUE_ENVAHISSEUR;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // element du jeu 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}

	public boolean aUnMissile() {
		return missile != null;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		boolean envahisseurTrouve = false;
		if (this.aUnEnvahisseur()) {
			int i = 0;
			while (i<envahisseur.size() && !envahisseurTrouve) {
				envahisseurTrouve = envahisseur.get(i).occupeLaPosition(x, y);
				i++;
			}
		}
		return envahisseurTrouve;
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}


	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Deplacement
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}
	
	
	public void deplacerEnvahisseurEnFonctionDeSaDirection() {
		
		for (int i = 0; i < envahisseur.size();i++) {
			
		}
		
		for (int i = 0; i < envahisseur.size();i++) {
			this.verifierSensDeDeplacement(i);
			if (envahisseur.get(i).getSensDeDeplacement().equals(Direction.DROITE)) {
				this.deplacerEnvahisseurVersLaDroite();
			}
			else if (envahisseur.get(i).getSensDeDeplacement().equals(Direction.GAUCHE)){
				this.deplacerEnvahisseurVersLaGauche();
			}
		}
		
	}
	
	
	public void deplacerEnvahisseurVersLaDroite() {
		for(int i = 0; i <envahisseur.size();i++) {
			if (envahisseur.get(i).abscisseLaPlusADroite() < (longueur - 1)) {
				envahisseur.get(i).deplacerHorizontalementVers(Direction.DROITE);
				if (!estDansEspaceJeu(envahisseur.get(i).abscisseLaPlusADroite(), envahisseur.get(i).ordonneeLaPlusHaute())) {
					envahisseur.get(i).positionner(longueur - envahisseur.get(i).longueur(), envahisseur.get(i).ordonneeLaPlusHaute());
				}
			}
		}
	}

	public void deplacerEnvahisseurVersLaGauche() {
		for(int i = 0; i <envahisseur.size();i++) {
			if (0 < envahisseur.get(i).abscisseLaPlusAGauche())
				envahisseur.get(i).deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceJeu(envahisseur.get(i).abscisseLaPlusAGauche(), envahisseur.get(i).ordonneeLaPlusHaute())) {
				envahisseur.get(i).positionner(0, envahisseur.get(i).ordonneeLaPlusHaute());
			}
		}
	}
	
	
	public void deplacerMissile() {
		if (this.aUnMissile()) {
			this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
			if (!estDansEspaceJeu(this.missile.abscisseLaPlusAGauche(),missile.ordonneeLaPlusBasse())) {
				this.missile = null;
			}
			if (this.aUnEnvahisseur()) {
				for (int i = 0; i < envahisseur.size(); i++) {
					if (this.aUnMissile() && Collision.detecterCollision(envahisseur.get(i), this.missile)) {
						this.envahisseur.set(i,null);
						if (envahisseur.isEmpty()) {
							terminerJeux();
						}
					}
				}
			}
		}
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Positionenement
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}
    
    
    public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		
 		int x = position.abscisse();
 		int y = position.ordonnee();
 		
 		if (!estDansEspaceJeu(x, y))
 			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

 		int longueurEnvahisseur = dimension.longueur();
 		int hauteurEnvahisseur = dimension.hauteur();
 		Direction direction = directionEnvahisseurDeBase;
 		
 		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
 			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
 		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
 			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");
 		if (dimension != null && position != null && !(vitesse == 0) && direction != null) {
 			envahisseur.add(new Envahisseur(dimension,position,vitesse,direction));
 		}
 	}
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	
	public Missile recupererMissile() {
		return this.missile;
	}

	public List<Envahisseur> recupererEnvahisseur() {
		return this.envahisseur;
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Action
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	
	
	 public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
			
		   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
     }
	 
	public void verifierSensDeDeplacement(int i) {
		if (this.aUnEnvahisseur()) {
			if (limiteEspaceDeJeuAteinte(i)) {
				for (int j = 0; j < envahisseur.size(); j++)
				this.envahisseur.get(j).ChangerDeSensDeDeplacement();
			}
		}
	}
	
	public boolean limiteEspaceDeJeuAteinte(int i) {
		if (0 >= envahisseur.get(i).abscisseLaPlusAGauche()){
			return true;
		}
		if (envahisseur.get(i).abscisseLaPlusADroite() >= (longueur - 1)) {
			return true;
		}
		return false;
	}
	/*
	public void envahisseurTouche(int i) {
		envahisseur.get(i) = null;
	}*/
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Initialisation / evolution
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void initialiserJeu() {
	    Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
	    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
	    
	    Position positionEnvahisseurNumero1 = new Position(this.longueur/5,this.hauteur/5);
	    Position positionEnvahisseurNumero2 = new Position((this.longueur/5)*2,this.hauteur/5);
	    Position positionEnvahisseurNumero3 = new Position((this.longueur/5)*3,this.hauteur/5);
	    Position positionEnvahisseurNumero4 = new Position((this.longueur/5)*4,this.hauteur/5);
	    //Position positionEnvahisseurNumero5 = new Position((this.longueur/5)*5,this.hauteur/5);
	    
	    Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
	    
	    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	    positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseurNumero1, Constante.ENVAHISSEUR_VITESSE);
	    positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseurNumero2, Constante.ENVAHISSEUR_VITESSE);
	    positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseurNumero3, Constante.ENVAHISSEUR_VITESSE);
	    positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseurNumero4, Constante.ENVAHISSEUR_VITESSE);
	    //positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseurNumero5, Constante.ENVAHISSEUR_VITESSE);

    }
    
	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir && !this.aUnMissile()) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}
		
		if (this.aUnMissile()) {
			this.deplacerMissile();
		}
		
		if (this.aUnEnvahisseur()) {
			deplacerEnvahisseurEnFonctionDeSaDirection();
		}
		
	}

	public void terminerJeux() {
		this.envahisseur = null;
		this.missile = null;
		this.etatPartie = true;
	}
	

    
    @Override
    public boolean etreFini() {
       return etatPartie; 
    }
    
    
    
}