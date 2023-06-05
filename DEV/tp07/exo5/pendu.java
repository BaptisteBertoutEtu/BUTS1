class Pendu extends Program{

    boolean estPresente(char carac, char[] tab){
        boolean present=false;
        int cpt=0;
        while (!present && cpt<length(tab)){
            present=tab[cpt]==carac;
            cpt+=1
        }
        return present;
    }

    boolean trouve(char carac, char[] tab){
        
    }

    char[] tableau(String mot){
        char[] tab = int[length(mot)];
        for (int cpt=0; cpt<length(mot); cpt+=1){
            tab[cpt]=charAt(mot,cpt);
        }
        return tab
    }
    
    char[] tabEtoile(int taille){
        char[] tab=new int[taille];
        for (int cpt=0; cpt<taille;cpt+=1){
            tab[cpt]="*";
        }
        return tab;
    }

    char[] tabCarac(char carac){
        char[] tabEtoile=tabEtoile()
    }


    String afficher(int taille){
        char[] etoile=tabEtoile(taille);
        String chaine="";
        for (int cpt=0; cpt<taille;cpt+=1){
            chaine+="*"
        }

    }


    void algorithm(){
        String mot="unix";
        char[] tableauMot=tableau(mot);
        String chaine=afficherEtoile(length(mot));
        int nbTentative=5;
        char carac;
        boolean caracTrouve;
        int indice;
        do{

            println("il vous reste "+nbTentative+":"+chaine);
            print("Entrer un caractère :");
            carac =readChar();
            caracTrouve=estPresente(carac,tableauMot);
            if (caracTrouve){
                
            }
            

        }while(! && nbTentative!=0);
        if(trouve())
    }

}