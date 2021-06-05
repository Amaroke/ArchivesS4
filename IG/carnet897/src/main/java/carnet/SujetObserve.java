package carnet;


import controlleur.Observateur;

import java.util.ArrayList;

/**
 * The type Sujet observe.
 */
public class SujetObserve {

    private final transient ArrayList<Observateur> observateurs = new ArrayList<>(10);

    /**
     * Ajouter observateur.
     *
     * @param observateur the observateur
     */
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    /**
     * Notifier observateur.
     */
    public void notifierObservateur() {
        for (Observateur v : observateurs) {
            v.reagir();
        }
    }
}