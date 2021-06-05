package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.ecouteurs.EcouteurArc;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

import java.awt.*;

/**
 * La classe VueArcIG.
 */
public class VueArcIG extends Pane implements Observateur {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * Le Arc.
     */
    ArcIG arc;

    /**
     * Instancie une VueArcIG.
     *
     * @param monde le monde
     * @param arc   l'arc
     */
    VueArcIG(MondeIG monde, ArcIG arc) {
        this.monde = monde;
        this.arc = arc;
        // Ajout du trait de la flèche.
        Line trait = new Line(getAX(), getAY(), getBX(), getBY());
        trait.setStrokeWidth(3);
        getChildren().add(trait);

        // Création de la pointe.
        Polyline pointe = new Polyline();
        pointe.setStrokeWidth(2);

        // Calcul pour la pointe de la flèche.
        Point c = new Point(getBX() - getAX(), getBY() - getAY());
        double rap = 20.0 / Math.sqrt(Math.pow((getBX() - getAX()), 2) + Math.pow((getBY() - getAY()), 2));
        double dX = -c.getX() * rap;
        double dY = -c.getY() * rap;
        double eX = getBX() + dX;
        double eY = getBY() + dY;
        dX /= 2;
        dY /= 2;

        // On trace la pointe.
        pointe.getPoints().addAll((double) getBX(), (double) getBY(), eX - dY, eY + dX, eX + dY, eY - dX, (double) getBX(), (double) getBY());
        pointe.setFill(Color.STEELBLUE);

        // Ajout de la pointe.
        getChildren().add(pointe);

        if (getArc().isSelect()) {
            trait.setStroke(Color.STEELBLUE);
            pointe.setStroke(Color.STEELBLUE);
            pointe.setFill(Color.BLACK);
        }
        setOnMouseClicked(new EcouteurArc(getMonde(), getArc()));
        this.setPickOnBounds(false);
    }

    @Override
    public void reagir() {
    }

    private int getAX() {
        return this.arc.getPt1().getPosX();
    }

    private int getBX() {
        return this.arc.getPt2().getPosX();
    }

    private int getAY() {
        return this.arc.getPt1().getPosY();
    }

    private int getBY() {
        return this.arc.getPt2().getPosY();
    }

    private MondeIG getMonde() {
        return monde;
    }

    private ArcIG getArc() {
        return arc;
    }
}
