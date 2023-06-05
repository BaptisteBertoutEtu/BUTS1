class Dichotomie extends Program{

    int rechDico (int[] tab, int val,int debut, int fin){
        int milieu=(debut+fin)/2;
        if (debut>fin){
            return -1;
        }
        else if(tab[milieu]==val){
            return val;
        }
        else if(val>tab[milieu]){
            return rechDico(tab,val,milieu+1,fin);
        }
        else{
            return rechDico(tab,val,debut,milieu-1);
        }
    }

    String toString(int[] tab){
        String res="";
        for (int i=0;i<length(tab);i++){
            res+=tab[i];
        }    
        return res;
        }
    void algorithm(){

        int[] tab= new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,16};
        int x=rechDico(tab,6,0,length(tab));
        int y=rechDico(tab,-1,0,length(tab));
         println(x);
          println(y);
    }


}