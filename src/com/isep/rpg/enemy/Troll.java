package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Troll extends Enemy {

    public Troll(Weapon weapon) {
        super("Troll", 25);
        super.setWeapon(weapon);
    }
}
