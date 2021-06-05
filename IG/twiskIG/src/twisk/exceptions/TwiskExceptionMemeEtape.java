package twisk.exceptions;

/**
 * La classe TwiskExceptionMemeEtape.
 */
public class TwiskExceptionMemeEtape extends TwiskException {

    /**
     * Instancie une TwiskExceptionMemeEtape.
     */
    public TwiskExceptionMemeEtape() {
        super("Impossible de relier les points d'une même étape !");
    }
}