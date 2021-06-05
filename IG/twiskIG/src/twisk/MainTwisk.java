package twisk;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

/**
 * La classe principale MainTwisk.
 */
public class MainTwisk extends Application {

    /**
     * Le point d'entrée de l'application.
     *
     * @param args les arguments en entrée
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MondeIG monde = new MondeIG();

        BorderPane root = new BorderPane();

        VueOutils vueOutils = new VueOutils(monde);
        vueOutils.setAlignment(Pos.CENTER);

        VueMondeIG vueMonde = new VueMondeIG(monde);
        VueMenu vueMenu = new VueMenu(monde);

        root.setTop(vueMenu);
        root.setCenter(vueMonde);
        root.setBottom(vueOutils);

        primaryStage.setScene(new Scene(root, 750, 500));
        if (primaryStage.isResizable()) {
            primaryStage.initStyle(StageStyle.UNDECORATED); // Sans les boutons de la fenêtre.
        }
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}