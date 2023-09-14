/**
 * @author DIALLO Mamadou Aliou && ACHI Tinhinane
 * 
 * Classe qui implemente une Expression booléenne 
 */
public class EB {

    private Noeud racine;

    /**
     * Constructeur par defaut qui cree une ExpBooleenne vide
     */
    public EB(){
        racine=null;
    }
    /**
     * Constructeur qui prend un noeud en parametre et crée une une EB
     * @param r
     */
    public EB(Noeud r){
        racine = r;
    }
    /**
     * Constructeur qui prend en parametre un caractère et crée une EB
     * @param c
     */
    public EB(char c){
        racine = new Noeud(c);

    }
    /**
     * Constructeur par recopie qui crée une EB identique à celle passée en paramètre
     * @param e
     */
    public EB(EB e){
        racine = e.racine;
    }

    /**
     * Recupere la racine
     * */
    public Noeud getRacine(){
        return racine;
    }

    /**
     *  Verifie si l'expression booléenne est vide
     *  
     * */
    public boolean isVide(){
        return racine == null;
    }

    /**
     * Methode qui en paramètre un caractère et construit une EB 
     * @param c
     * @return
     */
    public static EB singleton(char c){
        return new EB(c);
    }

    /**
     * Methode qui permet de faire la conjonction de deux EB
     * @param a
     * @return 
     */
    public EB conjonction(EB a){
        if (a.isVide()){
            return null;
        }
        EB conjonct = new EB('∧');

        conjonct.racine.setDroit(this.getRacine());
        conjonct.racine.setGauche(a.getRacine());
        return conjonct;
    }

    /**
     * Methode qui permet de faire la conjonction de deux EB
     * @param a
     * @return
     */
    public EB disjonction(EB a){
        if (a.isVide()){
            return null;
        }
        EB disjonct = new EB('∨');

        disjonct.racine.setGauche(this.getRacine());
        disjonct.racine.setDroit(a.getRacine());
        return disjonct;
    }

    /**
     * Methode qui permet de faire la negation d'une EB
     * @return la negation de !A
     */
    public EB negation(){
        if (isVide()){
            return null;
        }
        EB neg = new EB('¬');
        neg.racine.setDroit(this.getRacine());
        return neg;
    }

    /**
     * Methode d'affichage qui utilise un parcours infixe(recursif)
     * @return 
     */
    public String toString() {
    return parcoursInfixe(racine, true);
    }

    /**
     * Methode de parcours recursif qui parcours les noeuds à partir de la racine 
     * et les affichent de façon infixe(Gauche_Racine(Operateur)_Droit)
     * @param n
     * @param estRacineSousNoeud
     * @return
     */
    private String parcoursInfixe(Noeud n, boolean estRacineSousNoeud) {
        if (n == null) {
            return "";
        }

        String res = "";
        boolean estOperateur = n.getContenu() == '∧'
                            || n.getContenu() == '¬'
                            || n.getContenu() == '∨';

        if (estRacineSousNoeud && estOperateur) {
            res += "(";
        }

        res += parcoursInfixe(n.getGauche(), estOperateur);
        res += n.getContenu();
        res += parcoursInfixe(n.getDroit(), estOperateur);

        if (estRacineSousNoeud && estOperateur) {
            res += ")";
        }

        return res;
    }

    /**
     * Methode d'evaluation qui permet d'évaluer une expression booléenne (EB)
     * en fonction d'un vecteur de valeurs booléennes.
     * @param e
     * @param bVect
     * @return le resultat de l'evaluation
     */
    public boolean evaluation(EB e, boolean [] bVect) {
        if(isVide()) {
            return false;
        }
        return evaluationRec(e.racine, bVect);
    }

    /**
     * Methode d'evaluation Recursive qui permet de parcourir l'arbre de l'expression booléenne
     * et d'évaluer chaque noeud en fonction de ses opérateurs et de ses sous-arbres.
     * @param r
     * @param bVect
     * @return 
     */
    private boolean evaluationRec(Noeud r, boolean [] bVect) {
        if(r.getContenu() == '∨') {
            return evaluationRec(r.getGauche(), bVect) || evaluationRec(r.getDroit(), bVect);
        }

        if(r.getContenu() =='∧') {
            return evaluationRec(r.getGauche(), bVect) && evaluationRec(r.getDroit(), bVect);
        }

        if(r.getContenu() == '¬') {
            return !evaluationRec(r.getDroit(), bVect);
        }
        else {
            return bVect[r.getContenu() - 'a'];
          
        }
     }

}