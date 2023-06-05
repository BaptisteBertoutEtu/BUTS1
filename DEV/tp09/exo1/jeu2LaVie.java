class Jeu2LaVie extends Program{
    final char GRAND='@';
    final char PETIT='.';

    void afficher(boolean[][] monde){
        for(int cpt=0;cpt<length(monde,1);cpt++){
            for(int cpt2=0;cpt2<length(monde,2);cpt2++){
                if(monde[cpt][cpt2]){
                    print(GRAND);
                }
                else{
                    print(PETIT);
                }
                
            }
            println();
        }
    }

    void init(boolean[][] tab, double proba){
        for(int cpt=1;cpt<length(tab,1)-1;cpt++){
            for(int cpt2=1;cpt2<length(tab,2)-1;cpt2++){
                tab[cpt][cpt2]=random()<proba;
            }
        }
    }

    int nombreDeVoisins(boolean[][] monde, int lig, int col){
        int nbrVoisin=0;
        for(int cpt=lig-1;cpt<=lig+1;cpt++){
            
            for(int cpt2=col-1;cpt2<=col+1;cpt2++){
                
                if(monde[cpt][cpt2]){
                    nbrVoisin+=1;
                }
            }
        }
        if (monde[lig][col]){
            nbrVoisin-=1;
        }
        return nbrVoisin;
    }

    boolean evolution(int nombreDeVoisins, boolean etatCellule){
        return (etatCellule&&nombreDeVoisins==2)||(nombreDeVoisins==3);
    }

    void testEvolution () {
        assertEquals(false,evolution(0, false));
        assertEquals(false,evolution(0, true));
        assertEquals(false,evolution(1, false));
        assertEquals(false,evolution(1, true));
        assertEquals(false,evolution(2, false));
        assertEquals(true,evolution(2, true));
        assertEquals(true,evolution(3, false));
        assertEquals(true,evolution(3, true));
        assertEquals(false,evolution(4, false));
        assertEquals(false,evolution(4, true));
    }

    void testNombreDeVoisins() {
    // Initialiser un monde exemple
        boolean[][] monde =
        new boolean[][]{{false, false, false, false, false},
        {false, false, false, false, false},
        {false, true, true, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};

        assertEquals(2, nombreDeVoisins(monde, 2, 2));
        assertEquals(2, nombreDeVoisins(monde, 1, 1));
        assertEquals(3, nombreDeVoisins(monde, 3, 2));
    }

    void ajouterCarre (boolean[][] monde, int lig, int col){
        // @@
        // @@
        for(int cpt=lig;cpt<=lig+1;cpt++){
            for(int cpt2=col;cpt2<=col+1;cpt2++){
                monde[cpt][cpt2]=true;
            }
        }
    }

    void ajouterGlisseur (boolean[][] monde, int lig, int col){
        // .@.
        // ..@
        // @@@
        monde[lig][col]=false;
        monde[lig][col+1]=true;
        monde[lig][col+2]=false;

        monde[lig+1][col]=false;
        monde[lig+1][col+1]=false;
        monde[lig+1][col+2]=true;

        monde[lig+2][col]=true;
        monde[lig+2][col+1]=true;
        monde[lig+2][col+2]=true;
    }

    void ajouterBarre (boolean[][] monde, int lig, int col){
        for(int cpt=col;cpt<col+3;cpt++){
            monde[lig][cpt]=true;
        }
    }

    void algorithm() {
        boolean [][] monde = new boolean[20][20];
        boolean [][] mondeN;
        int generation = 0;
        boolean stop = false;

        init(monde, 0.1);
        // Ici, on pourra par la suite ajouter des motifs (carré, glisseur, barre, autres)
        //=> voir question suivante
        ajouterCarre(monde,3,5);
        ajouterGlisseur(monde,8,11);
        ajouterBarre(monde,10,6);
        afficher(monde);
        

        while(!stop){
            generation = generation +1;

            mondeN = new boolean[length(monde,1)][length(monde,2)];
            // Complétez ici par la double boucle qui calcule la
            // génération suivante dans mondeN
            int nbrVoisin;
            for(int cpt=1; cpt<length(monde,1)-1;cpt++){
                for(int cpt2=1;cpt2<length(monde,2)-1;cpt2++){
                    nbrVoisin=nombreDeVoisins(monde,cpt,cpt2);
                    mondeN[cpt][cpt2]=evolution(nbrVoisin,monde[cpt][cpt2]);
                }
            }

            monde = mondeN;

            println("Génération "+generation);
            afficher(monde);
            println("Entrez \"stop\" pour arrêter, ou entrée pour continuer");

            stop = equals("stop",readString());
        }
    }
}