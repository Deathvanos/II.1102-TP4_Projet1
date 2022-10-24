package com.isep.rpg;

public class Hunter extends Hero{
    public Hunter(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(2);
    }
}
