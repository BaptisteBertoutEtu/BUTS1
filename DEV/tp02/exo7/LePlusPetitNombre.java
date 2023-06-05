/*class LePlusPetitNombre extends Program{
    void algorithm(){
        println("Entrez deux nombres :");
        int nb1=readInt();
        int nb2=readInt();
        if (nb1>nb2){
            println("Le plus petit est "+nb2);
        }
        else{
            println("Le plus petit est "+nb1);
        }
    }
}*/

class LePlusPetitNombre extends Program{
    void algorithm(){
        println("Entrez 3 nombres :");
        int nb1=readInt();
        int nb2=readInt();
        int nb3=readInt();
        if (nb1>nb2 && nb2>nb3){
            println("Le plus petit est "+nb3);
        }
        else if(nb1>nb2 && nb1<nb3){
            println("Le plus petit est "+nb2);
        }
        else if(nb1>nb3 && nb2<nb3){
            println("Le plus petit est "+nb2);
        }
        else{
            println("Le plus petit est "+nb1);
        }
    }
}