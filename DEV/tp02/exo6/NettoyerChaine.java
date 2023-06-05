class NettoyerChaine extends Program{
    void algorithm(){
        String chaine = "bye ";
        boolean debut= equals(substring(chaine,0,1)," ");
        boolean fin=equals(substring(chaine,length(chaine)-1,length(chaine))," ");
        if (debut){
            chaine=substring(chaine,1,length(chaine));
        }
        if (fin){
            chaine=substring(chaine,0,length(chaine)-1);
        }
        println(chaine);
    }
}