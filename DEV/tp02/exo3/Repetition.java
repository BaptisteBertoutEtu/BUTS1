class Repetition extends Program{
    void algorithm(){
        String mot= readString();
        boolean rep= equals(substring(mot,0,length(mot)/2),substring(mot,length(mot)/2,length(mot)));
        println(rep);
    }
}