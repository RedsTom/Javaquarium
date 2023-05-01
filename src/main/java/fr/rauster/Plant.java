package fr.rauster;

public class Plant extends LivingBeing {
    
    public Plant(Aquarium aquarium, int hp) {
        super(aquarium, hp);
    }
    @Override
    public void live() {
        super.live();
        hp++;
        if (hp >= 10) {
            breed();
        }
    }
    @Override
    protected void breed() {
        hp /= 2;
        aquarium.addPlant(hp);
    }
}
