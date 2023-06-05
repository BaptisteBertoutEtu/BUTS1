class Ticket_de_caisse extends Program{
    void algorithm(){
        int nombre_entree=readInt();
        int somme=0;
        while(nombre_entree!=0){
            nombre_entree=readInt();
            somme=somme+nombre_entree;
        }
        println("total = "+somme);
    }
}