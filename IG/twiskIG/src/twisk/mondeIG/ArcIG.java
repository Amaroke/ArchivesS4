package twisk.mondeIG;

/**
 * La classe ArcIG.
 */
public class ArcIG {

    /**
     * Le point1.
     */
    PointDeControleIG pt1;
    /**
     * Le point2.
     */
    PointDeControleIG pt2;
    private boolean select;

    /**
     * Instancie un ArcIG.
     *
     * @param pt1 le point1
     * @param pt2 le point2
     */
    ArcIG(PointDeControleIG pt1, PointDeControleIG pt2) {
        this.pt1 = pt1;
        this.pt2 = pt2;
    }

    public PointDeControleIG getPt1() {
        return pt1;
    }

    public PointDeControleIG getPt2() {
        return pt2;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

