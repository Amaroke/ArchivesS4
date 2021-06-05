package vues;

import chocobar.EcouteurBouton;
import chocobar.Observateur;
import chocobar.Partie;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class PlateauDeJeu extends GridPane implements Observateur {

    private final Button[][] carres;
    private final Partie partie;

    public PlateauDeJeu(Partie partie) {
        super();
        this.partie = partie;
        this.carres = new Button[getPartie().getPlateau().getNbCarresLongueur()][getPartie().getPlateau().getNbCarresLargeur()];

        for (int i = 0; i < carres.length; ++i) {
            for (int j = 0; j < carres[i].length; ++j) {
                // Le Hamburger
                if (i == 0 && j == 0) {
                    Image image = new Image(getClass().getResourceAsStream("/ressources/hamburger.png"), 25, 25, true, true);
                    ImageView icon = new ImageView(image);
                    Button h = new Button();
                    h.setOnAction(new EcouteurBouton(getPartie(), i, j));
                    h.setGraphic(icon);
                    h.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");
                    this.carres[0][0] = h;
                    this.add(h, 0, 0);
                // Les carrés de chocolat
                } else {
                    Image image = new Image(getClass().getResourceAsStream("/ressources/carre.png"), 25, 25, true, true);
                    ImageView icon = new ImageView(image);
                    Button b = new Button();
                    b.setOnAction(new EcouteurBouton(getPartie(), i, j));
                    b.setGraphic(icon);
                    b.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");
                    carres[i][j] = b;
                    this.add(b, i, j);
                }
            }
        }
        getPartie().ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        for (int i = 0; i < carres.length; ++i) {
            for (int j = 0; j < carres[i].length; ++j) {
                // Si manger par le joueur 1
                if (getPartie().getPlateau().getTableau()[i][j] == 1) {
                    carres[i][j].setDisable(true);
                    carres[i][j].setStyle("-fx-background-color: yellow;-fx-cursor: hand;");
                // Si manger par le joueur 2
                } else if (getPartie().getPlateau().getTableau()[i][j] == 2) {
                    carres[i][j].setDisable(true);
                    carres[i][j].setStyle("-fx-background-color: green;-fx-cursor: hand;");
                }
            }
        }
    }

    public Partie getPartie() {
        return partie;
    }
}
