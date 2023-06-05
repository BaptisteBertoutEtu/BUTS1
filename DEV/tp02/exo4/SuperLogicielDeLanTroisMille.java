class SuperLogicielDeLanTroisMille extends Program{
    void algorithm(){
        println("Bienvenue dans le SuperLogicielDeLanTroisMille\n");
        println("1. Ouvrir un document existant.\n2. Créer un nouveau document.\n3. Enregistrer le document courant.\n4. Quitter ce magnifique logiciel.\n");
        print("Veuillez entrer votre choix: ");
        int choix=readInt();

        if (choix<1 || choix >4){
            println("Veuillez choisir un chiffre valide");
        }
        else{
            if (choix==1){
                println("vous avez choisi : \"Ouvrir un document existant.");
            }
            if (choix==2){
                println("vous avez choisi : \"Créer un nouveau document.");
            }
            if (choix==3){
                println("vous avez choisi : \"Enregistrer le document courant.");
            }
            if (choix==4){
                println("vous avez choisi : \"Quitter ce magnifique logiciel.");
            }
        }
    }
}