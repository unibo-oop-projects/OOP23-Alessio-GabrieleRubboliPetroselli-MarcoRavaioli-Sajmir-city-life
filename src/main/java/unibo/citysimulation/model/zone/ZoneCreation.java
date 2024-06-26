package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ZoneCreation class is responsible for creating zones from a JSON file.
 * It provides a static method to create zones and a private helper method to
 * create a single zone.
 */
public final class ZoneCreation {
    private static final String BOUNDARY = "boundary";
    private static final Logger LOGGER = LoggerFactory.getLogger(ZoneCreation.class);

    private ZoneCreation() {
    }

    /**
     * Creates a list of zones from a JSON file.
     *
     * @return the list of created zones
     */
    public static List<Zone> createZonesFromFile() {
        final List<Zone> zones = new ArrayList<>();
        try (InputStream inputStream = ZoneCreation.class.getClassLoader()
                .getResourceAsStream("unibo/citysimulation/data/ZoneInfo.json");
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            if (inputStream == null) {
                throw new IOException("Resource not found: unibo/citysimulation/data/ZoneInfo.json");
            }

            final Gson gson = new Gson();
            final JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
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

    /**
     * Creates a single zone from a JSON object.
     *
     * @param jsonObject the JSON object representing the zone
     * @return the created zone
     */
    private static Zone createZone(final JsonObject jsonObject) {
        return new Zone(
                jsonObject.get("name").getAsString(),
                jsonObject.get("personPercents").getAsInt(),
                jsonObject.get("businessPercents").getAsInt(),
                new Pair<>(
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("wellfareMinMax").getAsJsonObject().get("max").getAsInt()),
                new Pair<>(
                        jsonObject.get("ageMinMax").getAsJsonObject().get("min").getAsInt(),
                        jsonObject.get("ageMinMax").getAsJsonObject().get("max").getAsInt()),
                new Boundary(
                        jsonObject.get(BOUNDARY).getAsJsonObject().get("x1").getAsInt(),
                        jsonObject.get(BOUNDARY).getAsJsonObject().get("y1").getAsInt(),
                        jsonObject.get(BOUNDARY).getAsJsonObject().get("x2").getAsInt(),
                        jsonObject.get(BOUNDARY).getAsJsonObject().get("y2").getAsInt()));
    }
}
