package com.isep.rpg;

public abstract class Enemy extends Combatant{

    public Enemy(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void addProtection() {
        super.protection = 0;
    }

    @Override
    public void fight(Combatant cible) {
        cible.loosePV(this.weapon.getDps());
        System.out.println(getClassName(this.getClass()) + " attaque : " + getClassName(cible.getClass()) + " | Degats = " + this.getDamage());

    }
    public int getDamage() {return this.weapon.getDps();}
}
