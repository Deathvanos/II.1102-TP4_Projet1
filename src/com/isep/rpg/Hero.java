package com.isep.rpg;

public abstract class Hero extends Combatant {

    // Creation du sac d'objet
    private Item[] sacHero = new Item[5];


    public Hero(String n, int hp) {
        super(n, hp);

        // Ajout d'objet pour regen les pv
        this.addItemToSac(new Food(), 0);
        this.addItemToSac(new Food(), 1);
        this.addItemToSac(new Food(), 2);
    }

    public Item[] getSacHero() {return this.sacHero;}

    public void addItemToSac(Item i, int pos) {this.sacHero[pos] = i;}
}
