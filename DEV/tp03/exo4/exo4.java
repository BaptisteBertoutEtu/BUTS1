/*class Ht extends Program{
    void algorithm(){
        print("ligne : ");
        final double taux=1.196;
        int nombre_ligne=readInt();
        for(int cpt=1; cpt<nombre_ligne+1;cpt=cpt+1){
            println(cpt +"euros HT = "+cpt*taux+" euro TTC.");
        }
    }
}*/

class Ht extends Program{
    void algorithm(){
        print("ligne : ");
        final double taux=1.196;
        int nombre_ligne=readInt();
        print("A partir de ? : ");
        int partir_de=readInt();
        for(int cpt= 0; cpt<nombre_ligne ;cpt=cpt+1){
            println(partir_de+cpt*0.5 +" euros HT = "+(partir_de+cpt*0.5)*taux+" euro TTC.");
        }
    }
}