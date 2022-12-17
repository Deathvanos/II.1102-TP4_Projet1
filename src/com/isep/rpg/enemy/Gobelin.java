package com.isep.rpg.enemy;
import com.isep.rpg.Enemy;
import com.isep.rpg.Weapon;

public class Gobelin extends Enemy {

    public Gobelin(Weapon weapon) {
        super("Gobelin", 50);
        super.setWeapon(weapon);
    }


}
