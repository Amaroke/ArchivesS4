package controlleur;

import carnet.CarnetDeVoyage;
import carnet.PageJour;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The type Controlleur page jour.
 */
public class ControlleurPageJour implements Observateur {

    private final CarnetDeVoyage carnet;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private ImageView photo;
    @FXML
    private Label numero;
    @FXML
    private Pane map;

    /**
     * Instantiates a new Controlleur page jour.
     *
     * @param carnet the carnet
     */
    public ControlleurPageJour(CarnetDeVoyage carnet) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        if (carnet.getPageActuelle() != 0) {
            PageJour page = carnet.getPageJourActuelle();
            titre.setText(page.getTitre());
            description.setText(page.getDescription());
            photo.setImage(new Image(page.getPhoto()));
            numero.setText(page.getNumeroPage() + "/" + carnet.getNbPagesJour());
            setMap(page.getX(), page.getY());
        }
    }

    /**
     * Sets map.
     *
     * @param x the x
     * @param y the y
     */
    public void setMap(double x, double y) {
        Coordinate coord = new Coordinate(x, y);
        Marker mark = Marker.createProvided(Marker.Provided.BLUE);
        mark.setPosition(coord).setVisible(true);
        MapView mapView = new MapView();
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mapView.setCenter(coord);
                mapView.addMarker(mark);
            }
        });

        mapView.initialize();
        mapView.setMinSize(225, 175);
        mapView.setMaxSize(225, 175);
        map.getChildren().add(mapView);
    }

    @Override
    public void reagir() {
        if (carnet.getPageActuelle() != 0) {
            PageJour page = carnet.getPageJourActuelle();
            titre.setText(page.getTitre());
            description.setText(page.getDescription());
            photo.setImage(new Image(page.getPhoto()));
            numero.setText(page.getNumeroPage() + "/" + carnet.getNbPagesJour());
            setMap(page.getX(), page.getY());
        }
    }
}
