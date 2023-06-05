class PGCD extends Program{
    void algorithm(){
        int a=readInt();
        int b=readInt();
        int reste;
        if (a<0){
            a=-a;
        }
        if (b<0){
            b=-b;
        }
        if (b==0){
            println(a);
        }
        else{
            reste=a%b;
            while(reste!=0){
            
            a=b;
            b=reste;
            reste=a%b;
            }
            println(b);
        }
        
    }
}