package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;

/**
 * La classe EcouteurDragnDropActivite.
 */
public class EcouteurDragnDropActivite implements EventHandler<MouseEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * La Vue.
     */
    VueEtapeIG vue;

    /**
     * Instancie un EcouteurDragnDropActivite.
     *
     * @param monde le monde
     * @param vue   la vue
     */
    public EcouteurDragnDropActivite(MondeIG monde, VueEtapeIG vue) {
        this.monde = monde;
        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard db = getVue().startDragAndDrop(TransferMode.MOVE);
        WritableImage snapshot = getVue().snapshot(new SnapshotParameters(), null);
        db.setDragView(snapshot);
        ClipboardContent content = new ClipboardContent();
        content.putString(getVue().getId());
        db.setContent(content);
        mouseEvent.consume();
    }

    private VueEtapeIG getVue() {
        return vue;
    }
}