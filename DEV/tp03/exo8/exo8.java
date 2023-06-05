class Tri extends Program {
    void algorithm(){
        String s;
        char c1, c2, c3;
        c1 = readChar();
        c2 = readChar();
        c3 = readChar();
        char temp;
        // à compléter pour que c1, c2 et c3 contiennent les caractères saisis dans l’ordre ASCII
        if(c1>c3){
            temp=c1;
            c1=c3;
            c3=temp;
        }
        if(c2>c1){
            temp=c1;
            c1=c2;
            c2=temp;
        }
        if (c1>c2){
            temp=c1;
            c1=c2;
            c2=temp;
        }
        
        println("" + c1 + c2 + c3); //à garder tel quel
    }
}