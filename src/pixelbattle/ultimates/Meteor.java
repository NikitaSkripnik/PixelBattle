package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

import static pixelbattle.core.Fighter.random;

public class Meteor implements Ultimate {
    @Override public String getName() { return "Метеорит"; }
    @Override public String getDescription() { return "Наносить від 20 до 30 шкоди всім ворогам"; }
    @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int dmg = random(20, 30);
        for (Fighter e : enemies) { if(e.isAlive()) e.takeDamage(dmg); }
        return "Викликає величезний палаючий камінь з неба";
    }
    }

