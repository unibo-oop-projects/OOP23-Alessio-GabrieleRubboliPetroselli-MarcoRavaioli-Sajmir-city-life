package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileReader;
import java.time.LocalTime;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * Factory for creating Business objects.
 * This factory creates a list of Business objects based on a list of Zone objects.
 */
public class BusinessFactory {
    
    private static final Random random = new Random();
    public static List<Business> createBusinesses(List<Zone> zones){
        List<Business> businesses = new ArrayList<>();
        int n = 1000;

        try {
            Gson gson = new Gson();

            JsonArray jsonArray = gson.fromJson(
                    new FileReader("src/main/resources/unibo/citysimulation/data/BusinessInfo.json"), JsonArray.class);

                    
                    for (int i = 0; i < n; i++){
                        String name = jsonObject.get("name").getAsString();
                        double wageRate = jsonObject.get("wageRate").getAsDouble();
                        LocalTime openingTime = LocalTime.parse(jsonObject.get("openingTime").getAsString());
                        LocalTime closingTime = LocalTime.parse(jsonObject.get("closingTime").getAsString());

                        Zone zone = zones.get(random.nextInt(zones.size()));

                        Pair<Integer, Integer> position = zone.getRandomPosition();

                        boolean isOccupied = businesses
                                .stream()
                                .anyMatch(business -> business.getPosition().equals(position)); // Check if the position is already occupied

                        while (isOccupied) {
                            zone = zones.get(random.nextInt(zones.size()));
                            position = zone.getRandomPosition();
                            isOccupied = businesses
                                    .stream()
                                    .anyMatch(business -> business.getPosition().equals(position));
                        }



                        businesses.add(new BusinessImpl(name, zone, wageRate, openingTime, closingTime, position));
                    }

                    
                    return businesses;
        }   catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

