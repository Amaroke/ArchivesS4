package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;

/**
 * La classe VueOutils.
 */
public class VueOutils extends TilePane implements Observateur {

    private final Button plus;
    private final MondeIG monde;

    /**
     * Instancie.
     *
     * @param monde le monde
     */
    public VueOutils(MondeIG monde) {
        this.monde = monde;
        plus = new Button();
        getPlus().setOnAction(actionEvent -> getMonde().ajouter("Activite"));
        getPlus().setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/twisk/ressources/images/plus.png"), 50, 50, true, true)));
        getPlus().setStyle("-fx-background-color: transparent;-fx-cursor: hand;");
        getPlus().setTooltip(new Tooltip("Ajouter une étape."));
        this.getChildren().add(getPlus());
    }

    private Button getPlus() {
        return plus;
    }

    private MondeIG getMonde() {
        return monde;
    }

    @Override
    public void reagir() {}
}
