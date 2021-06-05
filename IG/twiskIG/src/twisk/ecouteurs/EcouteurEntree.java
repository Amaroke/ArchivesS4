package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe EcouteurEntree.
 */
public class EcouteurEntree implements EventHandler<ActionEvent> {

    private final MondeIG monde;

    /**
     * Instancie un EcouteurEntree.
     *
     * @param monde le monde
     */
    public EcouteurEntree(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for (EtapeIG e : getMonde()) {
            if (e.isSelect()) {
                e.setEntree(e.isNotEntree());
                e.setSelect(false);
            }
        }
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }
}
