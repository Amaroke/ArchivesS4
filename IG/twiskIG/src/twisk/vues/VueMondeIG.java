package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurDragnDropPanneau;
import twisk.ecouteurs.EcouteurDragnDropPanneauDrop;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe VueMondeIG.
 */
public class VueMondeIG extends Pane implements Observateur {

    private final MondeIG monde;

    /**
     * Instancie une VueMondeIG.
     *
     * @param monde le monde
     */
    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        getMonde().ajouterObservateur(this);
        for (int i = 0; i < getMonde().getNbEtapes(); i++) {
            VueEtapeIG vue = new VueActiviteIG(getMonde(), getMonde().getEtapes().get(String.valueOf(i)));
            getChildren().add(vue);
        }
        setOnDragOver(new EcouteurDragnDropPanneau(getMonde(), this));
        setOnDragDropped(new EcouteurDragnDropPanneauDrop(getMonde(), this));
        getMonde().notifierObservateur();
    }

    public void reagir() {
        getChildren().clear();
        for (ArcIG arc : getMonde().getArcs()) {
            VueArcIG vueArc = new VueArcIG(getMonde(), arc);
            getChildren().add(vueArc);
        }
        for (EtapeIG etape : getMonde()) {
            VueEtapeIG vue = new VueActiviteIG(getMonde(), etape);
            getChildren().add(vue);
        }
        for (EtapeIG etape : getMonde()) {
            for (int j = 0; j < 4; j++) {
                VuePointDeControleIG vueP = new VuePointDeControleIG(getMonde(), etape.getPointsDeControle()[j]);
                getChildren().add(vueP);
            }
        }
    }

    private MondeIG getMonde() {
        return monde;
    }
}
