import animatefx.animation.FadeInDownBig;
import carnet.CarnetDeVoyage;
import controlleur.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CarnetDeVoyage carnetDeVoyage = new CarnetDeVoyage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/Fusion.fxml"));
        ControlleurMenu controlleurMenu = new ControlleurMenu(carnetDeVoyage, stage);
        ControlleurPagePresentation controlleurPageCouverture = new ControlleurPagePresentation(carnetDeVoyage);
        ControlleurPageJour controlleurPageJour = new ControlleurPageJour(carnetDeVoyage);
        ControlleurPageDroite controlleurPageDroite = new ControlleurPageDroite(carnetDeVoyage, controlleurPageJour);
        ControlleurPageGauche controlleurPageGauche = new ControlleurPageGauche(carnetDeVoyage, controlleurPageCouverture);

        loader.setControllerFactory(ic -> {
            if (ic.equals(controlleur.ControlleurMenu.class)) return controlleurMenu;
            else if (ic.equals(controlleur.ControlleurPagePresentation.class)) return controlleurPageCouverture;
            else if (ic.equals(controlleur.ControlleurPageDroite.class)) return controlleurPageDroite;
            else if (ic.equals(controlleur.ControlleurPageGauche.class)) return controlleurPageGauche;
            return null;
        });
        BorderPane root = loader.load();
        stage.setScene(new Scene(root, 751, 605));
        if (stage.isResizable()) {
            stage.initStyle(StageStyle.UNDECORATED); // Sans les boutons de la fenêtre.
        }
        stage.setResizable(false); // Non redimensionnable.
        stage.show();
        new FadeInDownBig(root).setSpeed(0.5).play();
        carnetDeVoyage.notifierObservateur();
    }
}