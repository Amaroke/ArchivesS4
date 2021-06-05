package twisk.outils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FabriqueIdentifiantTest {

    @Test
    public void getIdentifiantEtape() {
        FabriqueIdentifiant singleton = FabriqueIdentifiant.getInstance();
        String noEtape = singleton.getIdentifiantEtape();
        assertEquals(noEtape, "0");
        String noEtape2 = singleton.getIdentifiantEtape();
        assertEquals(noEtape2, "1");
        String noEtape3 = singleton.getIdentifiantEtape();
        assertEquals(noEtape3, "2");
    }
}