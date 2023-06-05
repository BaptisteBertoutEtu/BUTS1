/*class Temperature extends Program{
    void algorithm(){
        int nombre_entre=readInt();
        int max=nombre_entre;
        while(nombre_entre!=-273){
            if (nombre_entre>max){
                max=nombre_entre;
            }
            nombre_entre=readInt();
        }
        println("le max est : "+max);
    }
}*/

class Temperature_2 extends Program{
    void algorithm(){
        int nombre_entre=readInt();
        int max=nombre_entre;
        int min=nombre_entre;
        int somme=0;
        int cpt=0;
        while(nombre_entre!=-273){
            if (nombre_entre>max){
                max=nombre_entre;
            }
            if(nombre_entre<min){
                min=nombre_entre;
            }
            somme+=nombre_entre;
            cpt+=1;
            nombre_entre=readInt();
        }
        int moyenne=somme/cpt;
        println("le max est : "+max+", le min est : "+min+", la moyenne est : "+moyenne);
    }
}