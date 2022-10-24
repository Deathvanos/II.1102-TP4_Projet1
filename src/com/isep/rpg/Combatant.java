package com.isep.rpg;

public abstract class Combatant {

    private String name;
    private int healthPoint;

    public Combatant(String n, int hp) {
        this.name = n;
        this.healthPoint = hp;
    }

    public void loosePV(int damage) {
        this.healthPoint -= damage;
    }

    // Classe abstraite
    public abstract void fight(Combatant combatant);

    // Accesseurs GET
    public String getName() {return this.name;}
    public int getHealthPoint() {return this.healthPoint;}

}
