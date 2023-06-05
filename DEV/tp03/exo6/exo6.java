/*class Bonneheure extends Program{
    void algorithm(){
        int heure=readInt();
        int minute=readInt();
        String sigle=readString();
        sigle = toUpperCase(sigle);
    
        if(equals(sigle,"AM") && heure>=1 && heure<12){
            println(heure+":" +minute);
        }
        else if(equals(sigle,"AM") && heure==12){
            println(0+":" +minute);
        }
        else if(equals(sigle,"PM") && heure>=1 && heure<12){
            println(heure+12 +":" +minute);
        }
        else if(equals(sigle,"PM") && heure==12){
            println(heure+":" +minute);
        }
    }
}*/

class Inverse extends Program{
    void algorithm(){
        int heure=readInt();
        int minute=readInt();
    
        if(heure>=1 && heure<12){
            println(heure+":" +minute+ " AM ");
        }
        else if(heure==12){
            println(12+":" +minute+ "PM");
        }
        else if(heure>=12 && heure<24){
            println(heure-12 +":" +minute+" PM");
        }
        else if(heure==0){
            println(12+":" +minute+" AM");
        }
    }
}