class FizzBuzz extends Program{
    void algorithm(){
        print("Entrez un nombre : ");
        int nb=readInt();
        if (nb%3==0 && nb%5==0){
            println("fizzbuzz");
        }
        else{
            if (nb%3==0){
                println("fizz");
            }
            else if (nb%5==0){
                println("buzz");
            }
            else{
                println(nb);
            }
        }
        
    }
}