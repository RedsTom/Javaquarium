package fr.rauster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium {
    
    Random r = new Random();
    private final List<LivingBeing> livingBeings = new ArrayList<>();
    private int time;
    
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        aquarium.startSimulation();
    }
    public void startSimulation() {
        addFish(Gender.MALE, FishType.GROUPER);
        addFish(Gender.FEMALE, FishType.GROUPER);
        addFish(Gender.MALE, FishType.TUNA);
        addFish(Gender.FEMALE, FishType.TUNA);
        addFish(Gender.MALE, FishType.CLOWNFISH);
        addFish(Gender.FEMALE, FishType.CLOWNFISH);
        addFish(Gender.MALE, FishType.SOLE);
        addFish(Gender.FEMALE, FishType.SOLE);
        addFish(Gender.MALE, FishType.BASS);
        addFish(Gender.FEMALE, FishType.BASS);
        addFish(Gender.MALE, FishType.CARP);
        addFish(Gender.FEMALE, FishType.CARP);
        
        addPlants(10);
        
        for (int i = 0 ; i < 30 ; i++) {
            spendTime();
        }
    }
    
    public void spendTime() {
        time++;
        
        for (LivingBeing livingBeing : new ArrayList<>(livingBeings)) {
           livingBeing.live();
        }
        
        System.out.println("Time "+time+", " + getPlants().size() + " plants and "+getFishes().size() + " fishes");
//        getFishes().forEach(System.out::println);
    }
    
    public void addFish(String name, Gender gender, FishType type) {
        Fish fish = new Fish(this, name, gender, type);
        livingBeings.add(fish);
    }
    public void addFish(Gender gender, FishType type) {
        Fish fish = new Fish(this, String.valueOf(r.nextInt(10000)), gender, type);
        livingBeings.add(fish);
    }
    private void addPlants(int number) {
        for (int j = 0 ; j < number ; j++) {
            addPlant();
        }
    }
    public void addPlant() {
        addPlant(10);
    }
    public void addPlant(int hp) {
        Plant plant = new Plant(this, hp);
        livingBeings.add(plant);
    }
    public void removeBeing(LivingBeing livingBeing) {
        livingBeings.remove(livingBeing);
    }
    
    
    public List<Fish> getFishes(){
        List<Fish> fishes = new ArrayList<>();
        livingBeings.forEach(lb -> {
            if (lb instanceof Fish f) fishes.add(f);
        });
        return fishes;
    }
    public List<Plant> getPlants(){
        List<Plant> plants = new ArrayList<>();
        livingBeings.forEach(lb -> {
            if (lb instanceof Plant p) plants.add(p);
        });
        return plants;
    }
}