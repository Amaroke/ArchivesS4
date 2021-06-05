package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * La classe VuePointDeControleIG.
 */
public class VuePointDeControleIG extends Circle implements Observateur {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * Le Point de controle.
     */
    PointDeControleIG pointDeControle;

    /**
     * Instancie une VuePointDeControleIG.
     *
     * @param monde           le monde
     * @param pointDeControle le point de controle
     */
    VuePointDeControleIG(MondeIG monde, PointDeControleIG pointDeControle) {
        this.monde = monde;
        this.pointDeControle = pointDeControle;
        setCenterX(pointDeControle.getPosX());
        setCenterY(pointDeControle.getPosY());
        setRadius(5);
        boolean select = false;
        if (getMonde().getPointSelectionne() != null) {
            if (getMonde().getPointSelectionne().getIdentifiant().equals(pointDeControle.getIdentifiant())) {
                select = true;
            }
        }
        if (select) {
            setFill(Color.DEEPSKYBLUE);
        } else {
            setFill(Color.STEELBLUE);
        }
        setOnMouseClicked(new EcouteurPointDeControle(getMonde(), getPointDeControle()));
    }

    @Override
    public void reagir() {

    }

    private MondeIG getMonde() {
        return monde;
    }

    private PointDeControleIG getPointDeControle() {
        return pointDeControle;
    }
}
