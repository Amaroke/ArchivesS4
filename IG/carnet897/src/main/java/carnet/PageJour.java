package carnet;

/**
 * The type Page jour.
 */
public class PageJour extends Page {

    private String description;
    private String photo;
    private double x, y;

    /**
     * Instantiates a new Page jour.
     *
     * @param titre       the titre
     * @param description the description
     * @param photo       the photo
     * @param numeroPage  the numero page
     * @param x           the x
     * @param y           the y
     */
    public PageJour(String titre, String description, String photo, int numeroPage, double x, double y) {
        super(titre, numeroPage);
        this.description = description;
        this.photo = photo;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifier description.
     *
     * @param description the description
     */
    public void modifierDescription(String description) {
        this.description = description;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Modifier photo.
     *
     * @param photo the photo
     */
    public void modifierPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }
}
