package com.isep.rpg.hero;

import com.isep.rpg.Combatant;
import com.isep.rpg.SpellCaster;


public class Mage extends SpellCaster {


    public Mage(String n) {
        super(n, 150);
        super.regenMana(80);
        this.iniSac();
    }

    @Override
    public void fight(Combatant cible) {
        if (super.getMana() > 0) {
            super.useMana(super.getConsoSort());
            cible.loosePV(super.weapon.getDps());
            System.out.println(getClassName(this.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + this.getDamage());
        }
        else {System.out.println("Plus de mana disponible");}
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }


    public int getDamage() {return this.weapon.getDps();}

    @Override
    public String getPrintMunition() {
        return "Mana = " + super.getMana();
    }






}
