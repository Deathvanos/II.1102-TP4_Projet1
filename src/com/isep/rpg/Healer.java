package com.isep.rpg;

public class Healer extends SpellCaster{

    private Weapon weapon = new Weapon("baton de soin", 10, 15, "boule de soin");

    public Healer(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.weapon.getDps());
    }
    @Override
    public int getDamage() {return this.weapon.getDps();}
}
