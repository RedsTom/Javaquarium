package fr.rauster;

import java.util.Random;

public class Fish extends LivingBeing{
    
    private final Random r = new Random();
    private final String name;
    private Gender gender;
    private final FishType type;
    public Fish(Aquarium aquarium, String name, Gender gender, FishType type) {
        super(aquarium);
        this.name = name;
        this.gender = gender;
        this.type = type;
    }
    
    @Override
    public void live() {
        super.live();
        damage(1);
        
        if (hp <= 5) eat();
        else breed();
        
        if (getType().getSexualityType() == SexualityType.HERMAPHRODITE_WITH_AGE && age == 10) {
            this.gender = gender.opposite();
        }
    }
    
    @Override
    protected void breed() {
        Fish fish = randomFish();
        if (fish == null || fish == this) return;
        if (fish.getType() != getType()) return;
        if (fish.getType().getSexualityType() != SexualityType.OPPORTUNIST_HERMAPHRODITE
                && fish.getGender() == getGender()) return;
        
        Gender gender = Gender.FEMALE;
        if (r.nextBoolean()) gender = Gender.MALE;
        aquarium.addFish(gender, getType());
    }
    public void eat() {
        if (getType().getDiet() == Diet.HERBIVORE) {
            eatRandomPlant();
        }
        if (getType().getDiet() == Diet.CARNIVORE) {
            eatRandomFish();
        }
    }
    private void eatRandomPlant() {
        Plant randomPlant = null;
        if (aquarium.getPlants().size() > 0)
            randomPlant = aquarium.getPlants().get(r.nextInt(aquarium.getPlants().size()));
        eat(randomPlant);
    }
    private void eatRandomFish() {
        Fish randomFish = randomFish();
        while (randomFish.getType() == getType() || randomFish == this) {
            randomFish = randomFish();
        }
        eat(randomFish);
    }
    private Fish randomFish(){
        if (aquarium.getFishes().size() > 0)
            return aquarium.getFishes().get(r.nextInt(aquarium.getFishes().size()));
        else return null;
    }
    private void eat(Plant plant) {
        if (plant == null) return;
        plant.damage(2);
        hp += 3;
    }
    private void eat(Fish fish) {
        if (fish == null) return;
        fish.damage(4);
        hp += 5;
    }
    
    
    public String getName() {
        return name;
    }
    public Gender getGender() {
        return gender;
    }
    public FishType getType() {
        return type;
    }
    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", type=" + type +
                ", hp=" + hp +
                ", age=" + age +
                '}';
    }
}
