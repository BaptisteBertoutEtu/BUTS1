class Remplacement extends Program{
    void algorithm(){
        String txt;
        char ancien, nouveau;
        println("Veuillez saisir votre texte : ");
        txt = readString();
        print("Caractère à remplacer : ");
        ancien = readChar();
        print("Caractère de remplacement : ");
        nouveau = readChar();

        String chaine="";
        for(int cpt=0; cpt<length(txt); cpt+=1){
            if(charAt(txt,cpt)==ancien){
                chaine+=nouveau;
            }
            else{
                chaine+=charAt(txt,cpt);
            }
        }
        println(chaine);
    }
}