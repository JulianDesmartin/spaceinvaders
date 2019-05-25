package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	
	private Direction sensDeDeplacement;
	
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse, Direction sensDeDeplacement) {
		super(dimension, positionOrigine, vitesse);
		this.sensDeDeplacement = sensDeDeplacement;
	}
	
	public Direction getSensDeDeplacement() {
		return this.sensDeDeplacement;
	}
	
	
	public void ChangerDeSensDeDeplacement() {
		if (this.sensDeDeplacement.equals(Direction.DROITE)) {
			sensDeDeplacement = Direction.GAUCHE;
		}
		else if (this.sensDeDeplacement.equals(Direction.GAUCHE)){
			sensDeDeplacement = Direction.DROITE;
		}
	}

	
}
