import com.isep.rpg.Dragon;
import com.isep.rpg.Game;
import com.isep.rpg.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
/*

        int[] l11 = new int[3];
        l11[0] = 1;
        l11[1] = 2;
        l11[2] = 3;

        int[] l12 = new int[3];
        l12[0] = 4;
        l12[1] = 5;
        l12[2] = 6;

        int[] concate = new int[l11.length + l12.length];
        System.arraycopy(l11, 0, concate, 0, l11.length);
        System.arraycopy(l12, 0, concate, l11.length, l12.length);

        System.out.println(Arrays.toString(l11));
        System.out.println(Arrays.toString(l12));
        System.out.println("Concatenated Array: " + Arrays.toString(concate));



        ArrayList<String> l1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        ArrayList<String> l2 = new ArrayList<>(Arrays.asList("4", "5", "6"));
        List<String> res = Stream.concat(l1.stream(), l2.stream()).toList();
        System.out.println("Liste 1: " + l1);
        System.out.println("Liste 2: " + l2);
        System.out.println("Résultat: " + res);

*/

    }

    private static void showStatus(Warrior w, Dragon d) {
        System.out.println(w.getName() + ":" + w.getHealthPoint() +"\n" +
                d.getName() + ":" + d.getHealthPoint());
    }
}