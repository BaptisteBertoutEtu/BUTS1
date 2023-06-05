class Moment extends Program{
    void algorithm(){
        int entree=readInt();
        if(entree<0 || entree>23){
            println("erreur");
        }
        else if(entree>=6 && entree<12){
            println("matinée");
        }
        else if(entree>=12 && entree<18){
            println("après-midi");
        }
        else if(entree>=18 && entree<22){
            println("soiréé");
        }
        else{
            println("nuit");
        }
    }
}