package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe EcouteurSortie.
 */
public class EcouteurSortie implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Instancie un EcouteurSortie.
     *
     * @param monde le monde
     */
    public EcouteurSortie(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for (EtapeIG e : getMonde()) {
            if (e.isSelect()) {
                e.setSortie(e.isNotSortie());
                e.setSelect(false);
            }
        }
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }
}
