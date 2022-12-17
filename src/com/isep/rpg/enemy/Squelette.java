package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Squelette extends Enemy {

    public Squelette(Weapon weapon) {
        super("Squelette", 75);
        super.setWeapon(weapon);
    }


}
