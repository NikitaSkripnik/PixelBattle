package pixelbattle;

import pixelbattle.core.Fighter;
import pixelbattle.enemies.Goblin;
import pixelbattle.enemies.Ogre;
import pixelbattle.enemies.Skeleton;
import pixelbattle.enemies.Slime;
import pixelbattle.engine.BattlePanel;
import pixelbattle.heroes.Archer;
import pixelbattle.heroes.Knight;
import pixelbattle.heroes.Mage;

import javax.swing.*;
import java.awt.*;

/**
 * Pixel Battle — покрокова битва.
 * Точка входу: створює команди та відкриває вікно гри.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fighter[] heroes = { new Knight(), new Mage(), new Archer() };
            Fighter[] enemies1 = { new Skeleton(), new Goblin(), new Ogre() };
            Fighter[] enemies2 = { new Skeleton(), new Skeleton(), new Skeleton() };
            // БОНУС: додайте сюди нового ворога, наприклад new Skeleton()
            Fighter[][] phases = {enemies1, enemies2};
            BattlePanel battle = new BattlePanel(heroes, phases);

            JFrame frame = new JFrame("Pixel Battle");
            frame.add(battle, BorderLayout.CENTER);
            frame.add(battle.getBottomPanel(), BorderLayout.SOUTH);
            frame.pack();
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
