package com.isep.rpg;

public class Weapon extends Item{
    String name;
    int dps;
    int munition;
    String nameMun;

    public Weapon(String name, int dps, int munition, String nameMun) {
        this.name = name;
        this.dps = dps;
        this.munition = munition;
        this.nameMun = nameMun;

    }

    public boolean canAttak(int totMun) {
        if (this.munition - totMun >= 0) {return true;}
        return false;
    }

    public String getName() {return this.name;}
    public int getDps() {return this.dps;}
    public int getNumition() {return this.munition;}
    public String getNameMun() {return this.nameMun;}
}
