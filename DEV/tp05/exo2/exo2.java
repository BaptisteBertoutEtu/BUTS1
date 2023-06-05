class Saison extends Program {
    String saisonMeteorologique (int mois) {
        String chaine;

        if (mois>12 || mois <1){
            chaine="erreur";
        }
        else if (mois>=9 && mois <=11){
            chaine="automne";
        }
        else if (mois>=3 && mois<=5){
            chaine="printemps";
        }
        else if (mois>=6 && mois<=8){
            chaine="été";
        }
        else{
            chaine="hiver";
        }
        return chaine;
    }


    int nombreJoursMois(int m){
        int entier;
        if (m>12 || m<1){
            entier=0;
        }
        else if ((m%2==0 && m<7) || (m%2==1 && m>8)){
            entier=30;
        }
        else{
            entier=31;
        }
        if (m==2){
            entier=28;
        }
        return entier;
    }



    String saisonAstronomique (int jour, int mois){
        String saison = saisonMeteorologique(mois);
        if (jour<21 && mois%3==0){
            saison = saisonMeteorologique(mois-1);
        }
        return saison ;
    }



    void algorithm () {
        //int total=0;
        for (int m=1;m<=12;m=m+1){
            for (int cpt=1;cpt<=nombreJoursMois(m);cpt+=1){
                ///total+=nombreJoursMois(m);
                println(cpt+"/"+m+" : "+saisonAstronomique(cpt,m));
            }
            
            
        }
        //println("nombre de jours total : "+total);
    }
}