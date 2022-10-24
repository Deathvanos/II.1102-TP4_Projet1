package com.isep.rpg;

public class Warrior extends Hero{


    private Weapon weapon = new Weapon("épée", 10, -1, "lame");

    public Warrior(String n, int hp) {
        super(n, hp);
    }


    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.weapon.getDps());
    }

    public int getDamage() {return this.weapon.getDps();}
}
