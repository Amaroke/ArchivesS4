package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

/**
 * La classe VueActiviteIG.
 */
public class VueActiviteIG extends VueEtapeIG implements Observateur {

    /**
     * Instancie une VueActiviteIG.
     *
     * @param monde le monde
     * @param etape l'étape
     */
    VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        HBox reservoirClients = new HBox();
        reservoirClients.setStyle("-fx-border-color:STEELBLUE; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        TailleComposants singleton = TailleComposants.getInstance();
        reservoirClients.setPrefSize(singleton.getLongeurVBox(), singleton.getHauteurVBox());
        getChildren().add(reservoirClients);
    }

    public void reagir() {
    }
}
