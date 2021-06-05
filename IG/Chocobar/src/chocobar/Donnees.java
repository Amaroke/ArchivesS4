package chocobar;

public class Donnees {

    private final int[][] tableau;
    private final Partie partie;
    private final int nbCarresLongueur;
    private final int nbCarresLargeur;
    private int mangerParJoueur1;
    private int mangerParJoueur2;

    public Donnees(Partie p, int lo, int la) {
        partie = p;
        mangerParJoueur1 = 0;
        mangerParJoueur2 = 0;
        nbCarresLongueur = lo;
        nbCarresLargeur = la;
        tableau = new int[lo][la];
        for (int i = 0; i < getNbCarresLongueur(); ++i) {
            for (int j = 0; j < getNbCarresLargeur(); ++j) {
                tableau[i][j] = 0;
            }
        }
    }

    void croquer(int i, int j) {
        for (int k = i; k < getNbCarresLongueur(); ++k) {
            for (int l = j; l < getNbCarresLargeur(); ++l) {
                if (getPartie().getJoueurQuiJoue() == 1) {
                    if (getTableau()[k][l] == 0) {
                        mangerParJoueur1++;
                        tableau[k][l] = 1;
                    }
                } else {
                    if (getTableau()[k][l] == 0) {
                        mangerParJoueur2++;
                        tableau[k][l] = 2;
                    }
                }
            }
        }
    }

    public Partie getPartie() {
        return partie;
    }

    public int[][] getTableau() {
        return tableau;
    }

    public int getNbCarresLongueur() {
        return nbCarresLongueur;
    }

    public int getNbCarresLargeur() {
        return nbCarresLargeur;
    }

    public int getMangerParJoueur1() {
        return mangerParJoueur1;
    }

    public int getMangerParJoueur2() {
        return mangerParJoueur2;
    }
}
