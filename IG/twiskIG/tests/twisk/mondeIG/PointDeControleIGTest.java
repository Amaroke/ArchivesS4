package twisk.mondeIG;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointDeControleIGTest {

    ActiviteIG etapeTest;
    PointDeControleIG pointN;
    PointDeControleIG pointO;
    PointDeControleIG pointE;
    PointDeControleIG pointS;

    @Before
    public void init() {
        etapeTest = new ActiviteIG("activiteTest", "1", 100, 100);
        pointN = etapeTest.getPointsDeControle()[0];
        pointO = etapeTest.getPointsDeControle()[1];
        pointE = etapeTest.getPointsDeControle()[2];
        pointS = etapeTest.getPointsDeControle()[3];
    }

    @Test
    public void getPosX() {
        assertEquals(pointN.getPosX(), etapeTest.getPosX() + etapeTest.getLargeur() / 2);
    }

    @Test
    public void getPosY() {
        assertEquals(pointN.getPosY(), etapeTest.getPosY() + etapeTest.getHauteur() - 100);
    }

    @Test
    public void getIdentifiant() {
        assertEquals("1N", pointN.getIdentifiant());
        assertEquals("1O", pointO.getIdentifiant());
        assertEquals("1E", pointE.getIdentifiant());
        assertEquals("1S", pointS.getIdentifiant());
    }

    @Test
    public void getEtape() {
        assertEquals(pointN.getEtape(), etapeTest);
        assertEquals(pointO.getEtape(), etapeTest);
        assertEquals(pointE.getEtape(), etapeTest);
        assertEquals(pointS.getEtape(), etapeTest);
    }
}