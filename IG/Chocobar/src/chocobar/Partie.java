package chocobar;

import java.util.ArrayList;

public class Partie {

    private final ArrayList<Observateur> obs = new ArrayList<>(10);
    private final String[] joueurs = {"Loiseau", "Lenôtre"};
    private final Donnees plateau;
    private int joueurQuiJoue;
    private int joueurQuiGagne;
    private boolean partieTerminee;


    public Partie(int lo, int la) {
        joueurQuiJoue = (int) (Math.random() * 2);
        partieTerminee = false;
        plateau = new Donnees(this, lo, la);
    }

    public void ajouterObservateur(Observateur o) {
        this.getObs().add(o);
    }

    public void croquer(int i, int j) {
        plateau.croquer(i, j);
        if (i == 0 && j == 0) {
            partieTerminee = true;
            joueurQuiGagne = (getJoueurQuiJoue() == 0) ? 1 : 0;
        } else {
            joueurQuiJoue = (getJoueurQuiJoue() == 0) ? 1 : 0;
        }
        this.prevenirObservateurs();
    }

    public void prevenirObservateurs() {
        for (Observateur o : this.obs) {
            o.reagir();
        }
    }

    public ArrayList<Observateur> getObs() {
        return obs;
    }

    public String getNomJoueurs(int n) {
        return joueurs[n];
    }

    public void setNomJoueurs(int n, String s) {
        this.joueurs[n] = s;
    }

    public int getJoueurQuiJoue() {
        return joueurQuiJoue;
    }

    public int getJoueurQuiGagne() {
        return joueurQuiGagne;
    }

    public boolean isTerminee() {
        return partieTerminee;
    }

    public Donnees getPlateau() {
        return plateau;
    }

}