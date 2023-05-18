package fr.rauster.file;

import fr.rauster.Aquarium;
import fr.rauster.LivingBeing;
import fr.rauster.Plant;
import fr.rauster.fish.Fish;
import fr.rauster.fish.FishType;
import fr.rauster.fish.Gender;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AquariumReader extends BufferedReader {
    
    private final Aquarium aquarium;
    public AquariumReader(Aquarium aquarium, String path) throws FileNotFoundException {
        super(new FileReader(path));
        this.aquarium = aquarium;
    }
    
    public List<LivingBeing> read(int state) {
        List<LivingBeing> list = new ArrayList<>();
        List<JSONObject> lines = new ArrayList<>();
        try {
            String current = readLine();
            while (current != null && !current.equals("")) { //get all the lines
                lines.add(new JSONObject(current));
                current = readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        JSONObject line = null;
        for (JSONObject json : lines) { //get the line with the corresponding state
            if ((int) json.get("state") == state) {
                line = json;
                break;
            }
        }
        if (line == null) {
            return new ArrayList<>();
        }
        
        JSONArray plants = (JSONArray) line.get("plants");
        for (int i = 0 ; i < plants.length() ; i++) {
            JSONObject plant = plants.getJSONObject(i);
            list.add(new Plant(aquarium, (int) plant.get("hp"), (int) plant.get("age")));
        }
        
        JSONArray fishes = (JSONArray) line.get("fishes");
        for (int i = 0 ; i < fishes.length() ; i++) {
            JSONObject fish = fishes.getJSONObject(i);
            list.add(new Fish(aquarium,
                    (String) fish.get("name"),
                    Gender.valueOf((String) fish.get("gender")),
                    FishType.valueOf((String) fish.get("type")),
                    (int) fish.get("age"),
                    (int) fish.get("hp")
            ));
        }
        return list;
    }
    
}
