package com.isep.rpg;

public class Mage extends SpellCaster {
    public Mage(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(2);
    }
}
