package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe EcouteurArc.
 */
public class EcouteurArc implements EventHandler<MouseEvent> {

    private final MondeIG monde;
    private final ArcIG arc;

    /**
     * Instancie un EcouteurArc.
     *
     * @param monde le monde
     * @param arc   l'arc
     */
    public EcouteurArc(MondeIG monde, ArcIG arc) {
        this.monde = monde;
        this.arc = arc;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        getArc().setSelect(true);
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }

    private ArcIG getArc() {
        return arc;
    }
}
