package fr.rauster;

public abstract class LivingBeing {
    protected int hp = 10;
    protected int age = 0;
    protected final Aquarium aquarium;
    protected LivingBeing(Aquarium aquarium) {
        this.aquarium = aquarium;
    }
    protected LivingBeing(Aquarium aquarium, int hp) {
        this(aquarium);
        this.hp = hp;
    }
    protected abstract void breed();
    
    public void live(){
        age++;
        if (age >= 20) aquarium.removeBeing(this);
    }
    
    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            aquarium.removeBeing(this);
        }
    }
}
