package com.isep.rpg;

public class Dragon extends Enemy {
    public Dragon(String n, int hp) {
        super(n, hp);
    }

    @Override
    public void fight(Combatant combatant) {
        combatant.loosePV(1);
    }
}
