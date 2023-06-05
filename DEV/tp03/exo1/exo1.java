class Echo extends Program{
    void algorithm(){
        print("phrase : ");
        String phrase=readString();
        print("nombre : ");
        int n=readInt();
        for(int cpt=0; cpt<n;cpt=cpt+1){
            println(phrase);
        }
    }
}