package fr.unilim.iut.spaceinvaders;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

import static org.junit.Assert.fail;

public class SpaceInvadersTest {
		
	private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
	
	 @Test
	   public void test_AuDebut_JeuSpaceInvaderEstVide() {
		    assertEquals("" + 
		    "...............\n" + 
		    "...............\n" +
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	        }
	 @Test
		public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9), 1);
			assertEquals("" + 
			"...............\n" + 
			"...............\n" +
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			".......V.......\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	    
	    @Test(expected = HorsEspaceJeuException.class)
		public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
		}
	    
	   	@Test
		public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
			SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
				fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
				fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
				fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
				fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
				
		}
		
	    @Test
		public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
	    	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
			assertEquals("" + 
			"...............\n" + 
			"...............\n" +
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			".......VVV.....\n" + 
			".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	    
	    @Test
		public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
				fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
				fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
				
		}
	    
		
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

	        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
	        spaceinvaders.deplacerVaisseauVersLaDroite();
	        assertEquals("" + 
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "..........VVV..\n" + 
	        "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }

	    @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "....VVV........\n" + 
	       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	    
	    @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	    
	    @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	    
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    // Missile
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    @Test
	     public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test(expected = MissileException.class)
		public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		   spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
		}
	    
	    @Test
	    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		   spaceinvaders.deplacerMissile();
		   
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	    
	    @Test
	    public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

	 	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	 	   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
	 	   for (int i = 1; i <=6 ; i++) {
	 		   spaceinvaders.deplacerMissile();
	 	   }
	 	   
	 	   spaceinvaders.deplacerMissile();
	 	   
	        assertEquals("" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        ".....VVVVVVV...\n" + 
	        ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    // Envahisseur
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    @Test
	    public void test_unNouvelleEnvahisseurEstCorrectementPositionneDansEspaceJeu() {

	    	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	 	    spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7,2),new Position(5,2), 1);
	 	   
	        assertEquals("" +
	        "...............\n" + 
	        ".....EEEEEEE...\n" +
	        ".....EEEEEEE...\n" + 
	        "...............\n" +
	        "...............\n" +
	        "...............\n" + 
	        "...............\n" +
	        "...............\n" + 
	        ".....VVVVVVV...\n" + 
	        ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test(expected = HorsEspaceJeuException.class)
		public void test_unNouvelEnvahisseurEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
		}
	    
	   	@Test
		public void test_UnNouvelVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
			SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(15,9), 1);
				fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(-1,9), 1);
				fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
				fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(14,-1), 1);
				fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}
				
		}

	    @Test
		public void test_UnNouvelEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
				fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
				fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
				
		}
	    
	    @Test
	    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

	        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7,2),new Position(5,2),1);
	        spaceinvaders.deplacerEnvahisseurEnFonctionDeSaDirection();
	        assertEquals("" + 
	        "...............\n" + 
	        "......EEEEEEE..\n" +
	        "......EEEEEEE..\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }

	    @Test
	    public void test_EnvahisseurAvance_LimiteDroiteAteinte_ChangeDeDirectionVersLaGauche() {

	       spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7,2),new Position(8,2), 1);
	       spaceinvaders.deplacerEnvahisseurEnFonctionDeSaDirection();
	       assertEquals("" + 
	       "...............\n" + 
	       ".......EEEEEEE.\n" +
	       ".......EEEEEEE.\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    /*
	    @Test
	    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

	        spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7,2),new Position(5,2),1);
	        spaceinvaders.deplacerEnvahisseurEnFonctionDeSaDirection();
	        assertEquals("" + 
	        "...............\n" + 
	        "....EEEEEEE....\n" +
	        "....EEEEEEE....\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" + 
	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
		*/
	    
	    @Test
	    public void test_EnvahisseurAvance_LimiteGaucheAteinte_ChangeDeDirectionVersLaDroite() {

	       spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7,2),new Position(0,2), 1);
	       spaceinvaders.deplacerEnvahisseurEnFonctionDeSaDirection();
	       spaceinvaders.deplacerEnvahisseurEnFonctionDeSaDirection();
	       assertEquals("" + 
	       "...............\n" + 
	       ".EEEEEEE.......\n" +
	       ".EEEEEEE.......\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	    
	    @Test
	    public void test_PlusDEnvahisseur_PartieTerminer() {
	    	
	    	spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(7, 2), new Position(5, 2),1);
	    	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	    	spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
	    	
			spaceinvaders.deplacerMissile();
			spaceinvaders.deplacerMissile();
			spaceinvaders.deplacerMissile();
			spaceinvaders.deplacerMissile();

			assertTrue(spaceinvaders.etatPartie);
			assertTrue(spaceinvaders.etreFini());
	    }
	    
	    
}
