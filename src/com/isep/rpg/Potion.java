package com.isep.rpg;

// regen les points de magie
public abstract class Potion extends Consumable{

    private final int effet;

    public Potion(int effet) {
        this.effet = effet;
        super.setType("Potion");
    }

    public int getPointRegen() {return this.effet;}

}
