package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Iterator;
import java.util.Optional;

/**
 * La classe EcouteurRenommer.
 */
public class EcouteurRenommer implements EventHandler<ActionEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;

    /**
     * Instancie un EcouteurRenommer.
     *
     * @param monde le monde
     */
    public EcouteurRenommer(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Iterator<EtapeIG> it = getMonde().iterator();
        TextInputDialog saisie = new TextInputDialog();
        saisie.setTitle("Renommer");
        saisie.setHeaderText("Entrer un nouveau nom :");
        saisie.setContentText("Nom :");
        Optional<String> result = saisie.showAndWait();
        result.ifPresent(name -> {
            while (it.hasNext()) {
                EtapeIG e = it.next();
                if (e.isSelect()) {
                    e.setNom(name);
                    e.setSelect(false);
                }
            }
        });
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }
}
