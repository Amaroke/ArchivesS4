package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * La classe EcouteurPointDeControle.
 */
public class EcouteurPointDeControle implements EventHandler<MouseEvent> {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * Le Point de controle.
     */
    PointDeControleIG pointDeControleIG;

    /**
     * Instancie un EcouteurPointDeControle.
     *
     * @param monde             le monde
     * @param pointDeControleIG le point de controle
     */
    public EcouteurPointDeControle(MondeIG monde, PointDeControleIG pointDeControleIG) {
        this.monde = monde;
        this.pointDeControleIG = pointDeControleIG;
    }

    @Override
    public void handle(MouseEvent e) {
        // Si il y a déjà un point sélectionné, je trace un arc.
        if (getMonde().getPointSelectionne() != null) {
            try {
                getMonde().ajouter(getMonde().getPointSelectionne(), getPointDeControleIG());
            } catch (TwiskException t) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de création");
                alert.setHeaderText("Erreur");
                alert.setContentText(t.getMessage());
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(3));
                p.setOnFinished(ev -> alert.close());
                p.play();
            }
            getMonde().setPointSelectionne(null);
        } else {
            getMonde().setPointSelectionne(getPointDeControleIG());
        }
        getMonde().notifierObservateur();
    }

    private MondeIG getMonde() {
        return monde;
    }

    private PointDeControleIG getPointDeControleIG() {
        return pointDeControleIG;
    }
}