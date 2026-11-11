package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Ultimate;
import pixelbattle.core.Fighter;

/** Лицар — міцний воїн ближнього бою. */
public class Knight extends Hero {

    public Knight() {
        super("Лицар", 110, new Sprite("knight_m_idle_anim", 4, 4));

        // ПРИКЛАД: звичайна атака, ефект записаний ЛЯМБДОЮ
        addSkill(new Skill("Удар мечем", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(12, 18));
            return user.getName() + " рубає " + target.getName() + ": " + dmg + " шкоди";
        }));

        addSkill(new Skill("Щит", Target.SELF, 2, (user, target) -> {
            user.setGuarding(true);
            return user.getName() + " захищається ";
        }));

           setUltimate(new Ultimate() {
               @Override public String getName() { return "Небесний молот"; }
               @Override public String getDescription() { return "Кожному живому ворогу наносить 15 шкоди та оглушення на 1 хід"; }
               @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                   for (Fighter e : enemies) { if(e.isAlive()){e.takeDamage(15); e.stun(1);} }
                   return "Б'є усіх ворогів велетенським сяючим молотом";
               }
           });
    }
    //"Клятва важкої зброї"
    @Override
    protected int modifyIncomingDamage(int damage) {
        return Math.max(damage-4, 0);
    }
}
