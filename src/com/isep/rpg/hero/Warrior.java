package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.Game;
import com.isep.rpg.Hero;


public class Warrior extends Hero {



    public Warrior(String n) {
        super(n, 200);
        this.iniSac();
    }


    @Override
    public void fight(Combatant cible) {
        cible.loosePV(this.weapon.getDps());
        System.out.println(getClassName(this.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + this.getDamage());
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }

    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "loop";
    }



    @Override
    public int chooseNumUpgrade(String txt) {
        return Game.safeIntScannerIntervalle(1, 4, txt);
    }

}
