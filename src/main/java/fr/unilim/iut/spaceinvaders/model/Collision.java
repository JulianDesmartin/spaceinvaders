package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public static boolean detecterCollision(Sprite premierSprite, Sprite deuxiemeSprite) {
		
		if (collisionMissileHautDroit(premierSprite, deuxiemeSprite)) {
			return true;
		}
		if (collisionMissileHautGauche(premierSprite, deuxiemeSprite)) {
			return true;
		}
		if (collisionMissileBasDroit(premierSprite, deuxiemeSprite)) {
			return true;
		}
		if (collisionMissileBasGauche(premierSprite, deuxiemeSprite)) {
			return true;
		}
		return false;
	}

	
	private static boolean collisionMissileHautDroit(Sprite premierSprite, Sprite deuxiemeSprite) {
		
		int OrdonneLaPlusHautePremierSprite = premierSprite.ordonneeLaPlusHaute();
		int OrdonneLaPlusBassePremierSprite = premierSprite.ordonneeLaPlusBasse();
		int OrdonneLaPlusHauteDeuxiemeSprite = deuxiemeSprite.ordonneeLaPlusHaute();
		int AbscisseLaPlusADroitePremierSprite = premierSprite.abscisseLaPlusADroite();
		int AbscisseLaPlusAGauchePremierSprite = premierSprite.abscisseLaPlusAGauche();
		int AbscisseLaPlusADroiteDeuxiemeSprite = deuxiemeSprite.abscisseLaPlusADroite();
		
		if ( (AbscisseLaPlusAGauchePremierSprite <= AbscisseLaPlusADroiteDeuxiemeSprite) && (AbscisseLaPlusADroiteDeuxiemeSprite <= AbscisseLaPlusADroitePremierSprite) ) {
			if ( (OrdonneLaPlusBassePremierSprite <= OrdonneLaPlusHauteDeuxiemeSprite) && (OrdonneLaPlusHauteDeuxiemeSprite <= OrdonneLaPlusHautePremierSprite) ) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	private static boolean collisionMissileHautGauche(Sprite premierSprite, Sprite deuxiemeSprite) {
		
		int OrdonneLaPlusHautePremierSprite = premierSprite.ordonneeLaPlusHaute();
		int OrdonneLaPlusBassePremierSprite = premierSprite.ordonneeLaPlusBasse();
		int OrdonneLaPlusHauteDeuxiemeSprite = deuxiemeSprite.ordonneeLaPlusHaute();
		int AbscisseLaPlusADroitePremierSprite = premierSprite.abscisseLaPlusADroite();
		int AbscisseLaPlusAGauchePremierSprite = premierSprite.abscisseLaPlusAGauche();
		int AbscisseLaPlusAGaucheDeuxiemeSprite = deuxiemeSprite.abscisseLaPlusAGauche();
		
		if ( (AbscisseLaPlusAGauchePremierSprite <= AbscisseLaPlusAGaucheDeuxiemeSprite) && (AbscisseLaPlusAGaucheDeuxiemeSprite <= AbscisseLaPlusADroitePremierSprite) ) {
			if ( (OrdonneLaPlusBassePremierSprite <= OrdonneLaPlusHauteDeuxiemeSprite) && (OrdonneLaPlusHauteDeuxiemeSprite <= OrdonneLaPlusHautePremierSprite) ) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	private static boolean collisionMissileBasDroit(Sprite premierSprite, Sprite deuxiemeSprite) {
		
		int OrdonneLaPlusHautePremierSprite = premierSprite.ordonneeLaPlusHaute();
		int OrdonneLaPlusBassePremierSprite = premierSprite.ordonneeLaPlusBasse();
		int OrdonneLaPlusBasseDeuxiemeSprite = deuxiemeSprite.ordonneeLaPlusBasse();
		int AbscisseLaPlusADroitePremierSprite = premierSprite.abscisseLaPlusADroite();
		int AbscisseLaPlusAGauchePremierSprite = premierSprite.abscisseLaPlusAGauche();
		int AbscisseLaPlusADroiteDeuxiemeSprite = deuxiemeSprite.abscisseLaPlusADroite();
		
		if ( (AbscisseLaPlusAGauchePremierSprite <= AbscisseLaPlusADroiteDeuxiemeSprite) && (AbscisseLaPlusADroiteDeuxiemeSprite <= AbscisseLaPlusADroitePremierSprite) ) {
			if ( (OrdonneLaPlusBassePremierSprite <= OrdonneLaPlusBasseDeuxiemeSprite) && (OrdonneLaPlusBasseDeuxiemeSprite <= OrdonneLaPlusHautePremierSprite) ) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	private static boolean collisionMissileBasGauche(Sprite premierSprite, Sprite deuxiemeSprite) {
		
		int OrdonneLaPlusHautePremierSprite = premierSprite.ordonneeLaPlusHaute();
		int OrdonneLaPlusBassePremierSprite = premierSprite.ordonneeLaPlusBasse();
		int OrdonneLaPlusBasseDeuxiemeSprite = deuxiemeSprite.ordonneeLaPlusBasse();
		int AbscisseLaPlusADroitePremierSprite = premierSprite.abscisseLaPlusADroite();
		int AbscisseLaPlusAGauchePremierSprite = premierSprite.abscisseLaPlusAGauche();
		int AbscisseLaPlusAGaucheDeuxiemeSprite = deuxiemeSprite.abscisseLaPlusAGauche();
		
		if ( (AbscisseLaPlusAGauchePremierSprite <= AbscisseLaPlusAGaucheDeuxiemeSprite) && (AbscisseLaPlusAGaucheDeuxiemeSprite <= AbscisseLaPlusADroitePremierSprite) ) {
			if ( (OrdonneLaPlusBassePremierSprite <= OrdonneLaPlusBasseDeuxiemeSprite) && (OrdonneLaPlusBasseDeuxiemeSprite <= OrdonneLaPlusHautePremierSprite) ) {
				return true;
			}
			return false;
		}
		return false;
	}

}
