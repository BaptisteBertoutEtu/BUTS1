class JeuxDeMots extends Program {
    void algorithm(){
        String mot = "univ";
        String resultat;
        char premiereLettre = charAt(mot, 0);
        String resteDuMot = substring(mot, 1, length(mot));
        resultat = resteDuMot + premiereLettre;
        println(resultat);
    }
}


//question 2 :