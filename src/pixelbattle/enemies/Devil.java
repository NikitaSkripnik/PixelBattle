package pixelbattle.enemies;

import pixelbattle.core.*;
import pixelbattle.engine.BattlePanel;
import pixelbattle.engine.Sprite;
import pixelbattle.heroes.Knight;

import java.util.ArrayList;
import java.util.List;

public class Devil extends Enemy{
    private int receivedHits = 0;
        public Devil(){
            super("Диявол", 300, new Sprite("big_demon_idle_anim", 4, 6));
            setSecondChance(true);
            addSkill(new Skill("Укус диявола", Target.ENEMY, 0, (user, target) -> {
                return user.getName() + " кусає " + target.getName() + ": "
                        + target.takeDamage(random(5, 10)) + " шкоди";
            }));

            addSkill(new Skill("Провокація", Target.SELF, 3, (user, target) -> {
                        user.applyTaunt(2);
                        return user.getName() + " провокує героїв на 2 раунди";
                    }));

            addSkill(new Skill("Зміцнення", Target.ALLY, 1, (user, target) -> {
                target.addToPopup(new Popup("Міцнішає", Popup.Kind.HEAL));
                if(target instanceof Skeleton)target.setSecondChance(true);
                return user.getName() + " повертає здібність воскресати " + target.getName();
            }));

            setUltimate(new Ultimate() {
                @Override public String getName() { return "Армагедон"; }
                @Override public String getDescription() { return "Ніщо живе не встоїть перед ним"; }
                @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                    int ticks = receivedHits*4;
                    receivedHits = 0;

                    for (int i = 0; i < ticks; i++) {
                        List<Fighter> aliveEnemies = new ArrayList<>();

                        for (Fighter enemy : enemies) {
                            if (enemy.isAlive()) {
                                aliveEnemies.add(enemy);
                            }
                        }

                        if (aliveEnemies.isEmpty()) break;

                        Fighter target = aliveEnemies.get(random(0, aliveEnemies.size() - 1));
                        if(target instanceof Knight)target.takeDamage(6);
                        else target.takeDamage(2);
                    }

                    for (int i = 0; i < ticks; i++) {
                        List<Fighter> aliveAllies = new ArrayList<>();

                        for (Fighter ally : allies) {
                            if (ally.isAlive()) {
                                aliveAllies.add(ally);
                            }
                        }

                        if (aliveAllies.isEmpty()) break;

                        Fighter target = aliveAllies.get(random(0, aliveAllies.size() - 1));
                        target.heal(1);
                    }
                    return "Ніщо живе не встоїть перед ним";
                }
            });
        }

    @Override
    protected int modifyIncomingDamage(int damage) {
            receivedHits+=1;
        int dmg = Math.max(Math.max(damage-2, damage-receivedHits), 0);
        if (getHp() - dmg <= 0 && getSecondChance()) {
            addToPopup(new Popup("Воскресає", Popup.Kind.HEAL));
            setSecondChance(false);
            return getHp()-5;
        }
        else return damage;
    }
}
