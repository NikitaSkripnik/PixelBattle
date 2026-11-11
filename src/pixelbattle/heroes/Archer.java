package pixelbattle.heroes;

import pixelbattle.core.*;
import pixelbattle.engine.Sprite;

import java.util.Random;


/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) ->
                user.getName() + " двічі стріляє в " + target.getName() + ": "
                        + (target.takeDamage(random(7, 10))+target.takeDamage(random(7, 10))) + " шкоди"));


        setUltimate(new Ultimate() {
            private int shots = 3;
            @Override public String getName() { return "Злива стріл"; }
            @Override public String getDescription() { return "Стріляє по випадковим цілям та наносить від 4 до 8 шкоди. Перший раз робить 3 постріли. Кожен наступний раз робить на 1 постріл більше"; }
            @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                for (int i = 0; i<shots; i++) {
                    while (true){
                        Random random = new Random();
                        Fighter e = enemies[random.nextInt(enemies.length)];
                        if (e.isAlive()){
                            e.takeDamage(random(4,8));
                            break;
                        }
                    }
                }
                shots++;
                return "Робить " + (shots-1) + " блискавично-швидких пострілів";
            }
        });
    }

    //"Ухилення"
    @Override
    protected int modifyIncomingDamage(int damage) {
        if(chance(25))return 0;
        else return damage;
    }
}
