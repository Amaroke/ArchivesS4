package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exceptions.TwiskExceptionDelai;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Iterator;
import java.util.Optional;

/**
 * La classe EcouteurEcart.
 */
public class EcouteurEcart implements EventHandler<ActionEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;

    /**
     * Instancie un EcouteurEcart.
     *
     * @param monde le monde
     */
    public EcouteurEcart(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Iterator<EtapeIG> it = getMonde().iterator();
        TextInputDialog saisie = new TextInputDialog();
        saisie.setTitle("Modifier écart");
        saisie.setHeaderText("Entrer un nouveau écart (entre 0 et le délai) :");
        saisie.setContentText("Écart :");
        Optional<String> result = saisie.showAndWait();
        result.ifPresent(ecart -> {
            while (it.hasNext()) {
                EtapeIG e = it.next();
                if (e.isSelect()) {
                    try {
                        e.setEcart(Integer.parseInt(ecart));
                    } catch (NumberFormatException | TwiskExceptionDelai t) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Erreur");
                        alert.setContentText(t.getMessage());
                        alert.show();
                        PauseTransition p = new PauseTransition(Duration.seconds(3));
                        p.setOnFinished(ev -> alert.close());
                        p.play();
                    }

                }
            }
        });
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }
}
