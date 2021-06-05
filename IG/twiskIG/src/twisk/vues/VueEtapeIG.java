package twisk.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import twisk.ecouteurs.EcouteurDragnDropActivite;
import twisk.ecouteurs.EcouteurEtape;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe VueEtapeIG.
 */
public abstract class VueEtapeIG extends VBox implements Observateur {

    private final MondeIG monde;
    private final EtapeIG etape;

    /**
     * Instancie une VueEtapeIG.
     *
     * @param monde le monde
     * @param etape l'étape
     */
    VueEtapeIG(MondeIG monde, EtapeIG etape) {
        setPrefWidth(etape.getLargeur());
        setPrefHeight(etape.getHauteur());
        setPadding(new Insets(5, 5, 5, 5));
        this.monde = monde;
        this.etape = etape;
        setId(getEtape().getIdentifiant());
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
        if (getEtape().isSelect()) {
            setBorder(new Border(new BorderStroke(Color.STEELBLUE,
                    BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
        }

        // Gestion du voyant de l'entrée.
        Button entree = new Button();
        entree.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/twisk/ressources/images/entree.png"), 15, 15, true, true)));
        entree.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");
        entree.setDisable(getEtape().isNotEntree());

        // Gestion du voyant de la sortie.
        Button sortie = new Button();
        sortie.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png"), 15, 15, true, true)));
        sortie.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");
        sortie.setDisable(getEtape().isNotSortie());

        Label titre = new Label(this.etape.getNom());
        HBox nom = new HBox();
        nom.getChildren().addAll(titre, entree, sortie);
        nom.setAlignment(Pos.CENTER);
        getChildren().add(nom);
        relocate(getEtape().getPosX(), getEtape().getPosY());
        setOnMouseClicked(new EcouteurEtape(getMonde(), getEtape()));
        setOnDragDetected(new EcouteurDragnDropActivite(monde, this));
    }

    private MondeIG getMonde() {
        return monde;
    }

    public EtapeIG getEtape() {
        return etape;
    }
}
