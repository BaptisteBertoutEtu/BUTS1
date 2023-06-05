class Demineur extends Program{

    final String ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    void afficher(boolean[][] champ, boolean[][] carte){
        print();
        for(int lng=0;lng<lentgh(champ[1]);lng++){
            print(charAt(ALPHABET,lng));
        }

        for(int cpt=0; cpt<length(champ,1);cpt++){
            for(int cpt2=0;cpt2<length(champ,2);cpt2++){
                if (!carte[cpt][cpt2]){
                    print('?');
                }
                else if(carte[cpt][cpt2] && !champ[cpt][cpt2]){
                    print('.');
                }
                else{
                    print('@');
                }
            }
            println();
        }
    }

    void algorithm(){
        boolean[][] champ= new boolean[][]{{false, false, false, false, false},
        {false, false, false, false, false},
        {false, true, true, true, false}};

        boolean[][] champ= new boolean[][]{{false, false, false, false, false},
        {false, false, false, false, false},
        {false, true, true, true, false}};
    }
}