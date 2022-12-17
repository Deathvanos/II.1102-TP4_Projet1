package com.isep.rpg;

public abstract class Weapon extends Item{
    String name;
    int dps;


    public Weapon(String name, int dps) {
        this.name = name;
        this.dps = dps;

    }



    public String getName() {return this.name;}
    public int getDps() {return this.dps;}

    public void setDps(int dps) {
        this.dps = dps;
    }
}
