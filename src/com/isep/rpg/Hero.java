package com.isep.rpg;

import com.isep.rpg.food.Lembdas;
import com.isep.rpg.food.Pain;

import java.util.ArrayList;

public abstract class Hero extends Combatant {

    // Creation du sac d'objet
    private final ArrayList<Consumable> sacHero = new ArrayList<>();
    protected Armor armure;
    private double efficaciteConso = 1.0;


    public Hero(String n, int hp) {
        super(n, hp);
    }

    public void setArmure(Armor armure) {
        this.armure = armure;
    }

    public Armor getArmure() {return this.armure;}

    public double getEfficaciteConso() {return this.efficaciteConso;}

    public abstract int chooseNumUpgrade(String txt);

    public void rempliSacBasique() {
        // Ajout d'objet pour regen les pv
        this.ajoutObjettoSac(new Pain());
        this.ajoutObjettoSac(new Pain());
        this.ajoutObjettoSac(new Lembdas());
    }
    public void ajoutObjettoSac(Consumable conso) {
        this.sacHero.add(conso);
    }

    public ArrayList<Consumable> getSacHero() {return this.sacHero;}
    public void useItem(int index, Combatant cbt) {
        Consumable conso = this.sacHero.get(index);
        System.out.println(super.getName() + " utilise l'objet : " + getClassName(conso.getClass()));
        int point;
        // Verification du type de cosommable
        switch(conso.getType()) {
            case "Food" -> {
                point = (int) Math.round(((Food) conso).getPointRegen() * efficaciteConso);
                super.loosePV(-point);
                System.out.println("La nouriture a renforcé pour " + super.getName() + " de " + point + " hp");
            }
            case "Potion" -> {
                point = (int) Math.round(((Potion) conso).getPointRegen() * efficaciteConso);
                ((SpellCaster) cbt).regenMana(point);
                System.out.println("Regénération pour " + super.getName() + " de " + point + " points de mana");
            }
        }

        // Objet consommé, on le supprime
        this.sacHero.remove(index);
    }


    public abstract String getPrintMunition();
    public abstract void iniSac();

    public void upgradeHero(int numChoose) {
        switch (numChoose) {
            case 1 -> {
                // Augmentation des dégats de l'arme
                // On va appliquer un multiplicateur de dégat à hauteur de 10%
                int newDPS = super.weapon.getDps() + super.weapon.getDps()*10/100;
                System.out.println(super.weapon.getName() + " damage : " + super.weapon.getDps() + " -> " + newDPS);
                super.weapon.setDps(newDPS);
            }
            case 2 -> {
                // Augmentation de la protection de l'armure
                // On ajout +20 en defence
                System.out.println(this.armure.getName() + " protection : " + this.armure.getProtection() + " -> " + (this.armure.getProtection()+20));
                this.armure.setProtection(this.armure.getProtection()+20);
            }
            case 3 -> {
                // Augmentation du multiplicateur d'efficacité des Potions et Food
                this.efficaciteConso += 0.25;
                System.out.println("Nouvelle efficacité aux consommables : x" + this.efficaciteConso);
            }
            case 4 -> {
                // Remet les elements de base contenu dans le sac
                this.iniSac();
                System.out.println("Sac remis à neuf : " + Game.listeConsumableSTR(this.getSacHero()));
            }

        }
    }



    @Override
    public void addProtection() {
        super.protection = this.armure.getProtection();
    }
    public void looseProctection() {super.protection = 0;}



}
