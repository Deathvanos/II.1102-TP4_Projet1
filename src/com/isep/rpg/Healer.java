package com.isep.rpg;

public class Healer extends SpellCaster{


    public Healer(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(2);
    }
}
