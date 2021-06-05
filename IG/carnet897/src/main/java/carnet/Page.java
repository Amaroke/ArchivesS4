package carnet;

/**
 * The type Page.
 */
public abstract class Page {

    private String titre;
    private int numeroPage;

    /**
     * Instantiates a new Page.
     *
     * @param titre      the titre
     * @param numeroPage the numero page
     */
    public Page(String titre, int numeroPage) {
        this.numeroPage = numeroPage;
        this.titre = titre;
    }

    /**
     * Gets titre.
     *
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Modifier titre.
     *
     * @param titre the titre
     */
    public void modifierTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Gets numero page.
     *
     * @return the numero page
     */
    public int getNumeroPage() {
        return numeroPage;
    }

    /**
     * Sets numero page.
     *
     * @param n the n
     */
    public void setNumeroPage(int n) {
        this.numeroPage = n;
    }
}
