package controlleur;

import carnet.CarnetDeVoyage;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * The type Controlleur menu.
 */
public class ControlleurMenu implements Observateur {

    private final CarnetDeVoyage carnet;
    private final Stage stage;
    @FXML
    private MenuItem suppression;
    @FXML
    private MenuItem suppressionActu;
    @FXML
    private MenuItem titre;
    @FXML
    private MenuItem description;
    @FXML
    private MenuItem photo;
    @FXML
    private MenuItem map;

    /**
     * Instantiates a new Controlleur menu.
     *
     * @param carnet the carnet
     * @param stage  the stage
     */
    public ControlleurMenu(CarnetDeVoyage carnet, Stage stage) {
        this.carnet = carnet;
        this.stage = stage;
        carnet.ajouterObservateur(this);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        suppression.setDisable(carnet.getNbPagesJour() == 0);
        suppressionActu.setDisable(carnet.getNbPagesJour() == 0);
        titre.setDisable(carnet.getPageActuelle() == 0);
        description.setDisable(carnet.getPageActuelle() == 0);
        photo.setDisable(carnet.getPageActuelle() == 0);
        map.setDisable(carnet.getPageActuelle() == 0);
    }

    /**
     * Aller a page.
     */
    public void allerAPage() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Se rendre à une page précise");
        dialog.setHeaderText("À quel page se rendre (entre 0 et " + carnet.getNbPagesJour() + ") :");
        dialog.setContentText("Numéro :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(numero -> {
            if (Integer.parseInt(numero) <= carnet.getNbPagesJour()) {
                carnet.setPageActuelle(Integer.parseInt(numero));
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setContentText("Choix invalide !");
                a.show();
                PauseTransition p = new PauseTransition(Duration.seconds(4));
                p.setOnFinished(event -> a.close());
                p.play();
            }
        });
        carnet.notifierObservateur();
    }

    /**
     * Supprimer page.
     */
    public void supprimerPage() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Supprimer une page précise");
        dialog.setHeaderText("À quel page se rendre (entre 1 et " + carnet.getNbPagesJour() + ") :");
        dialog.setContentText("Numéro :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(numero -> {
            if (Integer.parseInt(numero) < carnet.getNbPagesJour()+1 && Integer.parseInt(numero) != 0) {
                if (carnet.getPageActuelle()+1 > Integer.parseInt(numero)) {
                    carnet.setPageActuelle(carnet.getPageActuelle() - 1);
                }
                carnet.getPagesJour().remove(Integer.parseInt(numero)-1);
                carnet.reSetPage();
                carnet.notifierObservateur();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setContentText("Choix invalide !");
                a.show();
                PauseTransition p = new PauseTransition(Duration.seconds(4));
                p.setOnFinished(event -> a.close());
                p.play();
            }
        });
    }

    /**
     * Supprimer page actuelle.
     */
    public void supprimerPageActuelle() {
        if (carnet.getPageActuelle() != 0) {
            carnet.getPagesJour().remove(carnet.getPageActuelle() - 1);
            carnet.setPageActuelle(carnet.getPageActuelle() - 1);
            carnet.reSetPage();
            carnet.notifierObservateur();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText("Impossible de supprimer la page de présentation");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(4));
            p.setOnFinished(event -> a.close());
            p.play();
        }
    }

    /**
     * Ajouter page.
     */
    public void ajouterPage() {
        carnet.ajouterPageJour();
        carnet.notifierObservateur();
    }

    /**
     * Modifier titre carnet.
     */
    public void modifierTitreCarnet() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier le titre");
        dialog.setHeaderText("Entrer un nouveau titre :");
        dialog.setContentText("Titre :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nom -> carnet.getPagePresentation().modifierTitre(nom));
        carnet.notifierObservateur();
    }

    /**
     * Modifier auteur.
     */
    public void modifierAuteur() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier l'auteur");
        dialog.setHeaderText("Entrer un nouvel auteur :");
        dialog.setContentText("Auteur :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nom -> carnet.getPagePresentation().modifierAuteur(nom));
        carnet.notifierObservateur();
    }

    /**
     * Modifier dates.
     */
    public void modifierDates() {
        final Stage dialog = new Stage();
        dialog.setTitle("Changement des dates");
        Button valider = new Button("Valider");
        Label labelDebut = new Label("Date de début :");
        DatePicker saisieDateDebut = new DatePicker();
        Label labelFin = new Label("Date de fin :");
        DatePicker saisieDateFin = new DatePicker();
        Label displayLabel = new Label("Donnez de nouvelles dates :");
        displayLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        dialog.initModality(Modality.NONE);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        dialogVbox.getChildren().addAll(displayLabel, labelDebut, saisieDateDebut, labelFin, saisieDateFin, valider);
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    carnet.getPagePresentation().modifierDateDebut(saisieDateDebut.getValue().format(formatter));
                    carnet.getPagePresentation().modifierDateFin(saisieDateFin.getValue().format(formatter));
                    dialog.close();
                    carnet.notifierObservateur();
                });
        Scene dialogScene = new Scene(dialogVbox, 500, 250);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Modifier participants.
     */
    public void modifierParticipants() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier les participants");
        dialog.setHeaderText("Entrer les participants, séparés par des espaces :");
        dialog.setContentText("Participants :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nom -> carnet.getPagePresentation().modifierParticipants(nom));
        carnet.notifierObservateur();
    }

    /**
     * Modifier titre page.
     */
    public void modifierTitrePage() {
        if (carnet.getPageActuelle() != 0) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier le titre");
            dialog.setHeaderText("Entrer le nouveau titre de la page :");
            dialog.setContentText("Titre :");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nom -> carnet.getPageJourActuelle().modifierTitre(nom));
            carnet.notifierObservateur();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText("Vous n'êtes pas sur la page d'un jour.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(4));
            p.setOnFinished(event -> a.close());
            p.play();
        }
    }

    /**
     * Modifier description page.
     */
    public void modifierDescriptionPage() {
        if (carnet.getPageActuelle() != 0) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier la description");
            dialog.setHeaderText("Entrer la nouvelle description de la page :");
            dialog.setContentText("Description :");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nom -> carnet.getPageJourActuelle().modifierDescription(nom));
            carnet.notifierObservateur();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText("Vous n'êtes pas sur la page d'un jour.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(4));
            p.setOnFinished(event -> a.close());
            p.play();
        }
    }

    /**
     * Modifier photo page.
     */
    public void modifierPhotoPage() {
        final Stage dialog = new Stage();
        dialog.setTitle("Changement de la photo");
        FileChooser choixfichier = new FileChooser();
        choixfichier.setTitle("Quel photo charger ?");
        choixfichier.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("png", "*.png"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"));
        File selectedFile = choixfichier.showOpenDialog(dialog);
        if (selectedFile != null) {
            try {
                carnet.getPageJourActuelle().modifierPhoto(selectedFile.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        carnet.notifierObservateur();
    }

    /**
     * Modifier map.
     */
    public void modifierMap() {
        final Stage dialog = new Stage();
        dialog.setTitle("Changement de la map");
        Button valider = new Button("Valider");
        Label labelDebut = new Label("Longitude :");
        TextField longitude = new TextField();
        Label labelFin = new Label("Latitude :");
        TextField latitude = new TextField();

        Label displayLabel = new Label("Donnez de nouvelles coordonées :");
        displayLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        dialog.initModality(Modality.NONE);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().addAll(displayLabel, labelDebut, longitude, labelFin, latitude, valider);
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    carnet.getPageJourActuelle().setX(Double.parseDouble(longitude.getText()));
                    carnet.getPageJourActuelle().setY(Double.parseDouble(latitude.getText()));
                    dialog.close();
                    carnet.notifierObservateur();
                });
        Scene dialogScene = new Scene(dialogVbox, 500, 250);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Sauvegarder carnet.
     */
    public void sauvegarderCarnet() {
        DirectoryChooser dir = new DirectoryChooser();
        File pathtodir = dir.showDialog(stage);
        if (pathtodir != null) {
            final Stage dialog = new Stage();
            dialog.setTitle("Nom de la sauvegarde");
            Button valider = new Button("Valider");
            Label label = new Label("Nom du fichier : ");
            TextField saisie = new TextField();

            dialog.initModality(Modality.NONE);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setPadding(new Insets(20,20,20,20));
            dialogVbox.setAlignment(Pos.CENTER);
            dialogVbox.getChildren().addAll(label, saisie, valider);
            valider.setOnAction(f -> {
                carnet.sauvegarde(new File(pathtodir.getAbsolutePath() + "/" + saisie.getText() + ".json"));
                dialog.close();
            });
            Scene dialogScene = new Scene(dialogVbox, 300, 125);
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.show();
        }
    }

    /**
     * Charger carnet.
     */
    public void chargerCarnet() {
        final Stage dialog = new Stage();
        dialog.setTitle("Chargement d'une sauvegarde");
        FileChooser choixfichier = new FileChooser();
        choixfichier.setTitle("Quel fichier charger ?");
        choixfichier.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("json", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = choixfichier.showOpenDialog(dialog);
        if (selectedFile != null) {
            carnet.chargement(selectedFile);
        }
    }

    /**
     * Quitter application.
     */
    public void quitterApplication() {
        Platform.exit();
    }

    @Override
    public void reagir() {
        suppression.setDisable(carnet.getNbPagesJour() == 0);
        suppressionActu.setDisable(carnet.getNbPagesJour() == 0);
        titre.setDisable(carnet.getPageActuelle() == 0);
        description.setDisable(carnet.getPageActuelle() == 0);
        photo.setDisable(carnet.getPageActuelle() == 0);
        map.setDisable(carnet.getPageActuelle() == 0);
    }
}