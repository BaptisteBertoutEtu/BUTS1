class Majuscule extends Program {
    void algorithm(){
        String texte=readString();
        int compteur=0;
        for(int cpt=0; cpt<length(texte); cpt+=1){
            String carac=substring(texte,cpt,cpt+1);
            if(carac==toUpperCase(carac)){
                compteur+=1;
            }
        }
        println(compteur);
    }
}