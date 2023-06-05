import extensions.File;

class JeuMots extends Program{

    final String NOM_REPERTOIRE = "categories";

    void algorithm(){
        String nomFichier = choixFichier(NOM_REPERTOIRE);
        int nb= nbLignes(NOM_REPERTOIRE+"/"+nomFichier);
        println(nomFichier+" "+nb);
	    Categorie categorie = chargerCategorie(NOM_REPERTOIRE+"/"+nomFichier);
	    affichageCategorie(categorie);
    }

    

    String choixFichier(String nomRepertoire){
        int choice;
        int count=0;
        String[] tab=getAllFilesFromDirectory(nomRepertoire);
        for(int cpt=0;cpt<length(tab);cpt++){
            println(count+" "+tab[cpt]);
            count++;
        }
        println("Faite un choix : ");
        choice=readInt();
        return tab[choice];
    }
    
    int nbLignes(String nomFichier){
        File f = newFile(nomFichier);
        int nbLigne=0;
        while(ready(f)){
            readLine(f);
            nbLigne+=1;
        }
        return nbLigne;
    }
    
    Categorie chargerCategorie(String cheminFichier){
        File f = newFile(cheminFichier);
        String nom=readLine(f);
        String mot;
        String[] tabMot= new String[nbLignes(cheminFichier)-1];
        int cpt=0;
        while(ready(f)){
            mot=readLine(f);
            tabMot[cpt]=mot;
            cpt++;
        }
        return newCategorie(nom,tabMot);

    }

    Mot newMot(String mot){
        Mot m = new Mot();
        m.mot = mot;
        m.valide = false;
        m.trouve = false;
        return m;
    }

    Categorie newCategorie(String nom, String[] mots){
        Categorie c = new Categorie();
        c.nom = nom;
        c.mots = new Mot[length(mots)];
        for (int i=0; i<length(mots);i++){
            c.mots[i] = newMot(mots[i]);
        }
        return c;
    }

    void affichageCategorie(Categorie categorie){
        println(categorie.nom + " : ");
        for (int i=0;i<length(categorie.mots);i++){
                Mot m = categorie.mots[i];
                println(m.mot);
        }
    }

}
