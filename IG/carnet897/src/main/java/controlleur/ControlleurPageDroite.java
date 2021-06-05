package controlleur;

import carnet.CarnetDeVoyage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

/**
 * The type Controlleur page droite.
 */
public class ControlleurPageDroite implements Observateur {

    private final CarnetDeVoyage carnet;
    private final ControlleurPageJour controlleurPageJour;
    @FXML
    private Button boutonDroite;

    /**
     * Instantiates a new Controlleur page droite.
     *
     * @param carnet              the carnet
     * @param controlleurPageJour the controlleur page jour
     */
    public ControlleurPageDroite(CarnetDeVoyage carnet, ControlleurPageJour controlleurPageJour) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
        this.controlleurPageJour = controlleurPageJour;
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        if (carnet.getPageActuelle() == carnet.getNbPagesJour()) {
            boutonDroite.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flecheNavigationNouvellePage.png")), 61, 71, true, true)));
        } else {
            boutonDroite.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flecheNavigation.png")), 61, 71, true, true)));
        }
    }

    /**
     * Tourner page droite.
     */
    public void tournerPageDroite() {
        if (carnet.getPageActuelle() == carnet.getNbPagesJour()) {
            this.carnet.ajouterPageJour();
        }
        if (carnet.getPageActuelle() == 0) {
            BorderPane scene = ((BorderPane) boutonDroite.getParent());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PageJour.fxml"));
            loader.setControllerFactory(ic -> {
                if (ic.equals(controlleur.ControlleurPageJour.class)) return controlleurPageJour;
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
        this.carnet.setPageActuelle(carnet.getPageActuelle() + 1);
        carnet.notifierObservateur();
    }

    @Override
    public void reagir() {
        if (carnet.getPageActuelle() == carnet.getNbPagesJour()) {
            boutonDroite.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flecheNavigationNouvellePage.png")), 61, 71, true, true)));
        } else {
            boutonDroite.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/flecheNavigation.png")), 61, 71, true, true)));
        }
        if (carnet.getPageActuelle() != 0) {
            BorderPane scene = ((BorderPane) boutonDroite.getParent());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PageJour.fxml"));
            loader.setControllerFactory(ic -> {
                if (ic.equals(controlleur.ControlleurPageJour.class)) return controlleurPageJour;
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
