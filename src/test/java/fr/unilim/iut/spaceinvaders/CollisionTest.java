package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.Collision;

public class CollisionTest {

	private Collision collision;

    @Before
    public void initialisation() {
	    collision = new Collision();
    }
	
	@Test
	public void test_MissileVaisseauAToucheEnvahisseurBasDroite_Colision() {
		
		Envahisseur envahisseur = new Envahisseur(new Dimension(7, 2), new Position(5, 2),1, Direction.DROITE);
		Missile missile = new Missile(new Dimension(1, 2),new Position(11, 3), 1);

		
		 assertTrue(Collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void test_MissileVaisseauAToucheEnvahisseurHautDroite_Colision() {
		
		Envahisseur envahisseur = new Envahisseur(new Dimension(7, 2), new Position(5, 2),1, Direction.DROITE);
		Missile missile = new Missile(new Dimension(1, 2),new Position(11, 2), 1);

		
		 assertTrue(Collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void test_MissileVaisseauAToucheEnvahisseurHautGauche_Colision() {
		
		Envahisseur envahisseur = new Envahisseur(new Dimension(7, 2), new Position(5, 2),1, Direction.DROITE);
		Missile missile = new Missile(new Dimension(1, 2),new Position(5, 2), 2);

		
		 assertTrue(Collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void test_MissileVaisseauAToucheEnvahisseurBasGauche_Colision() {
		
		Envahisseur envahisseur = new Envahisseur(new Dimension(7, 2), new Position(5, 2),1, Direction.DROITE);
		Missile missile = new Missile(new Dimension(1, 2),new Position(5, 3), 2);

		
		 assertTrue(Collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void test_MissileVaisseauARateEnvahisseur_PasColision() {
		
		Envahisseur envahisseur = new Envahisseur(new Dimension(7, 2), new Position(5, 2),1, Direction.DROITE);
		Missile missile = new Missile(new Dimension(3, 2),new Position(1, 2), 2);

		
		 assertFalse(Collision.detecterCollision(envahisseur,missile));
	}
	


}
