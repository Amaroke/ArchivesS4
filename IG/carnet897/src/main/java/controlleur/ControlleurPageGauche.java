package controlleur;

import carnet.CarnetDeVoyage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * The type Controlleur page gauche.
 */
public class ControlleurPageGauche implements Observateur {

    private final CarnetDeVoyage carnet;
    /**
     * The Controlleur page presentation.
     */
    private final ControlleurPagePresentation controlleurPagePresentation;
    @FXML
    private Button boutonGauche;

    /**
     * Instantiates a new Controlleur page gauche.
     *
     * @param carnet                      the carnet
     * @param controlleurPagePresentation the controlleur page presentation
     */
    public ControlleurPageGauche(CarnetDeVoyage carnet, ControlleurPagePresentation controlleurPagePresentation) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
        this.controlleurPagePresentation = controlleurPagePresentation;
    }

    /**
     * Tourner page gauche.
     */
    public void tournerPageGauche() {
        this.carnet.setPageActuelle(carnet.getPageActuelle() - 1);
        if (carnet.getPageActuelle() != 0) {
            BorderPane scene = ((BorderPane) boutonGauche.getParent());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PagePresentation.fxml"));
            loader.setControllerFactory(ic -> {
                if (ic.equals(controlleur.ControlleurPageJour.class)) return controlleurPagePresentation;
                return null;
            });
            Parent pageJour = null;
            try {
                pageJour = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setCenter(pageJour);
        }
        carnet.notifierObservateur();
    }

    @Override
    public void reagir() {
        boutonGauche.setDisable(carnet.getPageActuelle() == 0);
        if (carnet.getPageActuelle() == 0) {
            BorderPane scene = ((BorderPane) boutonGauche.getParent());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PagePresentation.fxml"));
            loader.setControllerFactory(ic -> {
                if (ic.equals(controlleur.ControlleurPagePresentation.class)) return controlleurPagePresentation;
                return null;
            });
            Parent pageJour = null;
            try {
                pageJour = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setCenter(pageJour);
        }
    }
}
