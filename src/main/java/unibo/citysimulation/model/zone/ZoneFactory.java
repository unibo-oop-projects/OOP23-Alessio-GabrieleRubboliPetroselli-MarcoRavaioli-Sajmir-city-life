package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Factory for creating Zone objects.
 * This factory creates a list of Zone objects with predefined information.
 */
public class ZoneFactory {

    public static List<Zone> createZonesFromFile() {
        List<Zone> zones = new ArrayList<Zone>();

        try {
            Gson gson = new Gson();

            JsonArray jsonArray = gson.fromJson(
                    new FileReader("src/main/resources/unibo/citysimulation/data/ZoneInfo.json"), JsonArray.class);

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                zones.add(createZone(jsonObject));
            }
            return zones;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Zone createZone(JsonObject jsonObject) {
        return new ZoneImpl(
                jsonObject.get("name").getAsString(),
                jsonObject.get("personPercents").getAsFloat(),
                jsonObject.get("businessPercents").getAsFloat(),
                new Pair<Integer, Integer>(
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("max").getAsInt()),
                new Pair<Integer, Integer>(
                        jsonObject.get("ageMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("ageMinMax").getAsJsonObject().get("max").getAsInt()),
                new Boundary(jsonObject.get("boundary").getAsJsonObject().get("x1").getAsInt(),
                        jsonObject.get("boundary").getAsJsonObject().get("y1").getAsInt(),
                        jsonObject.get("boundary").getAsJsonObject().get("x2").getAsInt(),
                        jsonObject.get("boundary").getAsJsonObject().get("y2").getAsInt()));
    }
}
