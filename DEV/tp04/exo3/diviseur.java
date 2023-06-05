class Diviseur extends Program{
    void algorithm(){
        int nombre_entre=readInt();
        String list_diviseur="Diviseurs : ";
        int somme=0;
        if (nombre_entre==0){
            println("pas de diviseur");
        }
        else{
            for (int cpt=nombre_entre;cpt>=nombre_entre/2;cpt-=1){
                if (nombre_entre%cpt==0){
                    list_diviseur= list_diviseur+cpt+", ";
                    somme+=cpt;
                }
            }
            list_diviseur += 1;
            somme+=1;
            println(list_diviseur);
            if (somme==nombre_entre*2){
                println("nombre parfait");
            }
        }
        
    }
}