package controlleur;

import carnet.CarnetDeVoyage;
import carnet.PagePresentation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * The type Controlleur page presentation.
 */
public class ControlleurPagePresentation implements Observateur {

    private final CarnetDeVoyage carnet;
    @FXML
    private Label titre;
    @FXML
    private Label auteur;
    @FXML
    private Label participants;
    @FXML
    private Label dates;

    /**
     * Instantiates a new Controlleur page presentation.
     *
     * @param carnet the carnet
     */
    public ControlleurPagePresentation(CarnetDeVoyage carnet) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        PagePresentation couverture = carnet.getPagePresentation();
        titre.setText(couverture.getTitre());
        auteur.setText(couverture.getAuteur());
        ArrayList<String> lParticipants = couverture.getParticipants();
        StringBuilder sParticipants = new StringBuilder("Avec ");
        for (int i = 0; i < lParticipants.size() - 2; ++i) {
            sParticipants.append(lParticipants.get(i)).append(", ");
        }
        if (lParticipants.size() > 1) {
            sParticipants.append(lParticipants.get(lParticipants.size() - 2)).append(" et ");
        }
        sParticipants.append(lParticipants.get(lParticipants.size() - 1));
        participants.setText(sParticipants + ".");
        dates.setText("Du " + couverture.getDateDebut() + " au " + couverture.getDateFin() + ".");
    }

    @Override
    public void reagir() {
        PagePresentation couverture = carnet.getPagePresentation();
        titre.setText(couverture.getTitre());
        auteur.setText(couverture.getAuteur());
        ArrayList<String> lParticipants = couverture.getParticipants();
        StringBuilder sParticipants = new StringBuilder("Avec ");
        for (int i = 0; i < lParticipants.size() - 2; ++i) {
            sParticipants.append(lParticipants.get(i)).append(", ");
        }
        if (lParticipants.size() > 1) {
            sParticipants.append(lParticipants.get(lParticipants.size() - 2)).append(" et ");
        }
        sParticipants.append(lParticipants.get(lParticipants.size() - 1));
        participants.setText(sParticipants + ".");
        dates.setText("Du " + couverture.getDateDebut() + " au " + couverture.getDateFin() + ".");
    }
}
