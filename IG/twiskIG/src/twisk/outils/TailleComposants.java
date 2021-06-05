package twisk.outils;

/**
 * La classe TailleComposants.
 */
public class TailleComposants {

    private static final TailleComposants instance = new TailleComposants();
    /**
     * La Longeur de la VBox.
     */
    int longeurVBox = 175;
    /**
     * La Hauteur de VBox.
     */
    int hauteurVBox = 60;

    public static TailleComposants getInstance() {
        return instance;
    }

    public int getLongeurVBox() {
        return longeurVBox;
    }

    public int getHauteurVBox() {
        return hauteurVBox;
    }
}
