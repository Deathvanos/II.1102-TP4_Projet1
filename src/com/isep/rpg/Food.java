package com.isep.rpg;

// Regen les points de vie
public abstract class Food extends Consumable{

    private final int effet;


    public Food(int effet) {
        this.effet = effet;
        super.setType("Food");
    }

    public int getPointRegen() {
        return this.effet;
    }
}
