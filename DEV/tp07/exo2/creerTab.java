class creerTab extends Program{
    
    int[] creerTableau(){
        int[] tab= new int [10];
        for (int cpt=0;cpt<=5;cpt+=1){
            tab[cpt]=1;
        }
        for (int cpt2=5;cpt2<length(tab);cpt2+=1){
             tab[cpt2]=2;
        }
        return tab;
    }

    void testCreerTableau() {
        assertArrayEquals(new int[]{1,1,1,1,1,2,2,2,2,2}, creerTableau());
    }

    int[] creerTableau2(int taille){
        int[] tab= new int [taille];
        int moitie= taille/2;
        for (int cpt=0;cpt<moitie;cpt+=1){
            tab[cpt]=1;
        }
        for (int cpt2=moitie;cpt2<length(tab);cpt2+=1){
             tab[cpt2]=2;
        }
        return tab;
    }

    void testCreerTableau2() {
        assertArrayEquals(new int[]{1,1,1,1,1,2,2,2,2,2}, creerTableau2(10));
        assertArrayEquals(new int[]{1,1,2,2,2}, creerTableau2(5));
        assertArrayEquals(new int[]{}, creerTableau2(0));
    }


    int[] creerTableauAleatoire(int taille){
        int[] tab = new int[taille];
        for (int cpt=0; cpt<taille;cpt+=1){
            tab[cpt]= (int) (random()*21);
        }
        return tab;
    }

    boolean verif(int[] tab){
        boolean verifie=true;
        int cpt=0;
        while (verifie && cpt<length(tab)){
            verifie = tab[cpt]<=20 && tab[cpt]>=0;
            cpt+=1;
        }
        return verifie;
    }

    void testcreerTableauAleatoire(){
        assertTrue(verif(creerTableauAleatoire(10)));
        assertTrue(verif(creerTableauAleatoire(20)));
    }
}