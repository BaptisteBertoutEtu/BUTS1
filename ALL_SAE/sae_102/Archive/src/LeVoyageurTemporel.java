import extensions.CSVFile;
import extensions.File;

class LeVoyageurTemporel extends Program{

//------------------------------------------------------------définition des variables--------------------------------------------------
    final String PREHISTOIRE="../ressources/prehistoire.csv";
    final String ANTIQUITE = "../ressources/antiquite.csv";
    final String MOYEN_AGE="../ressources/moyen_age.csv";
    final String EPOQUE_MODERNE="../ressources/temp_moderne.csv";
    final String EPOQUE_CONTEMPORAINE="../ressources/epoque_contemporaine.csv";
    final String[] TAB_PERIOD= new String[]{PREHISTOIRE,ANTIQUITE,MOYEN_AGE,EPOQUE_MODERNE,EPOQUE_CONTEMPORAINE};
    final String[] TAB_PERIOD_STRING= new String[]{"Préhistoire","Antiquité","Moyen-âge","Temps moderne","époque contemporaine"};
    String path;
    String period_string;
    CSVFile load;
    String type_mode;
    int cpt_question;
    String name;

    final String ANSI_RED="\u001B[31m";
    final String ANSI_BLUE="\u001B[34m";
    final String ANSI_WHITE="\u001B[37m";
    final String ANSI_GREEN="\u001B[32m";
    final String RED_UNDERLINED = "\033[4;31m";
    final String GREEN_UNDERLINED = "\033[4;32m";
    final String RESET = "\033[0m";
    final String CYAN_BOLD = "\033[1;36m"; 

//------------------------------------------fonctions--------------------------------------------------------------


    //création du nouveau type Date
    Date newDate(String event, String year){
        Date d=new Date();
        d.event=event;
        d.year=year;
        return d;
    }

    
    
    //création du nouveau type QCM
    QCM newQCM(String answer,String answer1, String answer2, String answer3, String question){
        QCM qcm = new QCM();
        qcm.answer = answer;
        qcm.answer1 = answer1;
        qcm.answer2 = answer2;
        qcm.answer3 = answer3;
        qcm.question = question;
        return qcm;
    }

    //affichage de la réponse dans le mode normal
    void display_Date(Date d){
        println(d.event+" en : " + d.year);
    }

    //affichage des choix possibles de l'utilisateur en terme de réponse pour le qcm
    void display_QCM_periode(QCM qcm){
        println(qcm.question + " : ");
        println("1. " + qcm.answer1 +"\n2. " + qcm.answer2 + "\n3. " +qcm.answer3);
    }

    //affichage de la question pour le qcm à l'utilisateur
    void display_QCM_date(QCM qcm){
        println(qcm.question + " : ");
    }


    //test si la date renvoyée par l'utilisateur est la bonne réponse
    boolean is_true_date(String input_date_player, Date answer){
        if(equals(input_date_player , answer.year)){
           return true;
        }
        return false;
    }

    //affichage et saisie de l'utilisateur pour entrer sa réponse
    String answer(){
        print("Entrez la réponse : ");
        String answer=readString();
        return answer;
    }

    //prend en entré une date (type date), interroge l'utilisateur et renvoi true ou false si la réponse est bonne ou fausse
    boolean qcm_event_to_date(Date date){
        println("Veuillez renseigner uniquement des chiffres et éventuellement les signes + ou -. Ne pas mettre d'espace !!!");
        println(date.event + " ?");
        String answer = answer();
        while(!isCypher(answer)){
            println("Saisissez une date valide, composée de chiffre et sans espace.");
            answer = answer();
        }
        if(is_true_date(answer,date)){
            println("Bonne réponse ! \n Question suivante");
            return true;
        }
        else{
            println("Perdu ! \n La bonne réponse était " + date.year);
            return false;
        }
    }

    //Prend en paramètre le numéro de ligne que l'on veut récupérer ainsi que le chemin du fichier CSV 
    //renvoi un tableau de string avec l'événement([0]) et l'année([1])
    String[] recuperation_1_date_CSV(int index_question){
        String[] date= new String[2];
        CSVFile file = loadCSV(path, ',');
        date[0] = getCell(file, index_question, 0);
        date[1] = getCell(file, index_question, 1);
        return date;
    }

    //renvoi un tableau de string contenant l'expression(0), la date réponse(1) , la réponse 1 (2), la réponse 2(3) et la réponse 3(4)
    String[] recuperation_QCM_date_CSV(int index_question){
        String[] qcm = new String[5];
        CSVFile file = loadCSV(path,',');
        qcm[0] = getCell(file, index_question, 0);
        String date=getCell(file, index_question, 1);
        qcm[1] = date;
        int random =random_between_min_max(2,5);
        qcm[random] = date;
        int rep2;
        int rep3;
        if(random == 2){
            rep2=3;
            rep3=4;
        }
        else if(random == 3){
            rep2=2;
            rep3=4;
        }
        else{
            rep2=2;
            rep3=3;
        }
        do{
            qcm[rep2] = random_date(file, index_question);
            qcm[rep3] = random_date(file, index_question);
        }while(equals(qcm[rep3],qcm[rep2]) || equals(qcm[rep3], date) || equals(qcm[rep2], date));
        return qcm;
    }

    //renvoie une date aléatoire pour les qcm par date
    String random_date(CSVFile file, int index_question){
        int x=(int) ((random()*rowCount(file)));
        while(x==index_question){
            x=(int) ((random()*rowCount(file)));
        }
        return getCell(file, x, 1);
    }

    //renvoi un tableau de string contenant la date(0), l'event réponse (1), rep1 , rep2 et rep 3
    String[] recuperation_QCM_event_CSV(int index_question){
        String[] qcm = new String[5];
        CSVFile file = loadCSV(path, ',');
        qcm[0] = getCell(file, index_question, 1);
        qcm[1] = getCell(file, index_question, 0);
        int random =random_between_min_max(2,5);
        qcm[random] = qcm[1];
        int rep2;
        int rep3;
        if(random == 2){
            rep2=3;
            rep3=4;
        }
        else if(random == 3){
            rep2=2;
            rep3=4;
        }
        else{
            rep2=2;
            rep3=3;
        }
        do{
            qcm[rep2]= random_event(file, index_question);
            qcm[rep3]= random_event(file, index_question);
        }
        while(equals(qcm[rep3],qcm[rep2]) || equals(qcm[rep3], qcm[random]) || equals(qcm[rep2], qcm[random]));
        return qcm;
    }

    //renvoie un evenement aleatoire pour les qcm par evenements
    String random_event(CSVFile file, int index_question){
        int x=(int) ((random()*rowCount(file)));
        while(x==index_question){
            x=(int) ((random()*rowCount(file)));
        }
        return getCell(file, x, 0);
    }

    //Met en format Date l'extrait d'un CSV
    Date StringToDate(int index_question){
        String[] date_string = recuperation_1_date_CSV(index_question);
        Date date = newDate(date_string[0] , date_string[1]);
        return date;
    }

    //cree un nouveau qcm par date
    QCM StringToQCM_date(int index_question){
        String[] qcm_date_string = recuperation_QCM_date_CSV(index_question);
        QCM qcm = newQCM(qcm_date_string[1], qcm_date_string[2] , qcm_date_string[3] , qcm_date_string[4] , qcm_date_string[0]);
        return qcm;
    }

    //cree uhn nouveau qcm par evenements
    QCM StringToQCM_event(int index_question){
        String[] qcm_date_string = recuperation_QCM_event_CSV(index_question);
        QCM qcm = newQCM(qcm_date_string[1], qcm_date_string[2] , qcm_date_string[3] , qcm_date_string[4] , qcm_date_string[0]);
        return qcm;
    }

    //pose la question (event) et renvoi true si c'est bon et false sinon
    boolean ask_event_to_date(int index_question){
        display_frieze(cpt_question);
        Date date = StringToDate(index_question);
        print("\n\nTrouve cette date --> "+CYAN_BOLD+date.event+RESET+" : ");
        String answer = readString();
        while(!isCypher(answer) || length(answer)==0){
            print("Veuillez entrer une date valide : ");
            answer = readString();
        }
        if(equals(date.year, answer)){
            println("\nRéponse correct !");
            print("Question suivante dans :  ");
            print_timer();
            clearScreen();
            
            return true;
        }
        else{
            println("\nDommage la réponse était : \n");
            display_Date(date);
            print("Question suivante  dans :  ");
            print_timer();
            clearScreen();

            return false;
        }
    }

    //affiche le timer avant le passage à la prochaine question
    void print_timer(){
        int n=1;
        delay(300);
        for(int i=5;i>=1;i--){
            backward(n);
            clearEOL();
            print(ANSI_RED+i+" "+RESET);
            n=2;
            delay(1000);
        }
        println();
    }

    //affiche le qcm par date et demande la réponse a l'utilisateur
    boolean ask_qcm(int index_question){
        QCM qcm;
        if(equals(type_mode,"QCM par date")){
            qcm = StringToQCM_event(index_question);
        }
        else{
            qcm = StringToQCM_date(index_question);
        }
        display_frieze(cpt_question);
        println("\nA ton avis, quelle est la bonne réponse. ");
        println(ANSI_BLUE+qcm.question + " : "+ANSI_WHITE);
        println("  1. "+ qcm.answer1);
        println("  2. "+ qcm.answer2);
        println("  3. "+ qcm.answer3);
        print("--> ");
        String answer = readString();
        while(length(answer)!=1 || !between(answer,1,3)){
            println("N'oublie pas que tu dois écrire un chiffre entre 1 et 3 !");
            print("--> ");
            answer = readString();
        }
        if(equals(answer,"1"))answer=qcm.answer1;
        if(equals(answer,"2"))answer=qcm.answer2;
        if(equals(answer,"3"))answer=qcm.answer3;

        if(equals(qcm.answer, answer )){
            println("Réponse correct !");
            print("Question suivante dans :  ");
            print_timer();
            clearScreen();
            return true;
        }
        else{
            println("Dommage la réponse était : " + qcm.answer);
            print("Question suivante dans :  ");
            print_timer();
            clearScreen();
            return false;
        }
    }   

    //regarde si la réponse est contenu entre les deux entier entrés en paramètre
    boolean between(String answer,int cypher1,int cypher2){
        int cpt=0;
        boolean isTrue=true;
        if(isCypher(answer)){
            while(cpt<cypher2 && true){
                isTrue=stringToInt(answer)>=cypher1 && stringToInt(answer)<=cypher2;
                cpt++;
            }
            return isTrue;
        }
        return false;
    }

    // random de 0 à count row
    int random_cipher(int count_row){
        return (int) ((double)random()*count_row);
    }

    //renvoie un chiffre alétoire compris entre les paramètres
    int random_between_min_max(int min , int max){
        return min+(int) ((double)random()*(max-min));
    }

    //détermintaiton du chemin d'acces au fichié CSV spécifié et chargement du fichié spécifié
    void path(int choice_period){
        if(choice_period==6){
            play(display_start_menu());
        }
        else{
            path=TAB_PERIOD[choice_period-1];
            period_string=TAB_PERIOD_STRING[choice_period-1];
            load=loadCSV(path);
        }
        
    }

    //renvoi un tableau de boolean contenant true si la question d'indice n à été repondu correctement et false sinon
    boolean[] game(){
        int cpt=0;
        int[] index_question = new int[5];
        boolean[] estTrouver = new boolean[5];
        index_question=fill_array(5);
        
        while(cpt<5){
            cpt_question=cpt+1;
            
            if(equals(type_mode,"mode normal")){
                estTrouver[cpt] = ask_event_to_date(index_question[cpt]);
            }
            else{
                estTrouver[cpt] = ask_qcm(index_question[cpt]);
            }
            cpt++;
        }
        return estTrouver;
    }

    //renvoie un tableau contenant des chiffres aléatoire pour avoir des questions différentes
    int[] fill_array(int n){
        int[] array=new int[n];
        int random;
        for (int i = 0; i < n; i++) {
            do{
                random = random_between_min_max(1,n+1);
            }while(is_in_array(array,random));
            array[i]=random;
        }
        
        return array;
    }

    //renvoie true si le chiffre entré en paramètre se trouve dans le tableau entré en paramètre, false sinon
    boolean is_in_array(int[] array, int random){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==random) {
                return true;
            }
        }
        return false;
    }

    //appelle le bon qcm selon le choix de l'utilisateur
    void call_the_right_qcm(int choice){
        if(choice==1){
            type_mode="QCM par date";
        }
        else{
            type_mode="QCM par événement";
        }
        boolean[] estTrouver = game();
        score_and_percentage(estTrouver);
        continue_game();
    }

    //calcul le score et le pourcentage de reussite de l'utilisateur sur le qcm
    void score_and_percentage(boolean[] estTrouver){
        int score=0;
        
        for(int cpt=0;cpt<length(estTrouver);cpt++){
            if(estTrouver[cpt]){
                score+=1;
            }
        }
        double real_score=(double) Math.round(((score*10)/5.0)*10)/10;
        double percentage=(double) Math.round(((score*100)/5.0)*10)/10;
        save_advance(loadCSV("../ressources/save.csv", ','), percentage);
        String color_grade;
        if(score>=3){
            color_grade=ANSI_GREEN;
        }
        else{
            color_grade=ANSI_RED;
        }
        println("   Tu as un score de :                "+color_grade+score+"/5"+RESET);
        println("   Soit un score de :                 "+color_grade+real_score+"/10"+RESET);
        println("   Poucentage de réussite :           "+color_grade+percentage+" %"+RESET);
        if(score<3){
            println("Dommage "+name+", mais ne te décourage pas, tu vas y arriver !");
        }
        else if(score>=3 && score<5){
            println("Beau résultat "+name+", mais tu peux encore t'améliorer et atteindre la note de 5/5 et tu peux prendre ta revanche dès maintenant.");
        }
        else{
            println("Bravo "+name+", tu as réussi à répondre correctement à toutes les questions que je t'ai posé. Tu peux quand même rejouer sur le même QCM et avoir de nouveau événement ou de nouvelles dates en question.");
        }
    }

    //sauvegarde les pourcentages de réussite de l'utilisateur 
    void save_advance(CSVFile file, double percentage){
        String[][] loaded_file=load_file(file);
        for(int cpt = 0; cpt<length(loaded_file,1); cpt++){
            if(equals(loaded_file[cpt][0],period_string)&& equals(loaded_file[cpt][1],type_mode)){
                if(stringToDouble(loaded_file[cpt][2]) < percentage){
                    String str = "" + percentage;
                    if(percentage<100.0){
                        str = "0" + percentage;
                    }
                    else if(percentage<10.0){
                        str="00"+percentage;
                    }
                    loaded_file[cpt][2]= str;
                    saveCSV(loaded_file, "../ressources/save.csv");
                }
                break;
            }
        }
    }

    //affichage des pourcentages de réussite en fonction du mode de jeu et de la période
    void display_advance(){
        CSVFile file = loadCSV("../ressources/save.csv", ',');
        println(CYAN_BOLD+"                           ╭───────────────────┬───────────────┬───────────────┬───────────────┬────────────────┬──────────────────────╮");
        println("                           │"+RESET+"    Pourcentage    "+CYAN_BOLD+"│"+RESET+"  Préhistoire  "+CYAN_BOLD+"│"+RESET+"   Antiquité   "+CYAN_BOLD+"│"+RESET+"   Moyen-âge   "+CYAN_BOLD+"│"+RESET+"  Temps moderne "+CYAN_BOLD+"│"+RESET+" Epoque contemporaine"+CYAN_BOLD+" │");
        println("                           ├───────────────────┼───────────────┼───────────────┼───────────────┼────────────────┼──────────────────────┤");
        println("                           │"+RESET+" Mode Normal       "+CYAN_BOLD+"│"+RESET+"     " + getCell(file,0,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+ getCell(file,3,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+ getCell(file,6,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+getCell(file,9,2)+"      "+CYAN_BOLD+"│"+RESET+"        "+ getCell(file,12,2)+"        "+CYAN_BOLD+" │");
        println("                           ├───────────────────┼───────────────┼───────────────┼───────────────┼────────────────┼──────────────────────┤");
        println("                           │"+RESET+" QCM par Date      "+CYAN_BOLD+"│"+RESET+"     " + getCell(file,1,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+ getCell(file,4,2)+"     "+CYAN_BOLD+"│"+RESET+"     " +getCell(file,7,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+getCell(file,10,2)+"      "+CYAN_BOLD+"│"+RESET+"        " +getCell(file,13,2)+"        "+CYAN_BOLD+" │");
        println("                           ├───────────────────┼───────────────┼───────────────┼───────────────┼────────────────┼──────────────────────┤");
        println("                           │"+RESET+" QCM par événement "+CYAN_BOLD+"│"+RESET+"     " + getCell(file,2,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+ getCell(file,5,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+ getCell(file,8,2)+"     "+CYAN_BOLD+"│"+RESET+"     "+getCell(file,11,2)+"      "+CYAN_BOLD+"│"+RESET+"        "+ getCell(file,14,2)+"        "+CYAN_BOLD+" │");
        println("                           ╰───────────────────┴───────────────┴───────────────┴───────────────┴────────────────┴──────────────────────╯"+RESET);
        print("Entrez '1' pour revenir au menu : ");
        String x=readString();
        while(length(x)!=1 || charAt(x,0)!='1'){
            print("Entrez '1' pour revenir au menu : ");
            x=readString();
        }
        clearScreen();
        play(display_start_menu());
    }


    void reset_advance(){
        CSVFile file = loadCSV("../ressources/save.csv", ',');
        String[][] loaded_file=load_file(file);
        
        for (int index = 0; index < rowCount(file); index++) {
            loaded_file[index][2]="000.0";
        }
        saveCSV(loaded_file, "../ressources/save.csv");
    }

    //affichage de la frise
    void display_frieze(int cpt_question){
        String filename = "../ressources/frise"+cpt_question+".txt";
        File frieze = newFile(filename);
        println(ANSI_GREEN);
        String read;
        int cpt=0;
        while(ready(frieze)){
            read=readLine(frieze);
            if(cpt==6 || cpt==7){
                println(RESET+substring(read, 0, 31)+ANSI_GREEN+substring(read, 31, length(read)));
            }
            else if(cpt==8){
                for(int i=0;i<length(read);i++){
                    if(i<31 && (charAt(read, i)=='/' || charAt(read, i)=='\\')){
                        print(RESET+charAt(read, i)+ANSI_GREEN);
                    }
                    else{
                        print(charAt(read, i));
                    }
                }
                println();
            }
            else{
                println(read);
            }
            cpt++;
        }
        println(ANSI_WHITE);
    }

    //affiche le message de bienvenue contenu dans un fichier .txt
    void display_ascii_art(){
        String filename = "../ressources/ascii_art.txt";
        File ascii_art = newFile(filename);
        while(ready(ascii_art)){
            println(readLine(ascii_art));
        }
    }

    //fonction qui demande à l'utilisateur si il veut continuer de jouer en fin de mode de jeu
    void continue_game(){
        println("\nQue veux-tu faire ?");
        println(CYAN_BOLD+"                           ╭──────────────────────────────────╮");
        println("                           │"+RESET+" 1. Recommencer le QCM            "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2. Choisir une autre période     "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3. Retourner au menu principal   "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 4. Arrêter le jeu                "+CYAN_BOLD+"│");
        println("                           ╰──────────────────────────────────╯"+RESET+"\n");
        print("--> ");
        String choice=readString();
        while(length(choice)!=1 || (charAt(choice,0)>3 && charAt(choice,0)<1)){
            print("Veuillez entrer un chiffre entre 1 et 3 : ");
            choice=readString();
        }
        if(stringToInt(choice)==1){
            which_type();
        }
        else if(stringToInt(choice)==2){
            lets_play_at_game();
        }
        else if(stringToInt(choice)==3){
            play(display_start_menu());
        }
        else{
            println("Au revoir.");
        }
    }

    //fonction permettant l'appelle des fonction permettant de rejouer à la fin d'un mode de jeu
    void which_type(){
        if(equals(type_mode,"mode normal")){
            game_choice_normal();
        }
        else if(equals(type_mode,"QCM par date")){
            call_the_right_qcm(1);
        }
        else{
            call_the_right_qcm(2);
        }
    }

    //retourne true si la chine de caractère entrée en paramètre est bien soit "o" soit "n"; false sinon
    boolean yes_no(String answer){
        return length(answer)==1 && charAt(answer, 0)=='n' || charAt(answer, 0)=='o';
    }

    //fonction qui verifie si le mot de passe de changement saisi par l'utilisateur ne contient pas de virgule
    boolean whithout_comma(String str){
        for(int cpt=0; cpt<length(str); cpt++){
            if(charAt(str,cpt)==','){
                return false;
            }
        }
        return true;
    }

    void ask_name(){
        print("Quel est ton nom ? : ");
        name=readString();
    }

    
//---------------------------------------------------------partie admin---------------------------------------------------------------



    //chargement du fichier admin contenant le mot de passe et l'identifiant
    CSVFile login = loadCSV("../ressources/admin.csv",',');


    //identification de l'utilisateur et verification du mot de passe et de l'identifiant
    void login(){
        String[] login_and_passwd = new String[]{getCell(login, 0, 0),getCell(login, 0, 1)};
        int login_try=3;
        boolean right_login=false;
        println("Vous essayez de vous connecter en admin, pour cela veuillez entrer l'identifiant et le mot de passe.");
        print("IDENTIFIANT : ");
        String log=readString();
        print("MOT DE PASSE : ");
        String passwd=readString();
        while(login_try!=1 && !right_login){
            right_login= equals(log,login_and_passwd[0]) && equals(passwd,login_and_passwd[1]);
            if(!right_login){
                login_try--;
                println("\nIdentifiant ou mot de passe incorrect, il vous reste "+login_try+" essais.");
                print("IDENTIFIANT : ");
                log=readString();
                print("MOT DE PASSE : ");
                passwd=readString();
            }
        }
        clearScreen();
        if(right_login){
            println("\nBienvenue dans le mode ADMIN.\n");
            admin_mode();
        }
        else{
            println("Vous avez tenté de vous connecter trop de fois, vous allez être retourné vers le menu.\n");
            algorithm();
        }
    }

    //affichage du menu de l'admin et choix de l'utilisateur
    void admin_mode(){
        println("                                              Mode ADMIN");
        println(CYAN_BOLD+"                           ╭──────────────────────────────────────────────╮");
        println("                           │"+RESET+" 1.         Changer de mot de passe           "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2.          Visualiser les fichiers          "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3.           Modifier les fichiers           "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 4.    Ajouter des données dans le fichier    "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 5.             Revenir au menu               "+CYAN_BOLD+"│");
        println("                           ╰──────────────────────────────────────────────╯"+RESET);
        print("\nSaisis ton choix : ");
        String choice=readString();
        while(length(choice)!=1 || !between(choice, 1, 5)){
            print("\nVeuillez saisir un chiffre compris entre 1 et 5 : ");
            choice=readString();
        }
        clearScreen();
        admin_choice(stringToInt(choice));
    }

    //appelle de fonction selon le choix de l'utilisateur
    void admin_choice(int choice){
        if(choice==1){
            change_passwd();
        }
        else if(choice==2){
            choose_file();
            println("\nVoici le contenu du fichier \""+period_string+".csv\"\n");
            view_file(load_file(loadCSV(path, ',')));
            String x;
            do{
                print("Pour revenir au menu précédent, saisissez '1' : ");
                x=readString();
            }while(length(x)!=1 || charAt(x, 0)!='1');
            clearScreen();
            admin_mode();
        }
        else if(choice==3){
            choose_file();
            again_modif(modif_file(loadCSV(path, ',')));
        }
        else if(choice==4){
            choose_file();
            append(loadCSV(path, ','));
        }
        else{
            play(display_start_menu());
        }
    }

    //changement du mot de passe par l'utilisateur
    void change_passwd(){
        String[][] content = new String[1][2];
        String pass;
        String confirm_pass;
        print("Voulez-vous vraiment changer de mot de passe ? (o/n) : ");
        String respons=readString();
        respons=toLowerCase(respons);

        while(length(respons)!=1 || (charAt(respons,0)!='o' && charAt(respons,0)!='n')){
            print("Veuillez entrer une lettre valide (o/n) : ");
            respons=readString();
        }
        
        if(equals(respons,"o")){
            clearScreen();
            println("\nSaisissez votre nouveau mot de passe : ");
            pass=readString();
            println("\nSaisissez à nouveau votre mot de passe : ");
            confirm_pass=readString();
            while(!equals(pass,confirm_pass)){
                println("\n\nVos deux saisies sont differentes, veuillez retenter.\n");
                println("Saisissez votre nouveau mot de passe : ");
                pass=readString();
                println("\nSaisissez à nouveau votre mot de passe : ");
                confirm_pass=readString();
            }
            while(!whithout_comma(pass)){
                println("Veuillez saisir un mot de passe sans virgule.");
                println("Saisissez votre nouveau mot de passe : ");
                pass=readString();
                println("\nSaisissez à nouveau votre mot de passe : ");
                confirm_pass=readString();
            }
            content[0][0]=getCell(login, 0, 0);
            content[0][1]=pass;
            saveCSV(content,"../ressources/admin.csv");
            login=loadCSV("../ressources/admin.csv",',');
            println("Mot de passe pris en compte.");
        }
        admin_mode();
    }
    
    //affichage des fichiers que l'utilisateur peux choisir
    void choose_file(){
        println("Vous pouvez acceder à l'ensemble de ces fichiers : \n");
        println(CYAN_BOLD+"                           ╭─────────────────────────────────╮");
        println("                           │"+RESET+" 1. Préhistoire.csv              "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2. Antiquité.csv                "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3. Moyen Âge.csv                "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 4. Temps modernes.csv           "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 5. Époque contemporaine.csv     "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 6. Revenir au menu              "+CYAN_BOLD+"│");
        println("                           ╰─────────────────────────────────╯"+RESET);
        print("\nChoisissez un ficher : ");
        String choice=readString();
        while(length(choice)!=1 || (charAt(choice,0)<'1' || charAt(choice,0)>'6')){
            print("Veuillez saisir un chiffre compris entre 1 et 6 pour choisir le fichier : ");
            choice=readString();
        }
        clearScreen();
        if(equals(choice,"6"))admin_mode();
        else{
            path(stringToInt(choice));
        }
        
    }

    //charge le fichiers choisi par l'utilisateur//charge le fichiers choisio par l'utilisateur
    String[][] load_file(CSVFile file){
        String[][] final_file = new String[rowCount(file)][columnCount(file)];
        for (int cpt_y = 0; cpt_y < length(final_file,1); cpt_y++){
            for(int cpt_x = 0 ; cpt_x < length(final_file,2); cpt_x++){
                final_file[cpt_y][cpt_x]=getCell(file, cpt_y, cpt_x);
            }
        }
        return final_file;
    }




    //affichage du contenu du fichier
    void view_file(String[][] file){
        for (int cpt = 0; cpt < file.length; cpt++) {
            println(cpt+1+". "+file[cpt][0]+" ; "+file[cpt][1]);
        }
        println();
    }

    //permet la modification de certaines lignes du fichier et renvoie un boolean permettant de savoir si l'utilisateur a ou non modifié un fichier
    boolean modif_file(CSVFile file){
        String[][] loaded_file=load_file(file);
        println("Voici le contenu du fichier "+period_string+"\n");
        view_file(loaded_file);
        print("\nQuel ligne voulez-vous modifier ? (Si vous voulez revenir en arrière saisissez '0'): ");
        int num_lig=user_choice_line(file);
        if(num_lig!=0){
            println("\nQue voulez-vous modifier ?");
            println(CYAN_BOLD+"                           ╭─────────────────────╮");
            println("                           │"+RESET+" 1. L'événement      "+CYAN_BOLD+"│");
            println("                           │"+RESET+" 2. La date          "+CYAN_BOLD+"│");
            println("                           │"+RESET+" 3. Rien (menu)      "+CYAN_BOLD+"│");
            println("                           ╰─────────────────────╯"+RESET+"\n");
            print("--> ");
            String event_or_date=readString();
            while (length(event_or_date)!=1 && !between(event_or_date, 1, 3)) {
                print("Veuillez entrer un chiffre valide : ");
                event_or_date=readString();
            }
            if (!equals(event_or_date,"3")) {
                replace(num_lig,stringToInt(event_or_date),loaded_file);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    //retourne le numero de ligne choisi par l'utilisateur
    int user_choice_line(CSVFile file){
        String num=readString();
        while (!isCypher(num)){
            print("Veuillez entrer un chiffre valide : ");
            user_choice_line(file);
        }
        int real_num_lig=stringToInt(num);
        while(real_num_lig<0 || real_num_lig>rowCount(file)){
            user_choice_line(file);
        }
        return real_num_lig;
    }

    //verifie que la chaine de caractere entree en parametre ne contient que des chiffres
    boolean isCypher(String cypher){
        boolean isCypher=true;
        int cpt=0;
        while (isCypher && cpt<length(cypher)) {
            isCypher=charAt(cypher, cpt)>='0' && charAt(cypher, cpt)<='9';
            cpt++;
        }
        return isCypher;
    }

    //permet le remplacement d'une donnee dans le fichier, appellée par la fonction modif_file()
    void replace(int num_line,int event_or_date,String[][] loaded_file){
        println("Saisissez la donnée de remplacement : ");
        String replace_data=readString();
        loaded_file[num_line-1][event_or_date-1]=replace_data;
        saveCSV(loaded_file, path);
    }

    //permet l'ajout de donnees dans le fichier
    void append(CSVFile file){
        String[][] loaded_file=load_file(file);
        println("Vous allez rajouter des données dans le fichier "+period_string+".csv \n");
        modif(loaded_file);
    }

    //fonction appellée pour ajouter un evenement et une date choisit par l'utilisateur
    void modif(String[][] loaded_file){
        String[][] new_tab= new String[length(loaded_file)+1][2];
        view_file(loaded_file);
        println("Saisissez l'événement à rajouter : ");
        String eventString=readString();
        while(!whithout_comma(eventString)){
            println("Veuillez saisir une données sans virgule pour le bon fonctionnement du programme : ");
            eventString=readString();
        }
        println("Saisissez la date à rajouter : ");
        String dateString=readString();
        while(!isCypher(dateString)){
            print("Veuillez saisir une date valide (composée de chiffres) : ");
            dateString=readString();
        }
        for(int cpt=0;cpt<length(loaded_file);cpt++){
            for(int i=0;i<2;i++){
                new_tab[cpt][i]=loaded_file[cpt][i];
            }
        }
        new_tab[length(new_tab)-1][0]=eventString;
        new_tab[length(new_tab)-1][1]=dateString;
        saveCSV(new_tab, path);
        again_append(new_tab);
    }

    //demande a l'utilisateur si il veut rajouter d'autre donnee au fichier
    void again_append(String[][] loaded_file){
        print("Voulez-vous rajouter d'autres données ? (o/n) :");
        String respons=readString();
        respons=toLowerCase(respons);
        while(length(respons)!=1 && !yes_no(respons)){
            print("Veuillez entrer un caractère valide (o/n) :");
            respons=readString();
        }
        if(equals(respons,"o")){
            clearScreen();
            modif(loaded_file);
        }
        else{
            clearScreen();
            admin_mode();
        }
    }

    //permet une nouvelle modification si l'utilisateur le souhaite
    void again_modif(boolean again){
        if(again){
            print("Voulez-vous modifier d'autres données ? (o/n) :");
            String respons=readString();
            respons=toLowerCase(respons);
            while(length(respons)!=1 && !yes_no(respons)){
                print("Veuillez entrer un caractère valide (o/n) :");
                respons=readString();
            }
            if(equals(respons,"o")){
                clearScreen();
                again_modif(modif_file(loadCSV(path, ',')));
            }
        }
        clearScreen();
        admin_mode();
    }

    
// -------------------------------------------------------------menu------------------------------------------------------------------

    //affichage d'un message de bienvenue
    void welcome(){
        clearScreen();
        display_ascii_art();
        println(ANSI_GREEN+"\n\nPendant toute cette aventure, il te faudra choisir dans différents menus. \nPour cela, rien de plus simple, il te suffit de taper un chiffre présent dans le menu lorsque-l-on te le demande et le tour est joué."+RESET);
        separator();
    }

    //affichage d'un trait de séparation
    void separator(){
        println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n");
    }

    //fonction d'affichage du menu principale
    int display_start_menu(){
        println("Bienvenue dans le menu de démarrage. Pour choisir, tapez l'un des chiffres présents sur ce menu.\n");
        println(CYAN_BOLD+"                           ╭──────────────────────────────────╮");
        println("                           │"+RESET+" 1.           JOUER               "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2.  VISUALISER L'AVANCEMENT      "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3.  REINITIALISER L'AVANCEMENT   "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 4.  PASSER EN ADMINISTRATEUR     "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 5.          ARRETER              "+CYAN_BOLD+"│");
        println("                           ╰──────────────────────────────────╯"+RESET);
        println(ANSI_RED+RED_UNDERLINED+"\nAttention"+RESET+ANSI_RED+" : le mode ADMIN est reservé aux professeurs et est protégé par un mot de passe."+ANSI_WHITE);
        print("Saisis ton choix : ");

        String choice=readString();
        while(length(choice)!=1 || !between(choice, 1, 5)){
            print("Veuillez saisir un chiffre compris entre 1 et 5 pour choisir le mode : ");
            choice=readString();
        }
        clearScreen();
        return stringToInt(choice);

    }

    //affichage du menu pour permettre à l'utilisateur de choisir la période qu'il veut étudier
    void display_menu_period(){
        println();
        println("Tu peux voyager dans toute les périodes ci-dessous : \n");
        println(CYAN_BOLD+"                           ╭─────────────────────────────╮");
        println("                           │"+RESET+" 1. Préhistoire              "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2. Antiquité                "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3. Moyen Âge                "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 4. Temps modernes           "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 5. Époque contemporaine     "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 6. Retour au menu principal "+CYAN_BOLD+"│");
        println("                           ╰─────────────────────────────╯"+RESET);
    }

    //prise en compte du choix de l'utilisateur
    int choice_menu_period(){
        print("\nSaisis ton choix de période à visiter : ");
        String choice=readString();
        while(length(choice)!=1 || !between(choice, 1, 6)){
            print("Attention, il faut que tu saisisses un chiffre entre 1 et 6 pour choisir ce que tu veux faire : ");
            choice=readString();
        }
        clearScreen();
        return stringToInt(choice);
    }

    //affichage du menu pour permettre à l'utilisateur de choisir le tpe de qcm qu'il veut et prise en compte de sa saisie
    void menu_choice_type_of_QCM(){
        clearScreen();
        println(" @\n/|\\ \n/ \\ ");
        println("Tu as choisi d'apprendre par QCM, choisis maintenant le type de QCM auquel tu veux jouer.");
        println(CYAN_BOLD+"                           ╭─────────────────────╮");
        println("                           │"+RESET+" 1 : PAR DATE        "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2 : PAR EVENEMENT   "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3 : RETOUR AU MENU  "+CYAN_BOLD+"│");
        println("                           ╰─────────────────────╯"+RESET);
        details();
        print("\n Saisis ton choix de QCM (Aide toi des détails du dessus pour choisir celui qui te convient le mieux) : ");
        String choice=readString();
        while(length(choice)!=1 || !between(choice, 1, 3)){
            print("Attention, il faut que tu saisisses un chiffre entre 1 et 3 pour choisir ce que tu veux faire : ");
            choice=readString();
        }
        clearScreen();
        if(equals(choice,"3"))menu_choice_type_of_game();
        else{
            call_the_right_qcm(stringToInt(choice));
        }
    }

    //affichage des details pour les QCM
    void details(){
        println("Détails :");
        println(ANSI_GREEN+"- Qu'est ce que le QCM par date ? : Tu as 5 question à répondre, dans chacune d'elle, on te donne une date et c'est à toi de trouver le bon événement parmi les 3 propositions");
        println("- QU'est ce que le QCM par événement ? : Tu as 5 question à répondre, dans chacune d'elle, on te donne un événement et c'est à toi de trouver la bonne date parmi les 3 propositions \n"+ANSI_WHITE);
    }

    //affichage du menu pour permettre  à l'utilisateur de choisir le tpe de qcm qu'il veut et prise en compte de sa saisie
    void menu_choice_type_of_game(){
        clearScreen();
        println(" @\n/|\\ \n/ \\ ");
        println("Tu as choisi la période suivante : "+period_string+".");
        println("C'est à toi de choisir comment tu veux jouer et apprendre.");
        println(CYAN_BOLD+"                           ╭─────────────────────────────────╮");
        println("                           │"+RESET+" 1 : MODE NORMAL                 "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 2 : MODE QCM                    "+CYAN_BOLD+"│");
        println("                           │"+RESET+" 3 : RETOUR AU CHOIX DE PERIODE  "+CYAN_BOLD+"│");
        println("                           ╰─────────────────────────────────╯"+RESET);
        println(ANSI_GREEN+GREEN_UNDERLINED+"Remarque"+RESET+ANSI_GREEN+" : Mode normal --> On te donne un événement et c'est à toi de rentrer la bonne date."+ANSI_WHITE);
        print("Dans quel mode de jeu veux-tu jouer ? : ");
        String choice = readString();
        while(length(choice)!=1 || !between(choice, 1, 3)){
            print("Attention, il faut que tu saisisses un chiffre entre 1 et 3 pour choisir ce que tu veux faire : ");
            choice=readString();
        }
        clearScreen();
        if(equals(choice,"3"))lets_play_at_game();
        else{
            display_choice_game(stringToInt(choice));
        }
    }

    //choix du jeu en mode normal et appel de la fonction pour jouer
    void game_choice_normal(){
        type_mode="mode normal";
        boolean[] estTrouver= game();
        score_and_percentage(estTrouver);
        continue_game();
    }

    //appel les fonctions de jeu en fonction du choix de l'utilisateur
    void display_choice_game(int choice_game){
        if (choice_game==1){
            game_choice_normal();
        }
        else{
            menu_choice_type_of_QCM();
        }
    }

    //appelle les fonctions pour jouer
    void lets_play_at_game(){
        display_menu_period();
        int choice=choice_menu_period();
        path(choice);
        if(choice!=6){
            menu_choice_type_of_game();
        }
       
    }

    //demande à l'utilisateur si il confirme vouloir jouer
    boolean go(){
        print("On y va ? "+name+"(o/n) : ");
        String lets_go=readString();
        lets_go=toLowerCase(lets_go);
        while(length(lets_go)!=1 || !yes_no(lets_go)){
            print("Pense à entrer \"o\" ou \"n\" pour dire si tu veux y aller : ");
            lets_go=readString();
            lets_go=toLowerCase(lets_go);
        }
        return equals(lets_go,"o");
    }

    //appelle les fonctions selon le choix de l'utilisateur
    void play(int choice_start_menu){
        clearScreen();
        if(choice_start_menu==1){
            ask_name();
            welcome();
            boolean lets_play=go();
            if (lets_play) {
                println("\nChouette !!");
                lets_play_at_game();
            }
            else{
                println("\nHo mince alors, on retourne au menu ...");
                play(display_start_menu());
            }
            
        }
        else if(choice_start_menu==2){
            display_advance();
        }
        else if(choice_start_menu==3){
            println("Vous êtes sûr de vouloir réinitialiser l'avancement ? (o/n) : ");
            String respons=readString();
            while (!yes_no(respons)) {
                println("Vous êtes sûr de vouloir réinitialiser l'avancement ? (o/n) : ");
                respons=readString();
            }
            if(equals(respons,"o")){
                reset_advance();
                println("Avancement réinitialisé.");
            }
            play(display_start_menu());
        }
        else if(choice_start_menu==4){
            login();
        }
        else{
            println("Au revoir.");
        }
    }

// ---------------------------------------------------------test-----------------------------------------------------

    //fonction test de la fonction isCypher()
    void test_isCypher(){
        assertTrue(isCypher("1789"));
        assertTrue(isCypher("1515"));
        assertFalse(isCypher("Napoléon"));
        assertFalse(isCypher("Napoléon1789"));
    }

    //fonction test de la fonction whithout_comma()
    void test_whithout_comma(){
        assertTrue(whithout_comma("123456Pmlo"));
        assertTrue(whithout_comma("ebvfbe hfzeb"));
        assertTrue(whithout_comma("éqcqec"));
        assertFalse(whithout_comma("PM,"));
    }

    //fonction test de la fonction newDate()
    void testNewDate(){
        Date d1=newDate("L'écriture par les sumériens : ... avant JC","3000");
        Date d2=newDate("Les premières pyramides : ... avant JC","2700");
        Date d3=newDate("Premier alphabet : ... avant JC","1300");
        assertTrue(d1.event=="L'écriture par les sumériens : ... avant JC" && d1.year=="3000");
        assertTrue(d2.event=="Les premières pyramides : ... avant JC" && d2.year=="2700");
        assertFalse(d3.event=="Premier alphabet : ... avant JC" && d3.year=="1200");
    }

    //fonction test de la fonction newQCM()
    void testNewQCM(){
        QCM qcm1=newQCM("3000","3000","2700","1300","L'écriture par les sumériens : ... avant JC");
        QCM qcm2=newQCM("2700","2700","3000","1300","Les premières pyramides : ... avant JC");
        QCM qcm3=newQCM("1300","1300","3000","2700","Premier alphabet : ... avant JC");
        assertTrue(qcm1.answer=="3000" && qcm1.answer1=="3000" && qcm1.answer2=="2700" && qcm1.answer3=="1300" && qcm1.question=="L'écriture par les sumériens : ... avant JC");
        assertTrue(qcm2.answer=="2700" && qcm2.answer1=="2700" && qcm2.answer2=="3000" && qcm2.answer3=="1300" && qcm2.question=="Les premières pyramides : ... avant JC");
        assertFalse(qcm3.answer=="1300" && qcm3.answer1=="1400" && qcm3.answer2=="3000" && qcm3.answer3=="2700" && qcm3.question=="Premier alphabet : ... avant JC");
    }

    //fonction test de la fonction is_true_date()
    void testIs_true_date(){
        Date d1=newDate("L'écriture par les sumériens : ... avant JC","3000");
        Date d2=newDate("Les premières pyramides : ... avant JC","2700");
        assertTrue(is_true_date("3000",d1));
        assertFalse(is_true_date("2600",d2));
    }

    //fonction test de la fonction load_file()
    void test_load_file(){
        String[][] table = new String[][] { {"L'écriture par les sumériens : ... avant JC","3000"},
                                            {"Les premières pyramides : ... avant JC","2700"},
                                            {"Premier alphabet : ... avant JC","1300"},
                                            {"La guerre de Troie : ... avant JC","1250"},
                                            {"Le travail du fer (en Europe) : ... avant JC","800"},
                                            {"Fondation de Rome : ... avant JC","753"},
                                            {"Fondation de Massalia : ... avant JC","600"},
                                            {"Jules César conquiert la Gaule : ... avant JC","57"},
                                            {"Bataille d'Alésia : ... avant JC","52"},
                                            {"Assassinat de Jules César par son Fils Brutus : ... avant JC","44"},
                                            {"Suicide de Cléopatre : ... avant JC","30"},
                                            {"Auguste rétablit la paix romaine : ... avant JC","27"},
                                            {"Méron persécute les chrétiens","58"},
                                            {"Constantinople capitale romaine","330"},
                                            {"Chute de l'ampire romain","476"} };
        
        String[][] table2 = load_file(loadCSV(ANTIQUITE));
        for(int cpt = 0; cpt<length(table,1); cpt++){
            for(int i = 0; i<length(table,2); i++){
                assertTrue(equals(table[cpt][i],table2[cpt][i]));
            }
        }
    }

    //fonction test de la fonction random_between_min_max()
    void test_random_between_min_max(){
        for(int cpt=0;cpt<15; cpt++){
            int test = random_between_min_max(0,6);
            assertTrue(test<=6 && test>=0);
        }
    }

    //fonction test de la fonction between()
    void test_between(){
        assertTrue(between("1",0,5));
        assertFalse(between("a",1,3));
    }

    //fonction test de la fonction recuperation_1_date_CSV()
    void testRecuperation_1_date_CSV(){
        path=ANTIQUITE;
        String[] test1=recuperation_1_date_CSV(10);
        assertTrue(equals(test1[0],"Suicide de Cléopatre : ... avant JC") && equals(test1[1],"30"));
        assertFalse(test1[0]=="Auguste rétalit la paix romaine : ... avant JC" && test1[1]=="27");
    }
    
    //fonction test de la fonction stringToDate()
    void test_StringToDate(){
        path = ANTIQUITE;
        Date date = newDate("Premier alphabet : ... avant JC","1300");
        assertEquals(date.event, StringToDate(2).event);
        assertEquals(date.year, StringToDate(2).year);
    }

    //fonction test de la fonction Path() (partiellement)
    void testPath(){
        path(3);
        assertTrue(path==TAB_PERIOD[3-1] && period_string==TAB_PERIOD_STRING[3-1]);
        assertFalse(path==TAB_PERIOD[5-1] && period_string==TAB_PERIOD_STRING[5-1]);
    }

    //fonction test de la test_is_in_array()
    void test_is_in_array(){
        int[] array=new int[]{1,2,3,4,5};
        assertTrue(is_in_array(array, 3));
        assertTrue(is_in_array(array, 5));
        assertFalse(is_in_array(array, 6));
        
    }

    //fonction test de la yes_no()
    void test_yes_no(){
        assertTrue(yes_no("o"));
        assertTrue(yes_no("n"));
        assertFalse(yes_no(" o"));
        assertFalse(yes_no("oui"));
        assertFalse(yes_no("non"));
    }


// ---------------------------------------------------------void algorithm-----------------------------------------------------

    void algorithm(){
        clearScreen();
        play(display_start_menu());
    }
} 