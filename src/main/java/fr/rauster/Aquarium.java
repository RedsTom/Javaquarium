package fr.rauster;

import fr.rauster.file.AquariumReader;
import fr.rauster.file.AquariumWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium {
    
    private final Random r = new Random();
    private final List<LivingBeing> livingBeings = new ArrayList<>();
    private int time;
    private static final int TOTAL_ITERATIONS = 20;
    private static final int START_ITERATION = 40;
    public static final int MAX_FISH_POPULATION = 50;
    public static final int MAX_PLANT_POPULATION = 30;
    private AquariumWriter writer = null;
    private AquariumWriter logWriter = null;
    
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        aquarium.startSimulation(START_ITERATION, TOTAL_ITERATIONS);
    }
    
    public Aquarium() {
    }
    public void startSimulation(int start, int iterations) {
        time = start;
        
        if (start == 0) {
            
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
            
            addPlants(15);
        }
        else {
            setAquariumContent(start);
        }
        
        try {
            this.writer = new AquariumWriter("aquarium.fish");
            this.writer = new AquariumWriter("log.fish");
            for (int i = 0 ; i < iterations ; i++) {
                spendTime();
            }
            writer.close();
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void spendTime() {
        time++;
        
        for (LivingBeing livingBeing : new ArrayList<>(livingBeings)) {
           livingBeing.live();
        }
        
        try {
            writer.addFishes(getFishes());
            writer.addPlants(getPlants());
            writer.writeLine(time);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("Time "+time+", " + getPlants().size() + " plants and "+getFishes().size() + " fishes");
    }
    
    public void addFish(String name, Gender gender, FishType type) {
        Fish fish = new Fish(this, name, gender, type);
        livingBeings.add(fish);
    }
    public void addFish(Gender gender, FishType type) {
        addFish(String.valueOf(r.nextInt(100000)), gender, type);
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
    public void setAquariumContent(int state) {
        try (AquariumReader reader = new AquariumReader(this, "aquarium.fish")) {
            livingBeings.clear();
            livingBeings.addAll(reader.read(state));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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