package unibo.citysimulation.model.business;
 
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;

import java.time.LocalTime;
 
/**
* Factory for creating Business objects.
* This factory creates a list of Business objects based on a list of Zone objects.
*/
public class BusinessFactory {
    /**
     * Creates a list of Business objects based on a list of Zone objects.
     *
     * @param zones the list of Zone objects
     * @return a list of Business objects
     */
    public static List<Business> createBusinessesFromFile(List<Zone> zones) {
        List<Business> businesses = new ArrayList<>();

        try {
            Gson gson = new Gson();

            JsonArray jsonArray = gson.fromJson(
                    new FileReader("src/main/resources/unibo/citysimulation/data/BusinessInfo.json"), JsonArray.class);

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                businesses.add(
                    new BusinessImpl(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("income").getAsInt(),
                        jsonObject.get("wageRate").getAsDouble(),
                        LocalTime.parse(jsonObject.get("openingTime").getAsString()),
                        LocalTime.parse(jsonObject.get("closingTime").getAsString()),
                        zones.get(jsonObject.get("zone").getAsInt())
                    )
                );
            }
            return businesses;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}