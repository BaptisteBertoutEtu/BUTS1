class Conversion extends Program{
    void algorithm(){
        print("ligne : ");
        final double taux=135.9;
        int nombre_ligne=readInt();
        for(int cpt=1; cpt<nombre_ligne+1;cpt=cpt+1){
            println(cpt +"euros = "+cpt*taux+" yens.");
        }
    }
}