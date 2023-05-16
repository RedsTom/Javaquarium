package fr.rauster;

public class Plant extends LivingBeing {
    
    public Plant(Aquarium aquarium, int hp) {
        super(aquarium, hp);
    }
    public Plant(Aquarium aquarium, int hp, int age) {
        super(aquarium, hp);
        this.age = age;
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
        if (isTooPopulated(aquarium.getPlants().size(), Aquarium.MAX_PLANT_POPULATION)) return;
        hp /= 2;
        aquarium.addPlant(hp);
    }
    
    @Override
    public String toString() {
        return "Plant{" +
                "hp=" + hp +
                ", age=" + age +
                '}';
    }
}
