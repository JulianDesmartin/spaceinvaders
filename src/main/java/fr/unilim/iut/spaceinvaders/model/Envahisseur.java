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
			this.sensDeDeplacement = Direction.GAUCHE;
		}
		else if (this.sensDeDeplacement.equals(Direction.GAUCHE)){
			this.sensDeDeplacement = Direction.DROITE;
		}
	}

	
}
