package twisk.mondeIG;

/**
 * La classe PointDeControleIG.
 */
public class PointDeControleIG {

    /**
     * La Pos x.
     */
    int posX;
    /**
     * La Pos y.
     */
    int posY;
    /**
     * L'identifiant.
     */
    String identifiant;
    /**
     * L'étape.
     */
    EtapeIG etape;

    /**
     * Instancie une PointDeControleIG.
     *
     * @param posX la pos x
     * @param posY la pos y
     * @param id   l'id
     * @param e    l'étape
     */
    public PointDeControleIG(int posX, int posY, String id, EtapeIG e) {
        this.posX = posX;
        this.posY = posY;
        identifiant = id;
        etape = e;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public EtapeIG getEtape() {
        return etape;
    }

    public int getNumeroEtape() {
        return identifiant.charAt(0);
    }
}
