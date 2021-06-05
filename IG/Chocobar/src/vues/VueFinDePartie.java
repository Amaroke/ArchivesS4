package vues;

import chocobar.Observateur;
import chocobar.Partie;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class VueFinDePartie extends VBox implements Observateur {

    private final Label label;
    private final Partie partie;

    public VueFinDePartie(Partie partie) {
        this.partie = partie;
        this.label = new Label("Faites manger le burger a votre adversaire pour gagner !");
        getPartie().ajouterObservateur(this);
        this.getChildren().add(getLabel());
    }

    @Override
    public void reagir() {
        if (getPartie().isTerminee()) {
            getLabel().setText("C'est " + this.getPartie().getNomJoueurs(getPartie().getJoueurQuiGagne()) + " qui a gagné !");
            Media media = new Media(new File("src/ressources/applause.wav").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

    public Label getLabel() {
        return label;
    }

    public Partie getPartie() {
        return partie;
    }
}
