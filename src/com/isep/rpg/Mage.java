package com.isep.rpg;

public class Mage extends SpellCaster {

    private Weapon weapon = new Weapon("baton de destruction", 10, 20, "boule de feu");

    public Mage(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.weapon.getDps());
    }

    public int getDamage() {return this.weapon.getDps();}
}
