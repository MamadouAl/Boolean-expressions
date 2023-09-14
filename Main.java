public class Main {
    public static void main(String[] args) {
    /* CREATION DES EXPRESSION BOOLEENNE */
    System.out.println("***************** TAD Expression Booléenne *****************");
    System.out.println();

        /* Première expression booleenne */
        Noeud a = new Noeud('a');

        EB A = new EB(a);
        EB B = new EB('b');
        EB C = new EB('c');
        EB D = EB.singleton('d');
       

        // e = (¬a)∨(b∧c)∧(¬c)
        EB negA = A.negation(); //  ¬a
        EB BC = B.conjonction(C); // b∧c
        EB abc = negA.disjonction(BC); // (¬a)∨(b∧c)
        EB negC = C.negation(); //  ¬c

        EB e = abc.conjonction(negC); // e = (¬a)∨(b∧c)∧(¬c)
        System.out.println("e = "+e);
        System.out.println();

        System.out.println("1) Evaluation de e : " + e.evaluation(e, new boolean[]{true, false, false}));
        System.out.println("2) Evaluation de e : " + e.evaluation(e, new boolean[]{false, false, false}));


        /* Deuxieme Exemple d'expression booleenne */
            //f = ¬((¬a∧(c ∨ a)) ∨¬(b ∧ d))

        EB f = ((EB.singleton('a').negation().conjonction(EB.singleton('c').disjonction(EB.singleton('a')))).disjonction( //( ¬a ∧ (c ∨ a)) ∨¬(b ∧  d
            ((EB.singleton('d').conjonction(EB.singleton('b'))).negation()) //¬(b ∧  d))
        )).negation();

        System.out.println();
        System.out.println("f = "+f);
        System.out.println();
        System.out.println("*************** Evaluation **************");

        boolean[] bVect1 = new boolean[]{true, true, false, true, true};
        System.out.println("1) Evaluation de f : " + f.evaluation(f, bVect1));

        boolean[] bVect2 = new boolean[5];
        bVect2[0] = false;
        bVect2[1] = true;
        bVect2[2] = false;
        bVect2[3] = true;
        bVect2[4] = false;
        System.out.println("2) Evaluation de f : " + f.evaluation(f, bVect2));

    }
}