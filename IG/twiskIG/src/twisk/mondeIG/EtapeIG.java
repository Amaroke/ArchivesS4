package twisk.mondeIG;

import twisk.exceptions.TwiskExceptionDelai;
import twisk.exceptions.TwiskExceptionTemps;

import java.util.Iterator;

/**
 * La classe EtapeIG.
 */
public abstract class EtapeIG implements Iterable<PointDeControleIG> {

    private final String identifiant;
    private final int largeur;
    private final int hauteur;
    private String nom;
    private int posX;
    private int posY;
    private PointDeControleIG[] pointsDeControle = new PointDeControleIG[4];
    private boolean select;
    private boolean entree;
    private boolean sortie;
    private int temps;
    private int ecart;

    /**
     * Instancie une EtapeIG.
     *
     * @param nom  le nom
     * @param idf  l'identifiant
     * @param larg la largeur
     * @param haut la hauteur
     */
    EtapeIG(String nom, String idf, int larg, int haut) {
        this.nom = nom;
        identifiant = idf;
        posX = (int) ((Math.random() * (605)) + 25);
        posY = (int) ((Math.random() * (365)) + 25);
        temps = 2;  // 1 < temps < 100
        ecart = 1;  // 0 < delai < temps
        largeur = larg;
        hauteur = haut;
        pointsDeControle[0] = new PointDeControleIG(getPosX() + getLargeur() / 2, getPosY(), getIdentifiant() + "N", this);
        pointsDeControle[1] = new PointDeControleIG(getPosX(), getPosY() + getHauteur() / 2, getIdentifiant() + "O", this);
        pointsDeControle[2] = new PointDeControleIG(getPosX() + getLargeur(), getPosY() + getHauteur() / 2, getIdentifiant() + "E", this);
        pointsDeControle[3] = new PointDeControleIG(getPosX() + getLargeur() / 2, getPosY() + getHauteur(), getIdentifiant() + "S", this);
        select = false;
        entree = false;
        sortie = false;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return new Iterator<>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < getNombrePointsDeControle() && getPointsDeControle()[i] != null;
            }

            @Override
            public PointDeControleIG next() {
                return getPointsDeControle()[i++];
            }
        };
    }

    /**
     * Reset pdc.
     */
    public void resetPdc() {
        pointsDeControle[0].setPos(getPosX() + getLargeur() / 2, getPosY());
        pointsDeControle[1].setPos(getPosX(), getPosY() + getHauteur() / 2);
        pointsDeControle[2].setPos(getPosX() + getLargeur(), getPosY() + getHauteur() / 2);
        pointsDeControle[3].setPos(getPosX() + getLargeur() / 2, getPosY() + getHauteur());
    }

    /**
     * Supprime les pdc.
     */
    public void suppPdc() {
        pointsDeControle = null;
    }

    private int getTemps() {
        return temps;
    }

    /**
     * Règle le temps.
     *
     * @param temps le temps
     * @throws TwiskExceptionTemps l'exception
     */
    public void setTemps(int temps) throws TwiskExceptionTemps {
        if (temps < 100 && temps > 1) {
            this.temps = temps;
        } else {

            throw new TwiskExceptionTemps();
        }
    }

    /**
     * Règle l'écart.
     *
     * @param ecart l'écart
     * @throws TwiskExceptionDelai l'exception
     */
    public void setEcart(int ecart) throws TwiskExceptionDelai {
        if (this.ecart > 0 && this.ecart < getTemps()) {
            this.ecart = ecart;
        } else {

            throw new TwiskExceptionDelai();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public PointDeControleIG[] getPointsDeControle() {
        return pointsDeControle;
    }

    private int getNombrePointsDeControle() {
        return pointsDeControle.length;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isNotEntree() {
        return !entree;
    }

    public void setEntree(boolean entree) {
        this.entree = entree;
    }

    public boolean isNotSortie() {
        return !sortie;
    }

    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }
}
