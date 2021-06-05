package chocobar;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EcouteurBouton implements EventHandler<ActionEvent> {

    private final Partie partie;
    private final int i;
    private final int j;

    public EcouteurBouton(Partie partie, int i, int j) {
        this.partie = partie;
        this.i = i;
        this.j = j;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        partie.croquer(i, j);
    }

}
