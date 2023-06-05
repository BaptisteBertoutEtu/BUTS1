class DevinerUnNombre extends Program{
    void algorithm(){
        int milieu=50;
        println("Est-ce que le nombre est : "+milieu+" ?");
        String proposition=readString();
        while(!equals(proposition,"=")){
            if (equals(proposition,"+")){
                milieu=milieu+milieu/2;

            }
            else if(equals(proposition,"-")){
                milieu=milieu-milieu/2;

            }
            println("Est-ce que le nombre est : "+milieu+" ?");
            proposition=readString();

        }
        println("trouvé :"+milieu);
    }
}