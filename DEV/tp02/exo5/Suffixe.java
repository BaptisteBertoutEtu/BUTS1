class Suffixe extends Program{
    void algorithm(){
        print("Entrez une chaîne de caractère : ");
        String chaine=readString();
        print("Nombre de lettres de fin souhaitées : ");
        int nbLettres=readInt();
        boolean assez_de_lettre= length(chaine)>=0 && nbLettres>=0 && length(chaine)>nbLettres;
        if (assez_de_lettre){
            String resultat= substring(chaine,length(chaine)-nbLettres,length(chaine));
            println("Résultat : "+resultat);
        }
        else{
            println("Erreur, pas assez de caractères");
        }
    }
}