package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe EcouteurEtape.
 */
public class EcouteurEtape implements EventHandler<MouseEvent> {

    private final MondeIG monde;
    private final EtapeIG etape;

    /**
     * Instancie un EcouteurEtape.
     *
     * @param monde le monde
     * @param etape l'étape
     */
    public EcouteurEtape(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        this.etape = etape;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        getEtape().setSelect(true);
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }

    private EtapeIG getEtape() {
        return etape;
    }
}
