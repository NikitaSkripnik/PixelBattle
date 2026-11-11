package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

public class Skeleton extends Enemy {

    public Skeleton(){
        super("Скелет", 50, new Sprite("skelet_idle_anim", 4, 6));
        setSecondChance(true);

        addSkill(new Skill("Кістяний удар", Target.ENEMY, 0, (user, target) -> {
            return user.getName() + " б'є кісткою " + target.getName() + ": "
                    + target.takeDamage(random(8, 12)) + " шкоди";
        }));

    }
    @Override
    protected int modifyIncomingDamage(int damage) {
        if (getHp() - damage <= 0 && getSecondChance()) {
            addToPopup(new Popup("Воскресає", Popup.Kind.HEAL));
            setSecondChance(false);
            return getHp() - 1;
        } else return damage;
    }
}
