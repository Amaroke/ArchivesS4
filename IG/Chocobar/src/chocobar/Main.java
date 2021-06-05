package chocobar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vues.PlateauDeJeu;
import vues.VueFinDePartie;
import vues.VueJoueur;

public class Main extends Application {

    private int lo = 5;
    private int la = 10;

    @Override
    public void start(Stage primaryStage) {

        Partie partie = new Partie(lo, la);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(0, 0, 10, 0));

        // Creation du menu
        MenuBar menu = new MenuBar();
        VBox top = new VBox();

        // Menu pour les changements de noms.
        Menu menuNom = new Menu("Noms");
        MenuItem nomUn = new MenuItem("Changer le nom du joueur 1");
        MenuItem nomDeux = new MenuItem("Changer le nom du joueurs 2");
        nomUn.setOnAction(
                event -> {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    TextField saisie = new TextField("Nom du joueur 1");
                    dialogVbox.getChildren().add(saisie);
                    Button valider = new Button("Valider");
                    valider.setOnAction(e -> {
                        partie.setNomJoueurs(0, saisie.getText());
                        dialog.close();
                        partie.prevenirObservateurs();
                    });
                    dialogVbox.getChildren().add(valider);
                    dialogVbox.setAlignment(Pos.CENTER);
                    dialogVbox.setPadding(new Insets(10, 10, 10, 10));
                    Scene dialogScene = new Scene(dialogVbox);
                    dialog.setScene(dialogScene);
                    dialog.show();
                });
        nomDeux.setOnAction(
                event -> {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    TextField saisie = new TextField("Nom du joueur 1");
                    dialogVbox.getChildren().add(saisie);
                    Button valider = new Button("Valider");
                    valider.setOnAction(e -> {
                        partie.setNomJoueurs(1, saisie.getText());
                        dialog.close();
                        partie.prevenirObservateurs();
                    });
                    dialogVbox.getChildren().add(valider);
                    dialogVbox.setAlignment(Pos.CENTER);
                    dialogVbox.setPadding(new Insets(10, 10, 10, 10));
                    Scene dialogScene = new Scene(dialogVbox);
                    dialog.setScene(dialogScene);
                    dialog.show();
                });
        menuNom.getItems().addAll(nomUn, nomDeux);

        // Menu pour relancer une partie.
        Menu menuPartie = new Menu("Partie");
        MenuItem relancer = new MenuItem("Choisir la taille de la tablette");
        relancer.setOnAction(
                event -> {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    TextField saisieLo = new TextField("Longueur");
                    TextField saisieLa = new TextField("Largeur");
                    dialogVbox.getChildren().add(saisieLo);
                    dialogVbox.getChildren().add(saisieLa);
                    Button valider = new Button("Valider");
                    valider.setOnAction(e -> {
                        lo = Integer.parseInt(saisieLo.getText());
                        la = Integer.parseInt(saisieLa.getText());
                        dialog.close();
                        start(primaryStage);
                        partie.prevenirObservateurs();
                    });
                    dialogVbox.getChildren().add(valider);
                    dialogVbox.setAlignment(Pos.CENTER);
                    dialogVbox.setPadding(new Insets(10, 10, 10, 10));
                    Scene dialogScene = new Scene(dialogVbox);
                    dialog.setScene(dialogScene);
                    dialog.show();
                });
        menuPartie.getItems().addAll(relancer);

        // Menu pour le choix de la difficulté.
        Menu menuDifficulte = new Menu("Difficulté");
        MenuItem facile = new MenuItem("Facile");
        MenuItem difficile = new MenuItem("Difficile");
        menuDifficulte.getItems().addAll(facile, difficile);

        // Option pour quitter.
        Menu menuQuitter = new Menu("Quitter");
        MenuItem quitter = new MenuItem("Quitter le jeu");
        quitter.setOnAction(e -> Platform.exit());
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        menuQuitter.getItems().addAll(quitter);

        // On ajoute les éléments dans la barre de menu.
        menu.getMenus().addAll(menuNom, menuPartie, menuDifficulte, menuQuitter);

        // Labels affichant le nom du joueur actuel et les carrés mangés par chaque joueur.
        VueJoueur vueJoueur = new VueJoueur(partie);
        vueJoueur.setAlignment(Pos.CENTER);
        vueJoueur.setSpacing(10);

        top.getChildren().add(menu);
        top.getChildren().add(vueJoueur);
        top.setSpacing(20);
        root.setTop(top);

        // Le plateau de jeu (tablette de chocolat).
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu(partie);
        plateauDeJeu.setAlignment(Pos.CENTER);
        root.setCenter(plateauDeJeu);

        // Labels affichant le but du jeu, puis le joueur gagnant.
        VueFinDePartie vueFinDePartie = new VueFinDePartie(partie);
        vueFinDePartie.setAlignment(Pos.CENTER);
        vueFinDePartie.setSpacing(10);
        root.setBottom(vueFinDePartie);

        primaryStage.setScene(new Scene(root, 700, 700));
        if (primaryStage.isResizable()) {
            primaryStage.initStyle(StageStyle.UNDECORATED); // Sans les boutons de la fenêtre.
        }
        primaryStage.setResizable(false); // Non redimensionnable.
        primaryStage.show();
    }
}
