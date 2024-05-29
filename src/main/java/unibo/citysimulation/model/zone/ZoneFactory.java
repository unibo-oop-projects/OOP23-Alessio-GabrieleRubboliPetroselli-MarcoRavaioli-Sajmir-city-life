package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Factory for creating Zone objects.
 * This factory creates a list of Zone objects with predefined information.
 */
public final class ZoneFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZoneFactory.class);

    private ZoneFactory() {
    }
    /**
     * Creates a list of Zone objects from a JSON file.
     * @return the list of Zone objects
     */
    public static List<Zone> createZonesFromFile() {
        final List<Zone> zones = new ArrayList<>();

        try {
            final Gson gson = new Gson();

            final JsonArray jsonArray = gson.fromJson(
                    new FileReader("src/main/resources/unibo/citysimulation/data/ZoneInfo.json"), JsonArray.class);

            for (final JsonElement jsonElement : jsonArray) {
                final JsonObject jsonObject = jsonElement.getAsJsonObject();

                zones.add(createZone(jsonObject));
            }
            return zones;

        } catch (IOException e) {
            LOGGER.error("Error reading the JSON file: {}", e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Error parsing the JSON file: {}", e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    private static Zone createZone(final JsonObject jsonObject) {

        final String name = "boundary";

        return new Zone(
                jsonObject.get("name").getAsString(),
                jsonObject.get("personPercents").getAsFloat(),
                jsonObject.get("businessPercents").getAsFloat(),
                new Pair<>(
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("max").getAsInt()),
                new Pair<>(
                        jsonObject.get("ageMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("ageMinMax").getAsJsonObject().get("max").getAsInt()),
                new Boundary(jsonObject.get(name).getAsJsonObject().get("x1").getAsInt(),
                        jsonObject.get(name).getAsJsonObject().get("y1").getAsInt(),
                        jsonObject.get(name).getAsJsonObject().get("x2").getAsInt(),
                        jsonObject.get(name).getAsJsonObject().get("y2").getAsInt()));
    }
}
