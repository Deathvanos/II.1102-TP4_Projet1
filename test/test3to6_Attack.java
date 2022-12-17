import com.isep.rpg.Combatant;
import com.isep.rpg.Hero;
import com.isep.rpg.SpellCaster;
import com.isep.rpg.armor.Armure_en_cuir;
import com.isep.rpg.armor.Armure_en_fer;
import com.isep.rpg.armor.Robe_de_sorcier;
import com.isep.rpg.enemy.Dragon;
import com.isep.rpg.hero.Healer;
import com.isep.rpg.hero.Hunter;
import com.isep.rpg.hero.Mage;
import com.isep.rpg.hero.Warrior;
import com.isep.rpg.weapon.*;
import org.testng.annotations.Test;

public class test3to6_Attack {

    @Test
    void test() {
        System.out.println("Test 3 à 6 :\n");

        // Création des camps de combattant
        Combatant[] listHeros = new Combatant[4];
        Combatant[] listEnemy = new Combatant[1];

        // Ajout de Héros
        listHeros[0] = new Warrior("Wandrille de warrior");
        listHeros[0].setWeapon(new Epee());
        ((Hero) listHeros[0]).setArmure(new Armure_en_fer());
        listHeros[1] = new Mage("Malou le mage");
        listHeros[1].setWeapon(new Baton_de_destruction());
        ((Hero) listHeros[1]).setArmure(new Robe_de_sorcier());
        listHeros[2] = new Healer("hectore le soigneur");
        listHeros[2].setWeapon(new Baton_de_soin());
        ((Hero) listHeros[2]).setArmure(new Robe_de_sorcier());
        listHeros[3] = new Hunter("Henry le chasseur");
        listHeros[3].setWeapon(new Arc());
        ((Hero) listHeros[3]).setArmure(new Armure_en_cuir());

        // Ajout des Méchants
        listEnemy[0] = new Dragon(new Lance_Flamme());


        attaqueWarrior(listHeros, listEnemy);
        System.out.println("\n\n");

        attaqueMage(listHeros, listEnemy);
        System.out.println("\n\n");

        attaqueHealer(listHeros, listEnemy);
        System.out.println("\n\n");

        attaqueHunter(listHeros, listEnemy);
        System.out.println("\n\n");

        attaqueDragon(listHeros, listEnemy);
        System.out.println("\n\n");
    }


    /** WARRIOR
     *  On inflige bien les dégâts dans la bonne fourchette de dégâts
     */
    public void attaqueWarrior(Combatant[] listHeros, Combatant[] listEnemy) {
        System.out.println("---------- attaqueWarrior ----------");
        int num = 0;
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
        System.out.println("Damage Warrior : " + listHeros[num].getDamage());
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        for (int i = 0; i <= 2; i++) {
            listHeros[num].fight(listEnemy[0]);
        }
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
    }


    /** MAGE
     *  La quantité de mana diminue après avoir lancé un sort
     *  Si l’on n’a pas assez de mana, on ne peut pas lancer le sort
     *  On inflige bien les dégâts dans la bonne fourchette de dégâts
     */
    public void attaqueMage(Combatant[] listHeros, Combatant[] listEnemy) {
        System.out.println("---------- attaqueMage ----------");
        int num = 1;
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
        System.out.println("Damage Mage : " + listHeros[num].getDamage());
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("Conso sort Mage : " + ((SpellCaster) listHeros[num]).getConsoSort());
        for (int i = 0; i <= 9; i++) {
            listHeros[num].fight(listEnemy[0]);
        }
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
    }


    /** HEALER
     *  La quantité de mana diminue après avoir lancé un sort
     *  Si l’on n’a pas assez de mana, on ne peut pas lancer le sort
     *  On inflige bien les dégâts dans la bonne fourchette de dégâts
     */
    public void attaqueHealer(Combatant[] listHeros, Combatant[] listEnemy) {
        System.out.println("---------- attaqueHealer ----------");
        int num = 2;
        System.out.println("HP Mage : " + listHeros[num - 1].getHealthPoint());
        System.out.println("Damage Healer : " + listHeros[num].getDamage());
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("Conso sort Healer : " + ((SpellCaster) listHeros[num]).getConsoSort());
        for (int i = 0; i <= 13; i++) {
            listHeros[num].fight(listHeros[num - 1]);
        }
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("HP Mage : " + listHeros[num - 1].getHealthPoint());
    }


    /** HUNTER
     *  La quantité de flèches diminue quand on tire;
     *  Si l’on n’a pas assez de flèches, on ne peut pas lancer l'attaque
     *  On inflige bien les dégâts dans la bonne fourchette de dégâts
     */
    public void attaqueHunter(Combatant[] listHeros, Combatant[] listEnemy) {
        System.out.println("---------- attaqueHunter ----------");
        int num = 3;
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
        System.out.println("Damage Hunter : " + listHeros[num].getDamage());
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        for (int i = 0; i <= 11; i++) {
            listHeros[num].fight(listEnemy[0]);
        }
        System.out.println(((Hero) listHeros[num]).getPrintMunition());
        System.out.println("HP Dragon : " + listEnemy[0].getHealthPoint());
    }


    /** DRAGON
     *  On inflige bien les dégâts dans la bonne fourchette de dégâts
     */
    public void attaqueDragon(Combatant[] listHeros, Combatant[] listEnemy) {
        System.out.println("---------- attaqueDragon ----------");
        int num = 0;
        System.out.println("HP Mage : " + listHeros[1].getHealthPoint());
        System.out.println("Damage Dragon : " + listEnemy[num].getDamage());
        for (int i=0; i<=2; i++){
            listHeros[num].fight(listHeros[1]);
        }
        System.out.println("HP Mage : " + listHeros[1].getHealthPoint());
    }


}
