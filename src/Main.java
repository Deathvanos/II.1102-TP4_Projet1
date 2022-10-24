import com.isep.rpg.Dragon;
import com.isep.rpg.Game;
import com.isep.rpg.Warrior;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Player!");
        System.out.println("Start Game : Mini RPG Lite 3000");

        Game game = new Game();

        /*
        Warrior w = new Warrior("Wawa", 10);
        Dragon d = new Dragon("Drago", 20);
        w.fight(d);
        showStatus(w, d);
        */

    }

    private static void showStatus(Warrior w, Dragon d) {
        System.out.println(w.getName() + ":" + w.getHealthPoint() +"\n" +
                d.getName() + ":" + d.getHealthPoint());
    }
}