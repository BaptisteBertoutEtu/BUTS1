class Nombre extends Program{
    void algorithm(){
        String texte=readString();
        int nombre_mot=0;
        for(int cpt=0; cpt<length(texte); cpt+=1){
            if(length(texte)==0 ^ (charAt(texte,cpt)==' ' && length(texte)==1)){
                println("0");
            }
            else if((charAt(texte,cpt)==' ' && length(texte)!=1) ^ (charAt(texte,cpt)==' ' && length(texte)==1)){
                nombre_mot=1
            }
        }
    }
}