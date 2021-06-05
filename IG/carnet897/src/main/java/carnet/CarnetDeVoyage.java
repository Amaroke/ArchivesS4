package carnet;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Carnet de voyage.
 */
public class CarnetDeVoyage extends SujetObserve {

    private ArrayList<PageJour> pagesJour = new ArrayList<>();
    private PagePresentation pagePresentation;
    private int pageActuelle;

    /**
     * Instantiates a new Carnet de voyage.
     */
    public CarnetDeVoyage() {
        pagePresentation = new PagePresentation("Voyage par défaut", "Auteur par défaut", "01/01/2000", "01/01/2000", "A", "C", "D", "B");
        pageActuelle = 0;
    }

    /**
     * Ajouter page jour.
     */
    public void ajouterPageJour() {
        pagesJour.add(new PageJour("Jour par défaut", "Description par défaut", "/images/imageDefaut.png", this.getNbPagesJour() + 1, 48.692054, 6.184417));
        reSetPage();
    }

    /**
     * Gets page jour actuelle.
     *
     * @return the page jour actuelle
     */
    public PageJour getPageJourActuelle() {
        return pagesJour.get(pageActuelle - 1);
    }

    /**
     * Gets nb pages jour.
     *
     * @return the nb pages jour
     */
    public int getNbPagesJour() {
        return pagesJour.size();
    }

    /**
     * Gets page actuelle.
     *
     * @return the page actuelle
     */
    public int getPageActuelle() {
        return pageActuelle;
    }

    /**
     * Sets page actuelle.
     *
     * @param numeroPage the numero page
     */
    public void setPageActuelle(int numeroPage) {
        this.pageActuelle = numeroPage;
        notifierObservateur();
    }

    /**
     * Gets pages jour.
     *
     * @return the pages jour
     */
    public ArrayList<PageJour> getPagesJour() {
        return pagesJour;
    }

    /**
     * Gets page presentation.
     *
     * @return the page presentation
     */
    public PagePresentation getPagePresentation() {
        return pagePresentation;
    }

    /**
     * Re set page.
     */
    public void reSetPage() {
        int i = 1;
        for (PageJour p : getPagesJour()) {
            p.setNumeroPage(i);
            i++;
        }
    }

    /**
     * Sauvegarde.
     *
     * @param emplacement the emplacement
     */
    public void sauvegarde(File emplacement) {
        Gson sauvegarde = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(emplacement);
            fileWriter.write(sauvegarde.toJson(pagePresentation));
            fileWriter.write("\n");
            for (PageJour pageJour : pagesJour) {
                fileWriter.write(sauvegarde.toJson(pageJour));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chargement.
     *
     * @param emplacement the emplacement
     */
    public void chargement(File emplacement) {
        FileReader flot;
        try {
            pagesJour = null;
            pagesJour = new ArrayList<>();
            pagePresentation = null;
            pageActuelle = 0;
            flot = new FileReader(emplacement);
            Gson chargement = new Gson();
            BufferedReader flotFiltre = new BufferedReader(flot);
            String ligne;
            ligne = flotFiltre.readLine();
            pagePresentation = chargement.fromJson(ligne, PagePresentation.class);
            while (ligne != null) {
                ligne = flotFiltre.readLine();
                if(ligne != null) {
                    pagesJour.add(chargement.fromJson(ligne, PageJour.class));
                }
            }
            notifierObservateur();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
