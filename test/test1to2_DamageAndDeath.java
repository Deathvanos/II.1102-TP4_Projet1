import com.isep.rpg.Game;
import org.testng.annotations.Test;

public class test1to2_DamageAndDeath {

    @Test
    void testObjet() {

        /**
         * Si l’on prend plus de dégâts que l’on a de PV, on meurt ;
         * si aucun joueur n’est en vie, on a perdu ;
         */

        // Pour tester ces deux points, vous devez jouer
        System.out.println("Start Game Test : Mini RPG Lite 3000");
        new Game();

    }
}
