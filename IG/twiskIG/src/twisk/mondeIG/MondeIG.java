package twisk.mondeIG;

import twisk.exceptions.TwiskException;
import twisk.exceptions.TwiskExceptionArcExistant;
import twisk.exceptions.TwiskExceptionMemeEtape;
import twisk.exceptions.TwiskExceptionMemePoints;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * La classe MondeIG.
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG> {

    private final ArrayList<ArcIG> arcs = new ArrayList<>(10);
    private final HashMap<String, EtapeIG> etapes = new HashMap<>();
    private PointDeControleIG pointSelectionne;

    /**
     * Instancie un MondeIG.
     */
    public MondeIG() {
        FabriqueIdentifiant singleton = FabriqueIdentifiant.getInstance();
        String noEtape = singleton.getIdentifiantEtape();
        TailleComposants singletonTaille = TailleComposants.getInstance();
        getEtapes().put(noEtape, new ActiviteIG("Activite n°" + noEtape, noEtape, singletonTaille.getLongeurVBox(), singletonTaille.getHauteurVBox()));
    }

    /**
     * Ajouter une étape.
     *
     * @param type le type d'étape
     */
    public void ajouter(String type) {
        if (type.equals("Activite")) {
            FabriqueIdentifiant singleton = FabriqueIdentifiant.getInstance();
            String noEtape = singleton.getIdentifiantEtape();
            TailleComposants singletonTaille = TailleComposants.getInstance();
            getEtapes().put(noEtape, new ActiviteIG("Activite n°" + noEtape, noEtape, singletonTaille.getLongeurVBox(), singletonTaille.getHauteurVBox()));
        }
        notifierObservateur();
    }

    /**
     * Ajouter un arc.
     *
     * @param pt1 le pt1 de l'arc
     * @param pt2 le pt2 de l'arc
     * @throws TwiskException l'exception
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws TwiskException {
        for (ArcIG a : getArcs()) {
            // On vérifie que l'arc n'existe pas.
            if (a.getPt1().getPosX() == pt1.getPosX() && a.getPt1().getPosY() == pt1.getPosY() && a.getPt2().getPosX() == pt2.getPosX() && a.getPt2().getPosY() == pt2.getPosY() || a.getPt1().getPosX() == pt2.getPosX() && a.getPt1().getPosY() == pt2.getPosY() && a.getPt2().getPosX() == pt1.getPosX() && a.getPt2().getPosY() == pt1.getPosY()) {
                throw new TwiskExceptionArcExistant();
            }
        }
        // On vérifie que les deux points sont distincts.
        if (pt1.getPosX() == pt2.getPosX() && pt1.getPosY() == pt2.getPosY()) {
            throw new TwiskExceptionMemePoints();
        }
        // On vérifie qu'ils sont pas sur la même étape.
        if (pt1.getNumeroEtape() == pt2.getNumeroEtape()) {
            throw new TwiskExceptionMemeEtape();
        }
        // Si c'est correct, on ajoute l'arc et on mets à jour le modèle.
        getArcs().add(new ArcIG(pt1, pt2));
        notifierObservateur();
    }

    /**
     * Le nombre d'étape selectionnées.
     *
     * @return le nombre
     */
    public int nbEtapesSelect() {
        Iterator<EtapeIG> it = iterator();
        int nbSelect = 0;
        while (it.hasNext()) {
            EtapeIG e = it.next();
            if (e.isSelect()) {
                nbSelect++;
            }
        }
        return nbSelect;
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator();
    }

    /**
     * Iterator arc iterator.
     *
     * @return l'iterator iterator
     */
    public Iterator<ArcIG> iteratorArc() {
        return arcs.iterator();
    }

    public ArrayList<ArcIG> getArcs() {
        return arcs;
    }

    public HashMap<String, EtapeIG> getEtapes() {
        return etapes;
    }

    public int getNbEtapes() {
        return etapes.size();
    }

    public PointDeControleIG getPointSelectionne() {
        return pointSelectionne;
    }

    public void setPointSelectionne(PointDeControleIG pt) {
        this.pointSelectionne = pt;
    }
}
