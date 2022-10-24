package com.isep.rpg;

public class Hunter extends Hero{

    private Weapon weapon = new Weapon("arc", 10, 20, "fleche");
    public Hunter(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.weapon.getDps());
    }

    public int getDamage() {return this.weapon.getDps();}
}
