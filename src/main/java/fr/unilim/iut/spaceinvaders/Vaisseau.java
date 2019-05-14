package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	int longueur;
	int hauteur;
	int x;
	int y;

	public Vaisseau(int x, int y) {
		this.longueur = 1;
		this.hauteur =1;
		this.x = x;
		this.y = y;
	}

	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.x = x;
		this.y = y;
	}

	public boolean occupeLaPosition(int x, int y) {
		return (this.x==x) && (this.y==y);
	}

	public void seDeplacerVersLaDroite() {
		this.x = this.x + 1 ;
		
	}
	
	public void seDeplacerVersLaGauche() {
		this.x = this.x - 1 ;
	}
	
	public int abscisse() {
		return this.x;
	}


	
}
