// 1)
class IUT extends Program{
    void dessineLigne(int n, char c) {
        for (int i=0; i<n; i=i+1) {
        print(c);
        }
        println();
    }

    void dessineMilieu(int n, char c){
        for (int cpt=0;cpt<n/2;cpt+=1){
            print(" ");
        }
        println(c);
    }

    void dessinerExtremite(int n, char c){
        print(c);
        for (int cpt=0; cpt<n-2;cpt +=1){
            print(" ");
        }
        println(c);

    }

    void dessinerI(int taille){
        dessineLigne(taille,'I');
        for (int cpt=1;cpt<taille-1;cpt+=1){
            dessineMilieu(taille,'I');
        }
        dessineLigne(taille,'I');
        }

    void dessinerU(int taille){
        for (int cpt=0;cpt<taille-1;cpt+=1){
            dessinerExtremite(taille,'U');
        }
        dessineLigne(taille,'U');
    }

    void dessinerT(int taille){
        dessineLigne(taille,'T');
        for (int cpt=1;cpt<taille;cpt+=1){
            dessineMilieu(taille,'T');
        }    
    }


    void algorithm(){
        int taille=readInt();
        println();
        dessinerI(taille);
        println();
        dessinerU(taille);
        println();
        dessinerT(taille);

    }
}

