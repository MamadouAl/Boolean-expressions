/**
 * @author DIALLO Mamadou Aliou
 *
 */
public class Noeud {

    /**
     * Objet encapsulé dans un Noeud
     */
    protected char contenu;
    protected Noeud gauche;
    protected Noeud droit;

    public Noeud(char caract) {
        contenu = caract;
        gauche = null;
        droit = null;
    }
/**
 * Constructeur par defaut
 */
    public Noeud(){
        contenu =' ';
        gauche = null;
        droit = null;
    }

    /**
     *  Accesseurs & Setters
     *  */
    public Noeud getGauche() {
        return gauche;
    }
    public Noeud getDroit() {
        return droit;
    }
    public char getContenu() {
        return contenu;
    }


    /* Setters */
    public void setGauche(Noeud n_gauche) {
        gauche = n_gauche;
    }
    public void setDroit(Noeud n_droit) {
        droit = n_droit;
    }

    public void setContenu(char o_contenu) {
        contenu = o_contenu;
    }

    /**
     * Repésentation du Noeud en chaîne de caractères
     */
    public String toString() {
            return ""+contenu;
    }
}