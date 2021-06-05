package twisk.exceptions;

/**
 * La classe TwiskExceptionArcExistant.
 */
public class TwiskExceptionArcExistant extends TwiskException {

    /**
     * Instancie une TwiskExceptionArcExistant.
     */
    public TwiskExceptionArcExistant() {
        super("L'arc existe déjà !");
    }
}