package com.isep.rpg;

import java.util.*;

public class Game {

    // Parametres du jeu
    boolean gameOver = false;
    int wave = 0;

    // Personnages du jeu
    Combatant[] listHeros;
    Combatant[] listEnemy;

    public Game() {

        /*---------- Création des personnages ----------*/
        //this.chooseNbHeros();
        //this.createAllHeros();
        this.createPersoForTest();
        this.createAllEnemy();




        /*---------- Affichage des informations ----------*/
        //this.afficheCamp(listHeros, "g");
        //this.afficheCamp(listEnemy, "m");


        /*---------- Début du jeu ----------*/

        while (!this.gameOver) {


            this.affichePlateau();


            //this.listHeros[0].fight(this.listEnemy[1]);
            //this.listHeros[1].fight(this.listEnemy[1]);
            //this.listEnemy[0].fight(this.listHeros[0]);


            System.out.println("\n\n");
            /*  Methode 1*/
            // Choix des actions pour chaque heros
            // Les enemies ne peuvent que attaquer (cible aleatoire)
            // L'odre d'attaque des heros est aleatoire

            /* Methode 2*/ // Selection de cette methode
            // Fusion et trie aleatoire des 2 listes de heros
            //boucle sur la nouvelle liste
            // si c'est un mechant
                // attaque une cible aleatoire
            // si c'est un gentil
                // choix de l'action par le player



            // Concate les deux liste de combattants
            Combatant[] listCombatant = new Combatant[this.listHeros.length + this.listEnemy.length];
            System.arraycopy(this.listHeros, 0, listCombatant, 0, this.listHeros.length);
            System.arraycopy(this.listEnemy, 0, listCombatant, this.listHeros.length, this.listEnemy.length);
            //System.out.println("Concatenated Array : " + Arrays.toString(listCombatant));
            // trie au hasars cette liste
            List<Combatant> list = Arrays.asList(listCombatant);
            Collections.shuffle(list);
            list.toArray(listCombatant);
            //System.out.println("Random Array : " + Arrays.toString(listCombatant));


            // Action pour chaque combattant
            for (Combatant cbt : listCombatant) {

                // Condition pour verifier le camp du combattant
                List<Combatant> liste = Arrays.asList(this.listEnemy);
                // Le combattant est un mechant
                if (liste.contains(cbt)) {

                    // Choix d'un nombre aleatoire entre 0 et this.listHeros.length
                    Random r = new Random ();
                    int randomNumber = r.nextInt(this.listHeros.length);
                    // recuperation de la cible
                    Combatant cible = listHeros[randomNumber];
                    // attaque une cible au hazard
                    cbt.fight(cible);
                    System.out.println(getClassName(cbt.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + cbt.getDamage());

                    // Si l'attakant est mort
                    if (cible.getHealthPoint() <= 0) {
                        // on le vire des 2 listes auquel il est consigné
                        this.listHeros = this.removeElement(this.listHeros, randomNumber);
                        System.out.println(getClassName(cible.getClass()) + " est mort !");
                    }

                }
                // Le combattant est un gentil
                else {

                    // Choix d'un nombre aleatoire entre 0 et this.listHeros.length
                    Random r = new Random ();
                    int randomNumber = r.nextInt(this.listEnemy.length);
                    // recuperation de la cible
                    Combatant cible = listEnemy[randomNumber];
                    // attaque une cible au hazard
                    cbt.fight(cible);
                    System.out.println(getClassName(cbt.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + cbt.getDamage());

                    // try car un element mort joue quand meme

                    // Si l'attakant est mort
                    if (cible.getHealthPoint() <= 0) {
                        // on le vire des 2 listes auquel il est consigné
                        this.listEnemy = this.removeElement(this.listEnemy, randomNumber);
                        System.out.println(getClassName(cible.getClass()) + " est mort !");
                    }
                }

                if (this.listEnemy.length == 0 | this.listHeros.length == 0) {
                    this.gameOver = true;
                    System.out.println("Un des deux camps a été eliminé");
                    break;
                }

            }






            // Test la fin du jeu
            if (wave >= 50 | this.gameOver) {
                System.out.println("\n\nGAME OVER");
                this.affichePlateau();
                this.gameOver = true;
            }
            // Sinon prochaine vague
            this.wave++;
        }

    }


    // https://www.delftstack.com/fr/howto/java/java-remove-element-from-array-and-shift/#:~:text=Utilisez%20la%20boucle%20for%20pour%20supprimer%20l%E2%80%99%C3%A9l%C3%A9ment%20du,collection%20de%20taille%20fixe%20d%E2%80%99%C3%A9l%C3%A9ments%20du%20m%C3%AAme%20type.
    public Combatant[] removeElement( Combatant [] arr, int index ){
        Combatant[] arrDestination = new Combatant[arr.length - 1];
        int remainingElements = arr.length - ( index + 1 );
        System.arraycopy(arr, 0, arrDestination, 0, index);
        System.arraycopy(arr, index + 1, arrDestination, index, remainingElements);
        //System.out.println("Elements -- "  + Arrays.toString());
        return arrDestination;
    }




    public void createPersoForTest() {
        this.listHeros = new Combatant[4];
        this.listEnemy = new Combatant[4];
        this.listHeros[0] = new Hunter("aa", 200);
        this.listHeros[1] = new Warrior("bb", 300);
        this.listHeros[2] = new Healer("cc", 250);
        this.listHeros[3] = new Mage("dd", 100);
    }


    public void chooseNbHeros() {
        // Choix du nombre de Heros
        int nbCombatant = SafeIntScanner("Choix du nombre de Hero");
        // Liste des Heros du joueur
        this.listHeros = new Combatant[nbCombatant];
        // Liste des Enemy
        this.listEnemy = new Combatant[nbCombatant];
    }


    public void createAllHeros() {
        // Creation des heros
        for (int i = 0; i < this.listHeros.length; i++) {
            System.out.println("\n\nHero " + (i+1));

            // Selection du nom du hero
            String name = SafeStringScanner("Nom de votre Hero ?");

            // Selection de la classe et creation du hero
            boolean confirm = false;
            int numClasse;
            while (!confirm) {
                // Selection de la classe
                numClasse = SafeIntScanner("Choix de la classe :\nHunter:1 - Warrior:2 - Mage:3 - Healer:4");
                // Creation du hero en fonction de sa classe si
                switch (numClasse) {
                    case 1 -> {this.listHeros[i] = new Hunter(name, 3000); confirm = true;}
                    case 2 -> {this.listHeros[i] = new Warrior(name, 5000); confirm = true;}
                    case 3 -> {this.listHeros[i] = new Mage(name, 2000); confirm = true;}
                    case 4 -> {this.listHeros[i] = new Healer(name, 4000); confirm = true;}
                    default -> System.out.println("Nombre non compris en 1 et 4, recommencer");
                }
            }
        }
    }

    public void createAllEnemy() {
        // Creation des enemys
        for (int j = 0; j < this.listEnemy.length; j++) {
            this.listEnemy[j] = new Dragon("drago", 80);
        }
    }

    // http://www.java2s.com/Code/Java/Reflection/Gettheclassnamewithorwithoutthepackage.htm
    public static String getClassName(Class c) {
        String className = c.getName();
        int firstChar;
        firstChar = className.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            className = className.substring(firstChar);
        }
        return className;
    }

    public void affichePlateau(){
        System.out.println("\n-----------------------------------------------------------------------------------");
        System.out.println(("Vague number : " + this.wave + "\n"));
        for (Combatant c : this.listHeros) {
            System.out.println(getClassName(c.getClass())
                    + "[" + c.getName()
                    + "] : hp="
                    + c.getHealthPoint()
                    + " | dps="
                    + c.getDamage()
                    );
        }
        System.out.println("\n");
        for (Combatant c :this.listEnemy) {
            System.out.println(getClassName(c.getClass())
                            + "[" + c.getName()
                            + "] : hp="
                            + c.getHealthPoint()
                            + " | dps="
                       + c.getDamage()
            );
        }
        System.out.println("___________________________________________________________________________________");
    }

    public void afficheCamp(Combatant[] camp, String type){
        System.out.println("\n-----------------------------------------------------------------------------------");
         for (Combatant c : camp) {
            System.out.println(c.getClass() + " - "
                    + c.getName() + " - "
                    + c.getHealthPoint());
            if (Objects.equals(type, "g")) {
                // Affiche une list pour les combattants de class fille heros
                System.out.println("                             " + Arrays.toString(((Hero) c).getSacHero()));
            }
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    /**
     * Demande a l'utilisateur de saisir un nombre
     * @param text texte a afficher a l'utilisateur
     * @return le nombre que l'utilisateur a saisie
     */
    public int SafeIntScanner(String text) {
        System.out.println(text);
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Erreur, recommencer");
            }
        }
    }

    /**
     * Demande a l'utilisateur de saisir un mot
     * @param text texte a afficher a l'utilisateur
     * @return le mot que l'utilisateur a saisie
     */
    public String SafeStringScanner(String text) {
        System.out.println(text);
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Erreur, recommencer");
            }
        }
    }


}
