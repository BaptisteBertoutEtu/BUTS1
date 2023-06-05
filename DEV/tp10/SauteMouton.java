class SauteMouton extends Program{
    final char G = '<'; // mouton qui va vers la gauche
    final char D = '>'; // mouton qui va vers la droite
    final char V = '_'; // l’espace vide de la prairie


    void initialiser(char[] prairie){
        for(int cpt=0; cpt<length(prairie)/2;cpt++){
            prairie[cpt]=D;
        }
        prairie[length(prairie)/2]=V;
        for(int cpt2=length(prairie)/2+1; cpt2<length(prairie);cpt2++){
            prairie[cpt2]=G;
        }
    }

    String toString(char[] prairie){
        String chaine="";
        for(int cpt=0; cpt<length(prairie); cpt++){
            chaine+=prairie[cpt];
        }
        return chaine;
    }

    void effectuerDeplacement(int positionMouton, char[] prairie){
        int indice=indiceCaseVide(prairie);
        char temp=prairie[positionMouton];
        prairie[positionMouton]=V;
        prairie[indice]=temp;
    }

    void testEffectuerDeplacement () {
        char[] prairie;

        // de la prairie >>>_<<< en déplaçant le mouton 2, on doit passer à la prairie >>_><<<
        prairie = new char[]{D,D,D,V,G,G,G};
        effectuerDeplacement(2, prairie);
        assertArrayEquals(new char[]{D,D,V,D,G,G,G}, prairie);

        // de la prairie >>_><<< en déplaçant le mouton 4, on doit passer à la prairie >><>_<<
        prairie = new char[]{D,D,V,D,G,G,G};
        
        effectuerDeplacement(4, prairie);
        assertArrayEquals(new char[]{D,D,G,D,V,G,G}, prairie);

        
    }

    int indiceCaseVide(char[] prairie){
        int indice=0;
        for (int cpt=0; cpt<length(prairie);cpt++){
            if(prairie[cpt]==V){
                indice=cpt;
            }
        }
        return indice;
    }

    void testIndiceCaseVide () {
        char[] prairie;
        prairie = new char[]{D,D,D,V,G,G,G};
        assertEquals(3, indiceCaseVide(prairie));

        prairie = new char[]{D,D,V,D,G,G,G};
        assertEquals(2, indiceCaseVide(prairie));
        
    }

    boolean estPositionValide(int positionMouton, char[] prairie){
        int indice=indiceCaseVide(prairie);
        boolean reponse=positionMouton>=0 && positionMouton<length(prairie)&&positionMouton!=indice;

        if(reponse){
            if(prairie[positionMouton]==D && (indice-positionMouton>2 ^ indice<positionMouton)){
                reponse=false;
            }
            else if(prairie[positionMouton]==G && (positionMouton-indice>2 ^ indice>positionMouton)){
                reponse=false;
            }

        }
        
        return reponse;
        
        
    }

    void testEstPositionValide () {
        char[] prairie;
        // un indice en dehors des indices du tableau n’est jamais valide, quel que
        // soit le contenu de la prairie
        prairie = new char[7];
        assertFalse(estPositionValide(-1, prairie));
        assertFalse(estPositionValide(7, prairie));
        prairie = new char[]{D,D,D,V,G,G,G};
        assertTrue(estPositionValide(2, prairie));
        assertTrue(estPositionValide(1, prairie));
        assertTrue(estPositionValide(4, prairie));
        assertTrue(estPositionValide(5, prairie));
        assertFalse(estPositionValide(0, prairie));
        assertFalse(estPositionValide(3, prairie));
        assertFalse(estPositionValide(6, prairie));
        prairie = new char[]{D,V,D,G,D,G,G};
        // Complétez les ... par True ou False
        assertTrue(estPositionValide(0, prairie));
        assertFalse(estPositionValide(1, prairie));
        assertFalse(estPositionValide(2, prairie));
        assertTrue(estPositionValide(3, prairie));
        assertFalse(estPositionValide(4, prairie));
        assertFalse(estPositionValide(5, prairie));
        assertFalse(estPositionValide(6, prairie));
    }

    int saisirCoup(char[] prairie){
        int coup;
        do{
            print("saisir un entier ");
            coup=readInt();
            

        }while(coup>=0 && coup<length(prairie) && !estPositionValide(coup,prairie));
        return coup;
    }

    boolean configFinaleAtteinte(char[] prairie){
        final String configFinal="<<<_>>>";
        String compar="";
        for(int cpt=0; cpt<length(prairie);cpt++){
            compar+=prairie[cpt];
        }
        return equals(compar,configFinal);
    }

    void testConfigFinaleAtteinte() {
        char[] prairie = new char[]{G,G,G,V,D,D,D};
        // Écrire une assertion qui teste que la configuration finale est atteinte
        // pour la prairie <<<_>>>
        assertTrue(configFinaleAtteinte(prairie));
        // Écrire une assertion qui teste que la configuration finale n’est pas atteinte
        // pour la prairie <<_<>>>
        prairie = new char[]{G,G,V,G,D,D,D};
        assertFalse(configFinaleAtteinte(prairie));
    }

    boolean blocage(char[] prairie){
        boolean bloc=true;
        int indice=indiceCaseVide(prairie);
        if(estPositionValide(indice-2,prairie)){
            bloc=true;
        }
        else if(estPositionValide(indice-1,prairie)){
            bloc=true;
        }
        else if(estPositionValide(indice+1,prairie)){
            bloc=true;
        }
        else if(estPositionValide(indice+2,prairie)){
            bloc=true;
        }
        return bloc;
    }

    void testBlocage () {
        // Écrire une assertion qui teste que cette prairie est un blocage: >><><<_
        char[] prairie=new char[]{D,D,G,D,G,G,V};
        assertTrue(blocage(prairie));
        // Écrire une assertion qui teste que cette prairie est un blocage: <<<_>>>
        prairie=new char[]{G,G,G,V,D,D,D};
        assertTrue(blocage(prairie));
        // Écrire une assertion qui teste que cette prairie n’est pas un blocage: >>>_<<<
        prairie=new char[]{D,D,D,V,G,G,G};
        assertTrue(blocage(prairie));
        // Écrire une assertion qui teste que cette prairie n’est pas un blocage: _<<<>>>
        prairie=new char[]{V,G,G,G,D,D,D};
        assertTrue(blocage(prairie));
    }

    /*void algorithm () {
        char[] prairie = new char[7];
        initialiser(prairie);
        // Pour l’instant on ne peut pas détecter la fin de partie, alors on fera 5 tours de jeu
        for (int i = 1; i <= 5; i++) {
            println("Tour de jeu " + i);
            println(toString(prairie));
            int positionMouton = saisirCoup(prairie);
            effectuerDeplacement(positionMouton, prairie);
            println(toString(prairie)); // pour voir le déplacement qui est fait
        }
    }*/

    /*void algorithm () {
        char[] prairie = new char[7];
        initialiser(prairie); // Fonction à écrire
        println(toString(prairie)); // Fonction à écrire
    }*/
}