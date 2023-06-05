class NiOuiNiNon extends Program{
    void algorithm(){
        String mot=readString();
        mot=mot.toLowerCase();
        boolean condi=!equals(mot,"oui") && !equals(mot,"non");
        while(condi){
            mot=readString();
            condi=!equals(mot,"oui") && !equals(mot,"non");;
        }
        println("Perdu ...");
    }
}