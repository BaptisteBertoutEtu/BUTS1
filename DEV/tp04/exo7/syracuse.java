class Syracuse extends Program{
    void algorithm(){
        int nombre=readInt();
        String chaine="trajectoire : "+nombre;
        int altitude_max=nombre;
        int duree_de_vol=0;
        int duree_de_vol_en_altitude=0;

        int valeur_depart=nombre;
        while (nombre>1){
            
            if (nombre%2==0){
                nombre/=2;
            }
            else{
                nombre=nombre*3 +1;
            }
            chaine+=", "+nombre;
            altitude_max=max(nombre,altitude_max);
            duree_de_vol+=1;
           
           if (nombre>valeur_depart){
                duree_de_vol_en_altitude+=1;
            }

        }
        println(chaine);
        println("altitude max : "+altitude_max);
        println("durée de vol : "+duree_de_vol);
        println("duree de vol en altitude : "+duree_de_vol_en_altitude);
    }


int max(int nombre,int altitude_max){
    if (nombre>altitude_max){
        return nombre;
    }
    return altitude_max;
}


}