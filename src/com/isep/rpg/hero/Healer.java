package com.isep.rpg.hero;
import com.isep.rpg.Combatant;
import com.isep.rpg.SpellCaster;


public class Healer extends SpellCaster {




    public Healer(String n) {
        super(n, 215);
        super.regenMana(120);
        this.iniSac();
    }


    @Override
    public void fight(Combatant cible) {
        if (super.getMana() > 0) {
            super.useMana(super.getConsoSort());
            cible.loosePV(super.weapon.getDps());
            System.out.println(getClassName(this.getClass()) + " soigne : " + getClassName(cible.getClass()) + " | Soin = " + (-this.getDamage()));
        }
        else {System.out.println("Plus de mana disponible");}
    }

    @Override
    public int getDamage() {return super.weapon.getDps();}


    @Override
    public String getPrintMunition() {
        return "Mana = " + super.getMana();
    }

    @Override
    public void iniSac() {
        super.rempliSacBasique();
    }




}
