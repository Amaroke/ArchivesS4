package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;

/**
 * La classe VueMenu.
 */
public class VueMenu extends MenuBar implements Observateur {

    /**
     * Le Monde.
     */
    MondeIG monde;
    /**
     * L'ItemMenu Renommer.
     */
    MenuItem renommer = new MenuItem("Renommer");
    /**
     * L'ItemMenu ModifierDelai.
     */
    MenuItem modifierDelai = new MenuItem("Modifier le délai");
    /**
     * L'ItemMenu ModifierEcart.
     */
    MenuItem modifierEcart = new MenuItem("Modifier l'écart");

    /**
     * Instancie une VueMenu.
     *
     * @param monde le monde
     */
    public VueMenu(MondeIG monde) {

        this.monde = monde;
        getMonde().ajouterObservateur(this);

        // Fichier
        Menu menuFichier = new Menu("Fichier");
        MenuItem quitter = new MenuItem("Quitter");
        quitter.setOnAction(e -> Platform.exit());
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        menuFichier.getItems().addAll(quitter);

        // Édition
        Menu menuEdition = new Menu("Édition");
        MenuItem suppSelect = new MenuItem("Supprimer la sélection");
        suppSelect.setOnAction(new EcouteurSupprimerSelection(getMonde()));

        getRenommer().setOnAction(new EcouteurRenommer(getMonde()));

        MenuItem effacerSelect = new MenuItem("Effacer la sélection");
        effacerSelect.setOnAction(new EcouteurDesactiverSelection(getMonde()));

        menuEdition.getItems().addAll(suppSelect, getRenommer(), effacerSelect);

        // Monde
        Menu menuMonde = new Menu("Monde");

        MenuItem entree = new MenuItem("Entrée");
        entree.setOnAction(new EcouteurEntree(getMonde()));

        MenuItem sortie = new MenuItem("Sortie");
        sortie.setOnAction(new EcouteurSortie(getMonde()));

        menuMonde.getItems().addAll(entree, sortie);

        // Paramètres
        Menu menuParametres = new Menu("Paramètres");


        getModifierDelai().setOnAction(new EcouteurTemps(getMonde()));


        getModifierEcart().setOnAction(new EcouteurEcart(getMonde()));

        menuParametres.getItems().addAll(modifierDelai, modifierEcart);

        // On ajoute les éléments dans la barre de menu.
        getMenus().addAll(menuFichier, menuEdition, menuMonde, menuParametres);
        getMonde().notifierObservateur();
    }

    @Override
    public void reagir() {
        getRenommer().setDisable(getMonde().nbEtapesSelect() != 1);
        getModifierDelai().setDisable(getMonde().nbEtapesSelect() != 1);
        getModifierEcart().setDisable(getMonde().nbEtapesSelect() != 1);
    }

    private MondeIG getMonde() {
        return this.monde;
    }

    private MenuItem getRenommer() {
        return renommer;
    }

    private MenuItem getModifierDelai() {
        return modifierDelai;
    }

    private MenuItem getModifierEcart() {
        return modifierEcart;
    }
}
