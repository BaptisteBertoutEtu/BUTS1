class SAEpolybe_BERTOUT_DESMONS_DORMAEL extends Program {

    final int LARGEUR = 5;  // taille du carré (5x5 dans notre cas)
    
    //////////////////////////////////////////////////////////////////////////    
    
    // La fonction String initialiserCarre() retourne une chaine de caractères contenant le carré de Polybe (version de base, sans clé, c'est-à-dire la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ", le V et le W sont "fusionnés" en V)
    String initialiserCarreSimple(){
        return "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction void afficherCarre(String carre) affiche le carré de Polybe carrz passé en paramètre comme illustré dans l'exemple ci-après.
    // Par exemple : si le carré passé en paramètre est : "ABCDEFGHIJKLMNOPQRSTUVXYZ", la fonction devra afficher : 
    //  |0 1 2 3 4
    // ------------
    // 0|A B C D E
    // 1|F G H I J
    // 2|K L M N O 
    // 3|P Q R S T 
    // 4|U V X Y Z

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    void afficherCarre(String carre){
        println(" |0 1 2 3 4");
        println("------------");
        for (int cpt=0; cpt<LARGEUR;cpt+=1){
            print(cpt +"|");
            for (int cpt1=0; cpt1<LARGEUR; cpt1+=1){
                print(substring(carre,0,1)+" ");
                carre=substring(carre,1,length(carre));   
            }
            println();
        }
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderLettre (String carre, char lettre) retourne une chaîne de 2 caractères (2 entiers entre 0 inclus et LARGEUR exclus) contenant l'encodage du caractère lettre passé en paramètre en considérant le carré de Polybe carre également passé en paramètre.
    // Par exemple :
    // si on considère le carré de Polybe sans clé (càd le carré ABCDE représenté par la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ") : 
    //                                                           FGHIJ
    //                                                           KLMNO                   
    //                                                           PQRST
    //                                                           UVWYZ
    //      'A' est codé "00"
    //      'B' est codé "01"
    //      'F' est codé "10"
    //      'V' est codé "41"
    //      'W' est codé "41"
    //      'Z' est codé "44"
    //      'P' est codé "30"

    // si on considère le carré de Polybe donné par la chaine "AZERTYUIOPQSDFGHJKLMXCVBN" :
    //      'A' est codé "00"
    //      'B' est codé "43"
    //      'Z' est codé "01"
    
    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    // Indication : pensez à la division entière et au modulo

    String coderLettre(String carre, char lettre){
        String code;
        int reste;
        int quotient;
        int i = 0;
        boolean trouve = false;
        if (lettre == 'W') {
            lettre = 'V';
        }
        while(!trouve && i < length(carre) ) {
            trouve = charAt(carre, i) == lettre;
            i = i + 1;
        }
        quotient = (i-1)/LARGEUR;
        reste = (i-1)%LARGEUR;
        code = "" + quotient + reste;
        return code;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderMessage (String carre, String message) retourne une chaîne de caractères contenant l'encodage de la chaîne de caractères message passé en paramètre avec le carré de Polybe carre donné en paramètre.
    // Chaque paire d'entiers (compris entre 0 et 4) correspondant à chaque lettre sera séparée de la suivante par un espace.
    // Pensez à utiliser la fonction coderLettre.
    // Par exemple, si le carré considéré est celui sans clé ("ABCDEFGHIJKLMNOPQRSTUVXYZ") et le message à coder est "BONJOUR" alors le résultat attendu est "01 24 23 14 24 40 32 "

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message passé en paramètre est valide (càd constitué uniquement des 26 lettres de l'alphabet en majuscule)

    String coderMessage(String carre, String message){
        String messageFinal="";
        for (int cpt=0; cpt<length(message); cpt+=1){
            messageFinal+=coderLettre(carre,charAt(message,cpt));
            messageFinal+=" ";
        }
        return messageFinal;
    }
    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String decoderMessage (String carre, String messageCode) retourne une chaîne de caractères contenant le décodage de la chaîne de caractère messageCode avec le carré de Polybe carre donné en paramètre.
    // Par exemple, si carre = "ABCDEFGHIJKLMNOPQRSTUVXYZ" et messageCode = "01 24 23 14 24 40 32 " alors le résultat attendu est "BONJOUR"

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message codé passé en paramètre est valide (càd constitué de paires d'entiers compris entre 0 et LARGEUR-1 inclus et séparées par un espace)


    String decoderMessage(String carre, String messageCode){
        // '0' en ascii donne 48, or ((int) '0') -48 == 0 --> true
        // donc pour trouver l'entier d'un chiffre contenu dans une chaine de caractère, on enlève 48 à sa valeur decimal dans la table ascii

        String message = "";
        char car;
        int variable;
        char lettre;
        int temp;
        for(int i = 0; i<length(messageCode); i = i+3) {
            car = charAt(messageCode, i+1);
            if (car == '0'){
                variable = (10*((charAt(messageCode, i)) - 48) + (charAt(messageCode, i+1) - 48))/2;
                lettre = charAt(carre, variable);
                message = message + lettre;
    
            } else if(car > '0' && car <= '2'){
                    variable = (10*((charAt(messageCode, i)) - 48) + (charAt(messageCode, i+1) - 48))/2 + 1;
                    lettre = charAt(carre, variable);
                    message = message + lettre;
            } else {
                    variable = (10*((charAt(messageCode, i)) - 48) + (charAt(messageCode, i+1) - 48))/2 + 2;
                    lettre = charAt(carre, variable);
                    message = message + lettre;
            }
    
        }
        return message;
   }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction boolean estPresent(String mot, char lettre) retourne True si le caractère lettre est dans mot, faux sinon.
    // Par exemple :
    //      si mot = "BONJOUR" et lettre = 'B' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'R' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'M' alors le résultat de la fonction est False
    
    boolean estPresent(String mot, char lettre){
        boolean contient=false;
        int cpt=0;
        while (!contient && cpt<length(mot)){
            contient=charAt(mot,cpt)== lettre;
            cpt+=1;
        }
        return contient;
    }
  
    //////////////////////////////////////////////////////////////////////////
  
    // La fonction String initialiserCarreAvecCle(String cle) retourne une chaine de caractères contenant le carré de Polybe amélioré en considérant la clé passée en paramètre.
    // Pensez à utiliser la fonction estPresent
    // Par exemple, si cle = "BONJOUR" alors le résultat attendu est : "BONJURACDEFGHIKLMPQSTVXYZ"
    // Par exemple, si cle = "BUTINFORMATIQUE" alors le résultat attendu est : "BUTINFORMAQECDGHJKLPSVXYZ"

    // NB : On considère dans cette fonction que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule, le W se substituera en V)

    String initialiserCarreAvecCle(String cle){
        String alphabet=initialiserCarreSimple();
        String carreFinal="";
        boolean presence;
        for (int i=0; i<length(cle); i+=1){
            presence=estPresent(carreFinal,charAt(cle,i));
            if (!presence){
                carreFinal=carreFinal+charAt(cle,i);
            }
        }
        for (int cpt=0; cpt<length(alphabet);cpt+=1){
            presence=estPresent(carreFinal,charAt(alphabet,cpt));
            if (!presence){
                carreFinal=carreFinal+charAt(alphabet,cpt);
            }
        }
        
        return carreFinal;
    }
    
    //////////////////////////////////////////////////////////////////////////
    // LES FONCTIONS QUI SUIVENT SONT DES FONCTIONS DE VERIFICATION DE SAISIE
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction boolean estLettreMajuscule(char c) vérifie le caractère passé en paramètre est une lettre de l'alphabet en majuscule
    // Par exemple :
    //  si c='b', la fonction retourne false
    //  si c='B', la fonction retourne true

    boolean estLettreMajuscule(char c){
        return (c<='Z' && c>= 'A');

    }
   
    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estCleValide vérifie que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule)
    // Par exemple :
    //  si cle="BUTINFORMATIQUE", la fonction retourne true
    //  si cle="BUTINF ORMATIQUE", la fonction retourne false
    //  si cle="BUTINFORMATIQUE!", la fonction retourne false
    //  si cle="ButInformatique", la fonction retourne false
   
    boolean estCleValide(String cle){
        boolean verif=true;
        int cpt=0;
        while(verif && cpt<length(cle)){
            if (!estLettreMajuscule(charAt(cle,cpt))){
                verif=false;
            }
            cpt+=1;
        }
        return verif;
    }

    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estChiffreOK vérifie que le chiffre passé en paramètre est valide (càd est un entier compris entre 0 et LARGEUR-1)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estChiffreOK(int chiffre){
        return (chiffre>=0 && chiffre<LARGEUR);
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageCodeValide vérifie que le message codé passé en paramètre est valide (càd constituée uniquement de paires d'entiers compris entre 0 et LARGEUR-1 et que chaque paire est séparée de la suivante par un espace, et un espace final)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estMessageCodeValide(String messageCode){
        boolean valide=true;
        int cpt=0;
        int fin=0; // verifie que l'espace de fin est bien présent 
        int indexEspace=2;
        while (valide && cpt<length(messageCode)){
            if (cpt!=0 && cpt==indexEspace && charAt(messageCode,cpt)==' '){
                fin=0;
                indexEspace+=3;
            }
            else if (!estChiffreOK(((int)charAt(messageCode,cpt))-48)){
                valide=false;
            }
            else{
                fin+=1;
            }
            cpt+=1;
        }
        if (fin!=0){
            valide=false;
        }
        return valide;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageValide vérifie que le message passé en paramètre est valide (càd constitué uniquement de lettres de l'alphabet en majuscule)

    boolean estMessageValide(String message){
        boolean valide=true;
        int cpt=0;
        while (valide && cpt<length(message)){
            valide=charAt(message,cpt)<='Z' && charAt(message,cpt)>='A';
            cpt+=1;
        }
        return valide;
    }

    //////////////////////////////////////////////////////////////////////////
    // PROGRAMME PRINCIPAL
    //////////////////////////////////////////////////////////////////////////

    // Ecrire un programme principal qui :
    // 1. affiche un message d'introduction à l'utilisateur
    // 2. affiche un message à l'utilisateur demandant s'il veut utiliser une clé ?
    // 3. lit la réponse de l'utilisateur
    // 4. si l'utilisateur a répondu oui, demande et lit la clé souhaitée
    // 5. affiche le carré de Polybe (générique (càd sans clé) ou avec clé selon la réponse précédente de l'utilisateur)
    // 6. tant que la réponse n'est pas 0, affiche un menu et demande à l'utilisateur de saisir un entier (0 ou 1 ou 2 ou 3) pour :
    //              0. QUITTER
    //              1. CODER UN MESSAGE
    //              2. DECODER UN MESSAGE
    //              3. MODIFIER LE MODE AVEC/SANS CLE
    //        puis agit en conséquence.
    // NB : si et tant qu'une saisie de l'utilisateur n'est pas correcte, il faut la redemander (que ce soit pour la clé, le message à coder, le message à décoder ou le choix dans le menu)

    void algorithm(){
        boolean use_a_key=false;

        //message début
        // le titre est affiché encadré par des "-"
        println();
        for (int cpt=0; cpt<180; cpt+=1){
            print("-");
        }
        println();
        for (int cpt=0; cpt<65; cpt+=1){
            print(" ");
        }
        print("Codage ou décodage d'un message");
        println();
        for (int cpt=0; cpt<180; cpt+=1){
            print("-");
        }
        println();
        println();
        
        //fin message début

        // affichage d'un message de bienvenue
        println("Bonjour à vous. Vous êtes ici pour coder ou décoder un message, avec ou sans clé.");


        // choix de clé
        //on demande à l'utilisateur de choisir si il veut ou non utiliser une clé de codage/décodage
        print("Voulez-vous utilisé une clé pour coder ou décoder votre message ? Par défault, la clé utilisée sera \"ABCDEFGHIJKLMNOPQRSTUVXYZ\". (O/N) : ");

        // vérification de la saisie. Si celle-ci n'est pas correcte on lui redemande de choisir.
        String choixAvecOuSansCle=readString();
        while (!(equals(choixAvecOuSansCle,"O") ^ equals(choixAvecOuSansCle,"N"))){
            println("Le caractère saisie n'est pas valide.");
            print("Veuillez saisir à nouveau le caractère sans espace de fin et en majuscule : ");
            choixAvecOuSansCle=readString();
        }

        //saisie de clé 
        //si l'utilisateur souhaite utiliser une clé, on lui demande de saisir la clé qu'il veut utilisé et on vérifie si elle est valide, 
        //c'est à dire si elle est composé uniquement de lettres majuscule, sans espace et sans caractères spéciaux. fonction: estCleValide()
        String cleSaisie;
        String clePourCarre="";
        if (equals(choixAvecOuSansCle,"O")){
            print("Saisissez votre clé : ");
            cleSaisie=readString();
            while (!estCleValide(cleSaisie)){
                println("La clé saisie n'est pas valide !");
                print("Veuillez saisir une clé valide, c'est à dire composée de lettres majuscules, sans caractères spéciaux, sans espace : ");
                cleSaisie=readString();
            }
            println();
            //une clé est initialisée fonction: initialiserCarreAvecCle()
            clePourCarre=initialiserCarreAvecCle(cleSaisie);
            //et on l'affiche fonction: afficherCarre()
            afficherCarre(clePourCarre);
        }
        else{
            println();
            // si le choix de l'utilisateur est d'utiliser la clé basique, une clé est initalisée avec l'alphabet fonction: initialiserCarreSimple()
            clePourCarre=initialiserCarreSimple();
            // et on l'affiche fonction: afficherCarre()
            afficherCarre(clePourCarre);
        }
        println();

        // menu 
        // on affiche le menu pour que l'utilisateur choisisse ce qu'il veut faire
        String reponse="";
        while (!equals(reponse,"0")){
            println();
            for (int cpt=0; cpt<15; cpt+=1){
                print(" ");
            }
            println("MENU");
            for (int cpt1=0; cpt1<length("3. MODIFIER LE MODE AVEC/SANS CLE"); cpt1+=1){
                print("-");
            }
            println();
            println("0. QUITTER");
            println("1. CODER UN MESSAGE");
            println("2. DECODER UN MESSAGE");
            println("3. MODIFIER LE MODE AVEC/SANS CLE");
            println("4. AFFICHER LA CLÉ UTILISÉE");
            println();
            print("Que choisissez-vous ? ");
            reponse=readString();
            println();

            
            
            

            // coder le message

            String messageACoderOuDecoder;
            String changerDeMode;
            String changerDeCle;
            String reponseMode="";

            // 1.
            // ici il a choisi de coder un message
            if (equals(reponse,"1")){
                print("Que voulez-vous coder ? : ");
                messageACoderOuDecoder=readString();
                //verification de la validité du message à coder
                while (!estMessageValide(messageACoderOuDecoder) || length(messageACoderOuDecoder)<1){
                    
                    print("Saisie non valide. Saisissez à nouveau (en majuscule et sans espace) : ");
                    messageACoderOuDecoder=readString();
                }
                println();
                //affichage du message codé fonction: coderMessage()
                println("Le message a été codé, cela donne : "+coderMessage(clePourCarre,messageACoderOuDecoder));
                println();
            }

            // 2.
            // ici il a choisi de décoder un message
            else if (equals(reponse,"2")){
                print("Quel message voulez-vous décoder ? : ");
                messageACoderOuDecoder=readString();
                //vérification de la validité du message à décoder
                while (!estMessageCodeValide(messageACoderOuDecoder)|| length(messageACoderOuDecoder)<1){
                    print("Message codé non valide. Saisissez un message valide (ex: \"01 24 23 14 24 40 32 \"; attention à bien mettre l'espace de fin ! ) : ");
                    messageACoderOuDecoder=readString();
                }
                println();
                //affichage du message décodé fonction: decoderMessage()
                println("Le message a été décoder, cela donne : "+decoderMessage(clePourCarre,messageACoderOuDecoder));
                println();
            }

            // 3.
            // ici il a choisi de modifier le mode de codage, avec ou sans clé
            else if (equals(reponse,"3")){

                // affichage menu changement mode
                // nouveau menu
                while (!equals(reponseMode,"0")){
                    println();
                    println("   CHANGER DE MODE");
                    for (int cpt1=0; cpt1<22; cpt1+=1){
                        print("-");
                    }
                    println();
                    println("0. REVENIR AU MENU");
                    println("1. VOIR MODE ACTUEL");
                    println("2. CHANGER DE MODE");
                    println("3. CHANGER DE CLÉ");
                    println();
                    print("Que choisissez-vous ? ");
                    reponseMode=readString(); 
                    println();
                    
                    // 1.
                    // ici on affiche le mode dans lequel il se trouve, c'est à dire codage avec clé ou sans clé (sans clé = avec clé basique)
                    if (equals(reponseMode,"1")){
                        if (equals(choixAvecOuSansCle,"N")){
                            println("Mode actuel : SANS CLÉ");
                            
                        }
                        else{
                            println("Mode actuel : AVEC CLÉ");

                        }
                    }


                    //2.
                    //ici il décide de changer de mode, donc de coder avec une clé ou sans clé, selon ce qu'il avat choisi au début
                    else if (equals(reponseMode,"2")){
                        println();
                        if (equals(choixAvecOuSansCle,"N")){
                            // le mode codage avec clé est activé
                            println("Vous codez ou décodez maintenant à l'aide d'une clé choisie par vous même. ");
                            choixAvecOuSansCle="O";
                            //saisie de la clé
                            print("Vous devez entrer la clé souhaitée : ");
                            cleSaisie=readString();
                            //vérification de la clé saisie fonction: estCleValide()
                            while (!estCleValide(cleSaisie) || length(cleSaisie)<1){
                                println("La clé saisie n'est pas valide !");
                                print("Veuillez saisir une clé valide, c'est à dire avec des lettres majuscules, sans caractères spéciaux, sans espace : ");
                                cleSaisie=readString();
                            }
                            println("Votre nouvelle clé a été prise en compte.");
                            //initialisation de la nouvelle clé fonction: initialiserCarreAvecCle()
                            clePourCarre=initialiserCarreAvecCle(cleSaisie);
                        }
                        else{
                            //changement vers le mode codage sans clé
                            println("Vous codez ou décodez maintenant à l'aide d'une clé basique, à savoir l'alphabet");
                            choixAvecOuSansCle="N";
                            //initialisation de la clé basique  fonction: initalisationCarreSimple()
                            clePourCarre=initialiserCarreSimple();
                        }
                    }

                    // 3.
                    // ici il décide de changer de clé
                    else if(equals(reponseMode,"3") && equals(choixAvecOuSansCle,"O")){
                        print("Saisissez la nouvelle clé : ");
                        cleSaisie=readString();
                        //vérification de la clé fonction: estCleValide()
                        while (!estCleValide(cleSaisie) || length(cleSaisie)<1){
                            println("La clé saisie n'est pas valide !");
                            print("Veuillez saisir une clé valide, c'est à dire avec des lettres majuscules, sans caractères spéciaux, sans espace : ");
                            cleSaisie=readString();
                        }
                        println("Votre nouvelle clé a été prise en compte.");
                        // initialisation de la clé fonction: initialiserCarreAvecCle()
                        clePourCarre=initialiserCarreAvecCle(cleSaisie);
                        
                    }

                    else if(equals(reponseMode,"3") && equals(choixAvecOuSansCle,"N")){
                        println("Vous n'êtes pas en mode \"CODER AVEC CLÉ\". Pour changer de clé, changez d'abord de mode.");
                    }

                    else if(charAt(reponse,0)>'3' || charAt(reponse,0)<'0'){
                    print("Chiffre non valide !! Veuillez saisir un chiffre compris entre 0 et 3.");
                    println();
                
                    }

                }
                
                println();
                // affichage de la clé
                afficherCarre(clePourCarre);
            }

            // 4
            //ici il choisi d'affiché la clé qui est actuellement utilisé

            else if (equals(reponse,"4")){
                println("Clé actuellement utilisée : "+clePourCarre);
                println();
            }

            // >4
            //chiffre non-valide

            else if( length(reponse)!=1 || charAt(reponse,0)>'4' || charAt(reponse,0)<'0'){
                print("Chiffre non valide !! Veuillez saisir un chiffre compris entre 0 et 4.");
                println();
                
            }
            
        }
        println();
        println("Au revoir :) (mettez 20/20 svp) ");

    }
    //////////////////////////////////////////////////////////////////////////  

}