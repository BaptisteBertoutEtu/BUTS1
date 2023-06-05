class detecterSousChaine extends Program{

    boolean sousChaine(String chaine,String schaine){
        boolean verifier=false;
        int n=0;
        while (n<length(chaine)-length(schaine)+1 && !verifier){
        verifier=equals(substring(chaine,n,n+length(schaine)),schaine);
        n+=1;
        }
        return verifier;
    }


    void algorithm(){
        String chaine=readString();
        String schaine=readString();
        boolean sousChainePresente=sousChaine(chaine,schaine);
        if (!sousChainePresente){
            println("pas trouvé");
        }
        else{
            println("trouvé");
        }
    }
}