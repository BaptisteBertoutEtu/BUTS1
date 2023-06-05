class ParcVehicule extends Program{
    final int NB_MAX=100;

    Vehicule newVehicule (String modele, int annee){
        Vehicule v=new Vehicule();
        v.modele=modele;
        v.annee=annee;
        return v;
    }

    String toString (Vehicule v){
        return "" + v.modele + " "+ v.annee;
    }

    void testToStringVehicle(){
        assertEquals("Yaris 2020", toString(newVehicule("Yaris", 2020)));
    }

    Parc newParcVide(){
        Parc p=new Parc();
        p.vehicule= new Vehicule[NB_MAX];
        return p;
    }

    boolean ajouterVehicule (Parc parking, Vehicule v){
        if(parking.nbVehicule!=NB_MAX){
            parking.vehicule[parking.nbVehicule]=v;
            parking.nbVehicule+=1;
            return true;
        }
        else{
            return false;
        }
    }

    String toString (Parc parking){
        String chaine="";
        for(int cpt=0; cpt<parking.nbVehicule;cpt++){
            chaine+=toString(parking.vehicule[cpt])+"\n";
        }
        return chaine;
    }

    void testToStringParc(){
        Parc p = newParcVide();
        Vehicule v1 = newVehicule("Yaris", 2020);
        Vehicule v2 = newVehicule("Touran", 2019);
        ajouterVehicule(p, v1);
        ajouterVehicule(p, v2);
        assertEquals(toString(p),"Yaris 2020\nTouran 2019\n");
    }

    int nbVoitures (Parc p, String modele){
        int nb=0;
        for(int cpt=0; cpt<p.nbVehicule; cpt++){
            if(equals(p.vehicule[cpt].modele, modele)){
                nb++;
            }
        }
        return nb;
    }

    void testNbVoitures(){
        Parc p = newParcVide();
        Vehicule v1 = newVehicule("Yaris", 2020);
        Vehicule v2 = newVehicule("Touran", 2019);
        ajouterVehicule(p, v1);
        ajouterVehicule(p, v2);
        assertEquals(nbVoitures(p,"Touran"),1);
    }


    boolean fusionParcs (Parc p1, Parc p2){
        int cpt=0;
        if(p1.nbVehicule>p2.nbVehicule){
            while(p2.nbVehicule!=0 && p1.nbVehicule!=NB_MAX){
                p1.vehicule[p1.nbVehicule]=p2.vehicule[cpt];
                p2.nbVehicule--;
                p1.nbVehicule++;
                cpt++;
            }
            return p2.nbVehicule==0;
        }
        else{
            while(p1.nbVehicule!=0 && p2.nbVehicule!=NB_MAX){
                p2.vehicule[p2.nbVehicule]=p1.vehicule[cpt];
                p1.nbVehicule--;
                p2.nbVehicule++;
                cpt++;
            }
            return p1.nbVehicule==0;
        }
        
    }


    void testFusionParc(){
        Parc p1 = newParcVide();
        Vehicule v1 = newVehicule("Yaris", 2020);
        Vehicule v2 = newVehicule("Touran", 2019);
        Vehicule v3 = newVehicule("Kadjar", 2018);
        ajouterVehicule(p1, v1);
        ajouterVehicule(p1, v2);
        ajouterVehicule(p1, v3);

        Parc p2 = newParcVide();
        Vehicule v11 = newVehicule("Yaris1", 2020);
        Vehicule v22 = newVehicule("Touran2", 2019);
        Vehicule v33 = newVehicule("Kadjar3", 2018);
        Vehicule v44 = newVehicule("captur", 2018);
        ajouterVehicule(p2, v11);
        ajouterVehicule(p2, v22);
        ajouterVehicule(p2, v33);
        ajouterVehicule(p2, v44);
        
        assertTrue(fusionParcs(p1,p2));
        println(toString(p1));
        println(toString(p2));
    }


    void suppressionVehicule (Parc p, Vehicule v){
        int cpt=0;
        while(cpt!=p.nbVehicule && p.vehicule[cpt]!=v){
            cpt++;
        }
        if(cpt!=p.nbVehicule){
            p.vehicule[cpt]=p.vehicule[p.nbVehicule-1];
            p.nbVehicule--;
        }
    }

    void testSuppressionVehicule(){
        Parc p = newParcVide();
        Vehicule v1 = newVehicule("Yaris", 2020);
        Vehicule v2 = newVehicule("Touran", 2019);
        Vehicule v3 = newVehicule("Kadjar", 2018);
        ajouterVehicule(p, v1);
        ajouterVehicule(p, v2);
        ajouterVehicule(p, v3);
        assertEquals(toString(p),"Yaris 2020\nTouran 2019\nKadjar 2018\n");
        suppressionVehicule(p,v2);
        assertEquals(toString(p),"Yaris 2020\nKadjar 2018\n");
    }

}