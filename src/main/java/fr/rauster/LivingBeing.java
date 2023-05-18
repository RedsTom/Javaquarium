package fr.rauster;

import java.util.Random;

public abstract class LivingBeing {
    
    protected final Aquarium aquarium;
    protected final Random r = new Random();
    protected int hp = 10;
    protected int age = 0;
    protected LivingBeing(Aquarium aquarium, int hp) {
        this(aquarium);
        this.hp = hp;
    }
    protected LivingBeing(Aquarium aquarium) {
        this.aquarium = aquarium;
    }
    protected abstract void breed();
    
    public void live() {
        age++;
        if (age >= 20) aquarium.removeBeing(this);
        damage(0);
    }
    
    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            aquarium.removeBeing(this);
        }
    }
    protected boolean isTooPopulated(int population, int maximum) {
        return Math.exp(-0.2 * (population - maximum + 5)) <= r.nextDouble();
    }
    
    public int getHp() {
        return hp;
    }
    public int getAge() {
        return age;
    }
}
