package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Dragon extends Enemy {

    public Dragon(Weapon weapon) {
        super("Dragon", 150);
        super.setWeapon(weapon);
    }
}
