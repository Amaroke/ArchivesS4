package twisk.outils;

/**
 * La classe FabriqueIdentifiant.
 */
public class FabriqueIdentifiant {

    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    /**
     * Le n° de l'étape.
     */
    int noEtape;

    private FabriqueIdentifiant() {
        noEtape = 0;
    }

    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    public String getIdentifiantEtape() {
        noEtape++;
        return String.valueOf(noEtape - 1);
    }
}
