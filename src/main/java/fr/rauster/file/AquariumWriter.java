package fr.rauster.file;

import fr.rauster.Fish;
import fr.rauster.Plant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AquariumWriter extends BufferedWriter {
    JSONObject line = new JSONObject();
    public AquariumWriter(String path) throws IOException {
        super(new BufferedWriter(new FileWriter(path)));
    }
    
    public void addFishes(List<Fish> fishes) {
        JSONArray array = new JSONArray();
        fishes.forEach(f -> {
                    array.put(new JSONObject()
                            .put("name", f.getName())
                            .put("gender", f.getGender())
                            .put("type", f.getType())
                            .put("hp", f.getHp())
                            .put("age", f.getAge()));
        });
        line.put("fishes", array);
    }
    
    public void addPlants(List<Plant> plants) {
        JSONArray array = new JSONArray();
        plants.forEach(p -> {
            array.put(new JSONObject()
                    .put("hp", p.getHp())
                    .put("age", p.getAge()));
        });
        line.put("plants", array);
    }
    
    public void writeLine(int state) {
        try {
            line.put("state", state);
            write(line.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
