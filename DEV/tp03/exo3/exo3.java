class Factorielle extends Program{
    void algorithm(){
        int entier=readInt();
        if (entier<0){
            println("error");
        }
        else{
            int result=1;
            for(int cpt=1;cpt<entier+1;cpt=cpt+1){
                result*=cpt;
            }
            println(result);
        }
    }
}