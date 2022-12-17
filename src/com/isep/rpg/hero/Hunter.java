package com.isep.rpg.hero;

import com.isep.rpg.*;

public class Hunter extends Hero {

    private int fleches;
    private final int lotFleche = 10;

    public Hunter(String n) {
        super(n, 175);
        this.iniSac();
        this.addFleches(this.lotFleche);
    }

    public void addFleches(int nbArrows) {this.fleches += nbArrows;}

    @Override
    public void fight(Combatant cible) {
        if (this.fleches > 0) {
            this.fleches--;
            cible.loosePV(this.weapon.getDps());
            System.out.println(getClassName(this.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + this.getDamage());
        }
        else {System.out.println("Plus de fleches disponible");}
    }

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "Flèches = " + this.fleches;
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    @Override
    public void upgradeHero(int numChoose) {
        super.upgradeHero(numChoose);
        if (numChoose == 5) {
            // Pour l'archer, rajoute un stocke de fleche
            System.out.println("Stock de flèches : " + this.fleches + " -> " + (this.fleches+this.lotFleche));
            this.addFleches(this.lotFleche);
        }
    }


    @Override
    public int chooseNumUpgrade(String txt) {
        String txtup = txt + " - Add new Arrows:5";
        return Game.safeIntScannerIntervalle(1, 5, txtup);
    }

}
