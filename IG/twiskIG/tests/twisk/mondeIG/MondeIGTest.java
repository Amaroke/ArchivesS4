package twisk.mondeIG;

import org.junit.Test;
import twisk.exceptions.TwiskException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MondeIGTest {

    @Test
    public void ajouter() {
        MondeIG monde = new MondeIG();
        assertEquals(monde.getNbEtapes(), 1);
        monde.ajouter("Activite");
        assertEquals(monde.getNbEtapes(), 2);
        monde.ajouter("Activite");
        assertEquals(monde.getNbEtapes(), 3);
        monde.ajouter("Activite");
        assertEquals(monde.getNbEtapes(), 4);
    }

    @Test
    public void ajouterArcs() throws TwiskException {
        MondeIG monde = new MondeIG();
        EtapeIG etape = new ActiviteIG("", "", 1, 1);

        // Même étape
        assertThrows(TwiskException.class, () -> monde.ajouter(new PointDeControleIG(20, 20, "x", etape), new PointDeControleIG(10, 10, "x", etape)));

        // Même point
        assertThrows(TwiskException.class, () -> monde.ajouter(new PointDeControleIG(10, 10, "x", etape), new PointDeControleIG(10, 10, "y", etape)));

        monde.ajouter(new PointDeControleIG(20, 20, "y", etape), new PointDeControleIG(10, 10, "x", etape));
        // Déjà existant
        assertThrows(TwiskException.class, () -> monde.ajouter(new PointDeControleIG(20, 20, "x", etape), new PointDeControleIG(10, 10, "y", etape)));
    }
}