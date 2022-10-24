package com.isep.rpg;

public class Dragon extends Enemy {

    private Weapon weapon = new Weapon("griffe", 25, -1, "entaille");

    public Dragon(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(this.weapon.getDps());
    }


    public int getDamage() {return this.weapon.getDps();}
}
