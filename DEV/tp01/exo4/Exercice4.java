class Conversions extends Program {
    void algorithm(){
        print( "(int) 4.6 ->" );//1
        println( (int) 4.6 );

        print( "(double) 4 ->" );//2
        println( (double) 4 );

        print("2.1 + 3 -> ");//3
        println(2.1 + 3);

        print("(int) 'A' -> ");//4
        println( (int) 'A' );

        print(" (char) 66 -> ");//5
        println( (char) 66);

        print(" (int) 3.7 * 2 -> ");//6
        println( (int) 3.7 * 2);

        print("(int) (3.7 * 2) -> ");//7
        println((int) (3.7 * 2));
        
        print(" \"ABC\" + (char) 65 ");//8
        println( "ABC" + (char) 65 );
    }
}

/**
1: 4
2: 4.0
3: 5.1
4: 65
5: 'B'
6: 6
7: 7
8: "ABCA"

 */