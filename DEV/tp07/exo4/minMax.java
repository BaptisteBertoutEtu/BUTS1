class MinMax extends Program{

    int[] minMax(int[] tab){
        int[] tab2 = new int[] {tab[0],tab[0]};
        for (int cpt=0; cpt<length(tab); cpt+=1){
            if (tab[cpt]>tab2[1]){
                tab2[1]=tab[cpt];
            }
            else if (tab[cpt]<tab2[0]){
                tab2[0]=tab[cpt];
            }
        }
        return tab2;
    }

    void testMinMax() {
        assertArrayEquals(new int[]{1,3}, minMax(new int[]{2,1,3}));
        assertArrayEquals(new int[]{2,2}, minMax(new int[]{2,2,2}));
        assertArrayEquals(new int[]{-1,3}, minMax(new int[]{3,-1,2}));
    }


    int[] minMaxIndices(int[] tab){
        int[] indice = new int[] {0,0};
        for (int cpt=0; cpt<length(tab); cpt+=1){
            if (tab[cpt]>tab[indice[1]]){
                indice[1]=cpt;
            }
            else if (tab[cpt]<tab[indice[0]]){
                indice[0]=cpt;
            }
        }
        return indice;
    }


    void testMinMaxIndices() {
        assertArrayEquals(new int[]{1,2}, minMaxIndices(new int[]{2,1,3}));
        assertArrayEquals(new int[]{0,0}, minMaxIndices(new int[]{2,2,2}));
        assertArrayEquals(new int[]{1,0}, minMaxIndices(new int[]{3,-1,2}));
    }
}