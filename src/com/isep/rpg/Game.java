package com.isep.rpg;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

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

            this.listHeros[0].fight(this.listEnemy[1]);
            this.listHeros[1].fight(this.listEnemy[1]);
            this.listEnemy[0].fight(this.listHeros[0]);

            // Test la fin du jeu
            this.wave++;
            if (wave >= 1) {
                System.out.println("\n\nGAME OVER");
                this.affichePlateau();
                this.gameOver = true;
            }
        }

    }


    public void createPersoForTest() {
        this.listHeros = new Combatant[2];
        this.listEnemy = new Combatant[2];
        this.listHeros[0] = new Warrior("ww", 3000);
        this.listHeros[1] = new Mage("hh", 1000);
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
            this.listEnemy[j] = new Dragon("drago", 3000);
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
                 //   +c.getAttack
                    );
        }
        System.out.println("\n");
        for (Combatant c :this.listEnemy) {
            System.out.println(getClassName(c.getClass())
                            + "[" + c.getName()
                            + "] : hp="
                            + c.getHealthPoint()
                            + " | dps="
                    //   +c.getAttack
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
