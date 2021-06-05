package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Iterator;

/**
 * La classe EcouteurSupprimerSelection.
 */
public class EcouteurSupprimerSelection implements EventHandler<ActionEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;

    /**
     * Instancie un EcouteurSupprimerSelection.
     *
     * @param monde le monde
     */
    public EcouteurSupprimerSelection(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Iterator<EtapeIG> it = getMonde().iterator();
        Iterator<ArcIG> itArc = getMonde().iteratorArc();
        while (itArc.hasNext()) {
            ArcIG a = itArc.next();
            if (a.isSelect() || a.getPt2().getEtape().isSelect() || a.getPt1().getEtape().isSelect()) {
                itArc.remove();
            }
        }
        while (it.hasNext()) {
            EtapeIG e = it.next();
            if (e.isSelect()) {
                e.setSelect(false);
                e.suppPdc();
                it.remove();
            }
        }

        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return this.monde;
    }
}
