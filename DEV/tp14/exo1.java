class Recursive extends Program{

    int compte_directe(char car, String chaine){
        if (length(chaine)==0){
            return 0;
        }
        else if (charAt(chaine,0)==car){
            return 1+compte_directe(car,substring(chaine,1,length(chaine)));
        }
        else{
            return compte_directe(car,substring(chaine,1,length(chaine)));
        }
    }


    int compte_terminal(char car, String chaine, int nb_occurence){
        if (length(chaine)==0){
            return nb_occurence;
        }
        else if(charAt(chaine,0)==car){
            return compte_terminal(car,substring(chaine,1,length(chaine)),nb_occurence+1);
        }
        else{
            return compte_terminal(car,substring(chaine,1,length(chaine)),nb_occurence);
        }
    }



    void algorithm(){
        println(compte_directe('e', "kiki"));
        println(compte_directe('a',"banane"));
        println(compte_terminal('e', "kiki",0));
        println(compte_terminal('a',"banane",0));
    }

}