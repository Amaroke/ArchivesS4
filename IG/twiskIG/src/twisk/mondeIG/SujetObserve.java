package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

/**
 * La classe SujetObserve.
 */
public class SujetObserve {

    private final ArrayList<Observateur> observateurs = new ArrayList<>(10);

    /**
     * Ajouter un observateur.
     *
     * @param observateur l'observateur
     */
    public void ajouterObservateur(Observateur observateur) {
        getObservateurs().add(observateur);
    }

    /**
     * Notifie les observateurs.
     */
    public void notifierObservateur() {
        for (Observateur v : getObservateurs()) {
            v.reagir();
        }
    }

    private ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }

}
