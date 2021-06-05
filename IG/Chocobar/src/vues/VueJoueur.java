package vues;

import chocobar.Observateur;
import chocobar.Partie;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueJoueur extends VBox implements Observateur {

    private final Label label;
    private final Label carresManges;
    private final Partie partie;

    public VueJoueur(Partie partie) {
        this.partie = partie;
        this.label = new Label("C'est au tour de " + this.partie.getNomJoueurs(getPartie().getJoueurQuiJoue()) + " !");
        getPartie().ajouterObservateur(this);
        this.getChildren().add(getLabel());
        this.carresManges = new Label(getPartie().getNomJoueurs(0) + " a mangé " + getPartie().getPlateau().getMangerParJoueur1() + " carrés et " + getPartie().getNomJoueurs(1) + " a mangé " + getPartie().getPlateau().getMangerParJoueur2() + " carrés.");
        this.getChildren().add(getCarresManges());
    }

    @Override
    public void reagir() {
        label.setText("C'est au tour de " + this.partie.getNomJoueurs(getPartie().getJoueurQuiJoue()) + " !");
        carresManges.setText(getPartie().getNomJoueurs(0) + " a mangé " + getPartie().getPlateau().getMangerParJoueur1() + " carrés et " + getPartie().getNomJoueurs(1) + " a mangé " + getPartie().getPlateau().getMangerParJoueur2() + " carrés.");
    }

    public Label getLabel() {
        return label;
    }

    public Label getCarresManges() {
        return carresManges;
    }

    public Partie getPartie() {
        return partie;
    }
}
