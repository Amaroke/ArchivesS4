package carnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The type Page presentation.
 */
public class PagePresentation extends Page {

    private final ArrayList<String> participants = new ArrayList<>(10);
    private String auteur;
    private String dateDebut;
    private String dateFin;

    /**
     * Instantiates a new Page presentation.
     *
     * @param titre        the titre
     * @param auteur       the auteur
     * @param dateDebut    the date debut
     * @param dateFin      the date fin
     * @param participants the participants
     */
    public PagePresentation(String titre, String auteur, String dateDebut, String dateFin, String... participants) {
        super(titre, 0);
        this.auteur = auteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        Collections.addAll(this.participants, participants);
    }

    /**
     * Gets auteur.
     *
     * @return the auteur
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * Modifier auteur.
     *
     * @param auteur the auteur
     */
    public void modifierAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * Gets date debut.
     *
     * @return the date debut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifier date debut.
     *
     * @param dateDebut the date debut
     */
    public void modifierDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Gets date fin.
     *
     * @return the date fin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Modifier date fin.
     *
     * @param dateFin the date fin
     */
    public void modifierDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public ArrayList<String> getParticipants() {
        return participants;
    }

    /**
     * Modifier participants.
     *
     * @param participants the participants
     */
    public void modifierParticipants(String participants) {
        this.participants.clear();
        this.participants.addAll(Arrays.asList(participants.split(" ")));
    }
}
