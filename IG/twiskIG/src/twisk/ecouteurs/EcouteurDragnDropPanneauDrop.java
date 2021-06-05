package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;
import twisk.vues.VueMondeIG;

/**
 * La classe EcouteurDragnDropPanneauDrop.
 */
public class EcouteurDragnDropPanneauDrop implements EventHandler<DragEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * La Vue.
     */
    VueMondeIG vue;

    /**
     * Instancie un EcouteurDragnDropPanneauDrop.
     *
     * @param monde le monde
     * @param vue   la vue
     */
    public EcouteurDragnDropPanneauDrop(MondeIG monde, VueMondeIG vue) {
        this.monde = monde;
        this.vue = vue;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasString()) {
            String nodeID = dragboard.getString();
            VueEtapeIG vueEtape = (VueEtapeIG) getVue().lookup("#" + nodeID);
            vueEtape.getEtape().setPosX((int) dragEvent.getX());
            vueEtape.getEtape().setPosY((int) dragEvent.getY());
            vueEtape.getEtape().resetPdc();
            vueEtape.relocate((int) dragEvent.getX(), (int) dragEvent.getY());
        }
        dragEvent.consume();
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }

    private VueMondeIG getVue() {
        return vue;
    }
}
