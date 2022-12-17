import com.isep.rpg.Game;
import com.isep.rpg.armor.Armure_en_cuir;
import com.isep.rpg.armor.Armure_en_fer;
import com.isep.rpg.armor.Robe_de_sorcier;
import com.isep.rpg.hero.Hunter;
import com.isep.rpg.hero.Mage;
import com.isep.rpg.hero.Warrior;
import com.isep.rpg.weapon.Arc;
import com.isep.rpg.weapon.Baton_de_destruction;
import com.isep.rpg.weapon.Epee;
import org.testng.annotations.Test;

public class test7_AttributionRecompenses {

    @Test
    void test() {
        System.out.println("Test 7 : Attribution de récompense après une victoire.\n");

        // Création des personnages
        Warrior w = new Warrior("Warrior1");
        w.setWeapon(new Epee());
        w.setArmure(new Armure_en_fer());
        Mage m = new Mage("Mage1");
        m.setWeapon(new Baton_de_destruction());
        m.setArmure(new Robe_de_sorcier());
        Hunter hu = new Hunter("Hunter1");
        hu.setWeapon(new Arc());
        hu.setArmure(new Armure_en_cuir());
        //Healer he = new Healer("Healer1");
        //he.setWeapon(new Baton_de_soin());
        //he.setArmure(new Robe_de_sorcier());

        // Choix de l'amélioration
        int numGift;

        System.out.println("Choix 1 : augmenter les dégâts que l’on cause");
        System.out.println("Vérification Avant : " + w.getWeapon().getDps());
        numGift = 1;
        w.upgradeHero(numGift);
        System.out.println("Vérification Après : " + w.getWeapon().getDps());
        System.out.println("\n");

        System.out.println("Choix 2 : augmenter notre résistance aux attaques");
        numGift = 2;
        System.out.println("Vérification Avant : " + w.getArmure().getProtection());
        w.upgradeHero(numGift);
        System.out.println("Vérification Après : " + w.getArmure().getProtection());
        System.out.println("\n");

        System.out.println("Choix 3 : augmenter l’efficacité de la potion et de la nourriture");
        numGift = 3;
        System.out.println("Vérification Avant : " + w.getEfficaciteConso());
        w.upgradeHero(numGift);
        System.out.println("Vérification Après : " + w.getEfficaciteConso());
        System.out.println("\n");

        System.out.println("Choix 4 : augmenter le nombre de potions ou de nourriture");
        numGift = 4;
        System.out.println("Vérification Warrior1 Avant : " + Game.listeConsumableSTR(w.getSacHero()));
        w.upgradeHero(numGift);
        System.out.println("Vérification Warrior1 Après : " + Game.listeConsumableSTR(w.getSacHero()));
        System.out.println("Vérification Mage1 Avant : " + Game.listeConsumableSTR(m.getSacHero()));
        m.upgradeHero(numGift);
        System.out.println("Vérification Mage1 Après : " + Game.listeConsumableSTR(m.getSacHero()));
        System.out.println("\n");

        System.out.println("Choix 5 : augmenter le nombre de flèches (pour le Hunter), diminuer le coût en mana pour les sorceleurs");
        numGift = 5;
        System.out.println("Vérification Hunter1 Avant : " + hu.getPrintMunition());
        hu.upgradeHero(numGift);
        System.out.println("Vérification Hunter1 Après : " + hu.getPrintMunition());
        System.out.println("Vérification Mage1 Avant : " + m.getConsoSort());
        m.upgradeHero(numGift);
        System.out.println("Vérification Mage1 Après : " + m.getConsoSort());
        System.out.println("\n");


        // test pour choisir une option
        String txt;
            // Pour un Warrior
        txt = "Que voulez vous faire pour " + Game.getClassName(w.getClass()) + "[" + w.getName() + "] ? " +
                "Increase damage:1 - Increase protection:2 - " +
                "Increase consumable efficiency:3 - Increase Stuff:4";
        //w.chooseNumUpgrade(txt);

            // Pour un Hunter
        txt = "Que voulez vous faire pour " + Game.getClassName(hu.getClass()) + "[" + hu.getName() + "] ? " +
                "Increase damage:1 - Increase protection:2 - " +
                "Increase consumable efficiency:3 - Increase Stuff:4";
        //hu.chooseNumUpgrade(txt);

            // Pour une Spellcaster
        txt = "Que voulez vous faire pour " + Game.getClassName(m.getClass()) + "[" + m.getName() + "] ? " +
                "Increase damage:1 - Increase protection:2 - " +
                "Increase consumable efficiency:3 - Increase Stuff:4";
        //m.chooseNumUpgrade(txt);


    }
}
