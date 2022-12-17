package com.isep.rpg;

import com.isep.rpg.armor.*;
import com.isep.rpg.enemy.*;
//import com.isep.rpg.food.*;
import com.isep.rpg.hero.*;
import com.isep.rpg.weapon.*;
//import com.isep.rpg.potion.*;
//import com.isep.rpg.weapon.*;


import java.util.*;

public class Game {

    // Parametres du jeu
    boolean gameOver;
    int gameRound;
    int nbCombatant;

    // Parametre d'une vague
    boolean waveOver;
    int wave;

    // Personnages du jeu
    Combatant[] listHeros;
    Combatant[] listEnemy;
    Combatant[] listCombatant;

    public Game() {

        /*---------- Création des personnages ----------*/
        this.chooseNbHeros();
        this.createAllHeros();
        //this.createPersoForTest();
        this.startGame();
        this.endGameResult();

    }


    private void startGame() {
        this.gameOver = false;
        this.gameRound = 1;
        /*---------- Début du jeu ----------*/
        while (!this.gameOver) {
            String txt;
            // Si ce n'est pas une cinquieme manche -> manche normal
            if (this.gameRound % 5 != 0){
                this.createAllEnemy();
                txt = "NOUVELLE Manche : " + this.gameRound + " -------";
            }
            // Sinon c'est la manche final de Boss
            else {
                this.createBoss();
                txt = "Manche Finale : Le BOSS ---";
                this.gameOver = true;
            }
            System.out.println("\n------------------------------------");
            System.out.println("-------- " + txt);
            System.out.println("------------------------------------");
            this.startManche();
            this.gameRound++;
        }
    }


    private void startManche() {
        waveOver = false;
        wave = 1;
        /*---------- Début de la manche ----------*/
        while (!this.waveOver) {
            System.out.println("\n------------------------------------");
            System.out.println("-------- NOUVELLE VAGUE : " + this.wave + " --------");
            System.out.println("------------------------------------");
            // Concate les deux liste de combattants
            this.defineOdreDePassage();
            // Action pour chaque combattant
            for (Combatant cbt : listCombatant) {
                // Condition pour verifier le camp du combattant
                List<Combatant> liste1 = Arrays.asList(this.listEnemy);
                List<Combatant> liste2 = Arrays.asList(this.listHeros);
                // Le combattant est un mechant
                if (liste1.contains(cbt)) {
                    this.actionEnemy(cbt);
                }
                // Le combattant est un gentil
                else if (liste2.contains(cbt)){
                    this.actionHero(cbt);
                }
                // S'il n'y a plus d'enemis alors fin de la manche
                if (this.listEnemy.length == 0) {
                    this.waveOver = true;
                    System.out.println("Le camp des monstres a été eliminé");
                    if (!this.gameOver) {this.levelUp();}
                    break;
                }
                // Regarde si le joueur est toujours vivant
                else if (this.finMortPlayer()) {
                    this.waveOver = true;
                    this.gameOver = true;
                    break;
                }

            }
            // Test la fin de la manche
            if (this.waveOver) {
                System.out.println("\n\n\n\n\nWAVE OVER");
            } else {
                // Sinon prochaine vague
                this.wave++;
                // On remet a 0 les protections des heros
                for (Combatant cbt : this.listHeros) {
                    ((Hero) cbt).looseProctection();
                }
            }
        }
    }

    private void endGameResult() {
        System.out.println("FIN DE LA PARTIE");
        if (!this.finMortPlayer()) {System.out.println("Vous avez gagné");}
        else {System.out.println("vous avez perdu");}
    }
    private boolean finMortPlayer() {
        return this.listHeros.length == 0;
    }

    private void defineOdreDePassage(){
        // Concate les deux liste de combattants
        Combatant[] listCombatant = new Combatant[this.listHeros.length + this.listEnemy.length];
        System.arraycopy(this.listHeros, 0, listCombatant, 0, this.listHeros.length);
        System.arraycopy(this.listEnemy, 0, listCombatant, this.listHeros.length, this.listEnemy.length);
        // trie au hasars cette liste
        List<Combatant> list = Arrays.asList(listCombatant);
        Collections.shuffle(list);
        list.toArray(listCombatant);
        this.listCombatant = listCombatant;
        System.out.println("Odre de passage : " + this.listeCombattantSTR(listCombatant) + "\n");
    }

    private void actionHero(Combatant cbt) {
        this.affichePlateau();
        // C'est au joueur de jouer, il choisit entre 3 actions
        System.out.println("A votre tour !\nC'est à " + getClassName(cbt.getClass()) + "[" + cbt.getName() + "] de jouer.");

        // Choix de l'action
        String txt = "Que voulez vous faire ?\nAttaquer:1 - Defendre:2 - User d'un consommable:3";
        int actionchoose = safeIntScannerIntervalle(1, 3, txt);

        // Execution de l'action choisi
        switch (actionchoose) {
            // Attaquer
            case 1 -> {
                // Verification de l'unité Héro
                String className = getClassName(cbt.getClass());
                Combatant[] listCible;
                if (Objects.equals(className, "Healer")) {
                    listCible = this.listHeros;
                }
                else {
                    listCible = this.listEnemy;
                }
                // Choix de la cible par le player
                txt = "Qui voulez vous attaquer de [0 à " + (listCible.length-1) + "] ? " + listeCombattantSTR(listCible);
                int enemyChoose = safeIntScannerIntervalle(0, listCible.length-1, txt);
                // recuperation de la cible
                Combatant cible = listCible[enemyChoose];
                // attaque la cible choisie
                cbt.fight(cible);
                // Verification de l'etat de santé de la cible
                this.verifMortEnemie(cible, enemyChoose);
            }
            // Defendre
            case 2 -> {
                cbt.addProtection();
                System.out.println("Une protection de " + cbt.getProtection() + " a été appliquer pour ce tour");
            }
            // User d'un consommable
            case 3 -> {
                // Recuperatoin et affichage du sac des consomables
                ArrayList<Consumable> lstConso = ((Hero) cbt).getSacHero();
                int size = lstConso.size();
                if (size > 0) {
                    System.out.println("Vos consommables : " + listeConsumableSTR(lstConso));
                    // Selectoin de l'objet
                    txt = "Selectionner un objet entre [0 et " + (size-1) + "]";
                    int itemChoose = safeIntScannerIntervalle(0, size-1, txt);
                    // Utlisation de l'objet
                    ((Hero) cbt).useItem(itemChoose, cbt);
                }
                else {
                    System.out.println("Votre sac est vide");
                    actionHero(cbt);}
            }
        }


    }

    public static int safeIntScannerIntervalle(int min, int max, String msg) {
        boolean confirm = false;
        int number = -1;
        while (!confirm) {
            number = SafeIntScanner(msg);
            if (number >= min & number <= max) {confirm = true;}
            else{System.out.println("Action invalide");}
        }
        return number;

    }

    public void levelUp() {
        System.out.println("\n\n\n\n");
        System.out.println("Tous les énemies ont été vaincu, vous pouvez améliorer vos Héros !\n");
        for (Combatant c: this.listHeros) {
            String txt = "Que voulez vous faire pour " + getClassName(c.getClass()) + "[" + c.getName() + "] ? " +
                    "Increase damage:1 - Increase protection:2 - " +
                    "Increase consumable efficiency:3 - Increase Stuff:4";
            int num = ((Hero) c).chooseNumUpgrade(txt);
            ((Hero) c).upgradeHero(num);
        }
    }

    public void verifMortAmi(Combatant cible, int n) {
        // Si l'attakant est mort
        if (cible.getHealthPoint() <= 0) {
            // on le vire des 2 listes auquel il est consigné
            this.listHeros = this.removeElement(this.listHeros, n);
            System.out.println(getClassName(cible.getClass()) + " est mort !");
        }
    }

    public void verifMortEnemie(Combatant cible, int n) {
        // Si l'attakant est mort
        if (cible.getHealthPoint() <= 0) {
            // on le vire des 2 listes auquel il est consigné
            this.listEnemy = this.removeElement(this.listEnemy, n);
            System.out.println(getClassName(cible.getClass()) + " est mort !");
        }
    }


    public void actionEnemy(Combatant cbt) {
        // Choix d'un nombre aleatoire entre 0 et this.listHeros.length
        Random r = new Random ();
        int randomNumber = r.nextInt(this.listHeros.length);
        // recuperation de la cible
        Combatant cible = listHeros[randomNumber];
        // attaque une cible au hazard
        cbt.fight(cible);

        // Verification de l'etat de santé de la cible
        verifMortAmi(cible, randomNumber);
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
        this.nbCombatant = 4;
        this.listHeros = new Combatant[this.nbCombatant];
        this.listEnemy = new Combatant[this.nbCombatant];
        // H1
        this.listHeros[0] = new Hunter("Henry le chasseur");
        this.listHeros[0].setWeapon(new Arc());
        ((Hero) this.listHeros[0]).setArmure(new Armure_en_cuir());
        // H2
        this.listHeros[1] = new Warrior("Wandrille de warrior");
        this.listHeros[1].setWeapon(new Epee());
        ((Hero) this.listHeros[1]).setArmure(new Armure_en_fer());
        // H3
        this.listHeros[2] = new Healer("hectore le soigneur");
        this.listHeros[2].setWeapon(new Baton_de_soin());
        ((Hero) this.listHeros[2]).setArmure(new Robe_de_sorcier());
        // H4
        this.listHeros[3] = new Mage("Malou le mage");
        this.listHeros[3].setWeapon(new Baton_de_destruction());
        ((Hero) this.listHeros[3]).setArmure(new Robe_de_sorcier());
    }


    public void chooseNbHeros() {
        // Choix du nombre de Heros
        this.nbCombatant = SafeIntScanner("Choix du nombre de Hero");
        // Liste des Heros du joueur
        this.listHeros = new Combatant[nbCombatant];
    }



    public void createAllHeros() {
        // Creation des heros
        for (int i = 0; i < this.listHeros.length; i++) {
            System.out.println("\n\nHero " + (i+1));

            // Selection du nom du hero
            String name = SafeStringScanner("Nom de votre Hero ?");

            // Selection de la classe
            String txt = "Choix de la classe :\nHunter:1 - Warrior:2 - Mage:3 - Healer:4";
            int numClasse = safeIntScannerIntervalle(1, 4, txt);
            // Creation du hero
            switch (numClasse) {
                case 1 -> {
                    this.listHeros[i] = new Hunter(name);
                    this.listHeros[i].setWeapon(new Arc());
                    ((Hero) this.listHeros[i]).setArmure(new Armure_en_cuir());
                }
                case 2 -> {
                    this.listHeros[i] = new Warrior(name);
                    this.listHeros[i].setWeapon(new Epee());
                    ((Hero) this.listHeros[i]).setArmure(new Armure_en_fer());
                }
                case 3 -> {
                    this.listHeros[i] = new Mage(name);
                    this.listHeros[i].setWeapon(new Baton_de_destruction());
                    ((Hero) this.listHeros[i]).setArmure(new Robe_de_sorcier());
                }
                case 4 -> {
                    this.listHeros[i] = new Healer(name);
                    this.listHeros[i].setWeapon(new Baton_de_soin());
                    ((Hero) this.listHeros[i]).setArmure(new Robe_de_sorcier());
                }
                default -> System.out.println("Nombre non compris en 1 et 4, recommencer");
            }
        }
    }

    public void createAllEnemy() {
        this.listEnemy = new Combatant[this.nbCombatant];
        int min_val = 1;
        int max_val = 4;
        Random ran = new Random();
        // Creation des enemys
        for (int j = 0; j < this.listEnemy.length; j++) {
            int x = ran.nextInt(max_val) + min_val;
            switch (x) {
                case 1 -> this.listEnemy[j] = new Gobelin(new Arc());
                case 2 -> this.listEnemy[j] = new Gobelin(new Epee());
                case 3 -> this.listEnemy[j] = new Squelette(new Arc());
                case 4 -> this.listEnemy[j] = new Troll(new Gourdin());
            }
        }
    }

    public void createBoss() {
        this.listEnemy = new Combatant[1];
        // Creation du boss
        this.listEnemy[0] = new Dragon(new Lance_Flamme());

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
        System.out.println("\n------------------------- Vague number : " + this.wave + " -------------------------");
        for (Combatant c : this.listHeros) {
            System.out.println(getClassName(c.getClass())
                    + "[" + c.getName()
                    + "] : hp="
                    + c.getHealthPoint() + "(" + c.getProtection() + ")"
                    + " | dps="
                    + c.getDamage()
                    + " | "
                    + ((Hero) c).getPrintMunition()
                    );
        }
        System.out.println();
        for (Combatant c :this.listEnemy) {
            System.out.println(getClassName(c.getClass())
                            + "[" + c.getName()
                            + "] : hp="
                            + c.getHealthPoint()
                            + " | dps="
                       + c.getDamage()
            );
        }
        System.out.println("\nOdre de passage : " + this.listeCombattantSTR(this.listCombatant));
        System.out.println("____________________________________________________________________");
    }

    public String listeCombattantSTR(Object[] lstCbt){
        String[] lstPrint = new String[lstCbt.length];
         for (int i=0; i< lstCbt.length; i++) {
             lstPrint[i] = getClassName(lstCbt[i].getClass());
        }
        return Arrays.toString(lstPrint);
    }

    public static String listeConsumableSTR(ArrayList<Consumable> lstConso){
        String[] lstPrint = new String[lstConso.size()];
        for (int i=0; i< lstConso.size(); i++) {
            lstPrint[i] = getClassName(lstConso.get(i).getClass());
        }
        return Arrays.toString(lstPrint);
    }

    /**
     * Demande a l'utilisateur de saisir un nombre
     * @param text texte a afficher a l'utilisateur
     * @return le nombre que l'utilisateur a saisie
     */
    public static int SafeIntScanner(String text) {
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
