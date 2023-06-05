class voteMajoritaire extends Program{

    boolean estAdopte(boolean[] tab){
        int cptTrue=0;
        for (int cpt=0; cpt<length(tab);cpt+=1){
            if (tab[cpt]==true) cptTrue+=1;
        }
        return cptTrue>(length(tab)/2);
    }

    void testEstAdopte(){
        assertFalse(estAdopte(new boolean[]{true,false}));
        assertTrue(estAdopte(new boolean[]{true,true,false}));
    }
}