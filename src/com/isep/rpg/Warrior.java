package com.isep.rpg;

public class Warrior extends Hero{

    int damage = 2;
    public Warrior(String n, int hp) {
        super(n, hp);
    }


    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.damage);
    }

    public int getDamage() {return this.damage;}
}
