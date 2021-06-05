package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMondeIG;

/**
 * La classe EcouteurDragnDropPanneau.
 */
public class EcouteurDragnDropPanneau implements EventHandler<DragEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * La Vue.
     */
    VueMondeIG vue;

    /**
     * Instancie un EcouteurDragnDropPanneau.
     *
     * @param monde le monde
     * @param vue   la vue
     */
    public EcouteurDragnDropPanneau(MondeIG monde, VueMondeIG vue) {
        this.monde = monde;
        this.vue = vue;
    }

    @Override
    public void handle(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }
}
