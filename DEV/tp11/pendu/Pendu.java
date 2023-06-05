class Pendu extends Program{

    Lettre newLettre(char car){
        Lettre l = new Lettre();
        l.car=car;
        return l;
    }

    void testNewLettre(){

        assertEquals(newLettre('A').car,'A');
        assertFalse(newLettre('A').decouverte);

        assertEquals(newLettre('U').car,'U');
        assertFalse(newLettre('U').decouverte);
    }

    Lettre[] newMot(String mot){
        Lettre[] tab=new Lettre[length(mot)];

        for (int cpt=0;cpt<length(mot); cpt++){
            tab[cpt]=newLettre(charAt(mot,cpt));
        }
        return tab;
    }

    void testNewMot(){
        Lettre[] mot=newMot("unix");

        assertEquals(length(mot),4);
        assertEquals(mot[0].car,'u');
        assertEquals(mot[2].car,'i');
    }

    String toString (Lettre lettre){
        if(!lettre.decouverte){
            return "*";
        }
        else{
            return ""+lettre.car;
        }
    }

    void testToStringLettre(){
        Lettre car1=newLettre('a');
        assertEquals(toString(car1),"*");

        Lettre car2=newLettre('b');
        car2.decouverte=true;
        assertEquals(toString(car2),"b");
    }

    String toString (Lettre[] mot){
        String chaine="";
        for (int cpt=0; cpt<length(mot); cpt++){
            chaine+=toString(mot[cpt]);
        }
        return chaine;
    }

    void testToStringMot(){
        Lettre[] mot=newMot("unix");

        assertEquals(toString(mot),"****");
    }


    boolean estDecouvert(Lettre[] mot){
        int cpt=0;
        for(int cpt1=0; cpt1<length(mot);cpt1++){
            if(mot[cpt1].decouverte){
                cpt++;
            }
        }
        return cpt==length(mot);
    }

    void testEstDecouvert(){
        Lettre[] tab=newMot("unix");
        tab[0].decouverte=true;
        tab[1].decouverte=true;
        tab[2].decouverte=true;
        tab[3].decouverte=true;
        Lettre[] tab1=newMot("unix");
        tab1[2].decouverte=true;
        Lettre[] tab2=newMot("");

        assertTrue(estDecouvert(tab));
        assertFalse(estDecouvert(tab1));
        assertTrue(estDecouvert(tab2));
    }

    boolean decouvrir(Lettre[] mot, char car){
        int fin=0;
        for (int cpt=0; cpt<length(mot); cpt++){
            if(mot[cpt].car==car){
                mot[cpt].decouverte=true;
                fin++;
            }
        }
        return fin!=0;
    }

    void testDecouvrir(){
        Lettre[] mot=newMot("unix");
        assertTrue(decouvrir(mot,'u'));
        assertFalse(decouvrir(mot,'p'));
    }

    void algorithm(){
        String mot="unix";
        Lettre[] tabMot=newMot(mot);
        int nbTentative=5;
        String chaine=toString(tabMot);
        char carac;
        do{

            println("il vous reste "+nbTentative+" tentatives : "+chaine);
            print("Entrer un caractère :");
            carac =readChar();
            decouvrir(tabMot,carac);
            if(!decouvrir(tabMot,carac)){
                nbTentative--;
            }
            chaine=toString(tabMot);
            

        }while(!estDecouvert(tabMot) && nbTentative!=0);
        if(estDecouvert(tabMot)){
            println("Bravo, vous avez découvert le mot : "+ mot);
        }
        else{
            println("Bravo, vous avez découvert le mot : "+ mot);
        }
    }
}