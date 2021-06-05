package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Iterator;

/**
 * La classe EcouteurDesactiverSelection.
 */
public class EcouteurDesactiverSelection implements EventHandler<ActionEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;

    /**
     * Instancie un EcouteurDesactiverSelection.
     *
     * @param monde le monde
     */
    public EcouteurDesactiverSelection(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Iterator<EtapeIG> it = getMonde().iterator();
        Iterator<ArcIG> itArc = getMonde().iteratorArc();
        while (itArc.hasNext()) {
            ArcIG a = itArc.next();
            if (a.isSelect()) {
                a.setSelect(false);
            }
        }
        while (it.hasNext()) {
            EtapeIG e = it.next();
            if (e.isSelect()) {
                e.setSelect(false);
            }
        }
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }
}
