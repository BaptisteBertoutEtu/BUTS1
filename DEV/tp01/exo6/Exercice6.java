class MajMin extends Program {
    void algorithm(){
        final int DECALAGE = (int)('a'-'A' );
        print("Entrez une lettre en minuscule ");
        char lettreMin = readChar();
        char enMaj = (char) ((int) lettreMin - DECALAGE); // <- À COMPLÉTER
        println("La lettre " + lettreMin + " en majuscule donne : " + enMaj );
        print("Entrez une lettre en majuscule");
        char lettreMaj = readChar();
        char enMin = (char) ((int) lettreMaj + DECALAGE); // <- À COMPLÉTER
        println("La lettre " + lettreMaj + " en minuscule donne : " + enMin );
    }
}