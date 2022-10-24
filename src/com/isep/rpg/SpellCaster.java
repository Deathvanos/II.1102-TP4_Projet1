package com.isep.rpg;

public abstract class SpellCaster extends Hero{

    public SpellCaster(String n, int hp) {
        super(n, hp);

        // Ajout d'objet pour regen les pv
        super.addItemToSac(new Potion(), 3);
        super.addItemToSac(new Potion(), 4);


    }
}
