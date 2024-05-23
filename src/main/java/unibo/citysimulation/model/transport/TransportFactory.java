package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Factory for creating TransportLine objects.
 * This factory creates a list of TransportLine objects based on a list of Zone
 * objects.
 */
public final class TransportFactory {
    private TransportFactory() {
        // private constructor to prevent instantiation
    }

    /**
     * Create a list of TransportLine objects based on a list of Zone objects.
     * 
     * @param zones List of Zone objects.
     * @return List of TransportLine objects.
     */
    public static List<TransportLine> createTransportsFromFile(final List<Zone> zones) {
        List<TransportLine> lines = new ArrayList<>();

        try {
            Gson gson = new Gson();

            JsonArray jsonArray = gson.fromJson(
                    new FileReader("src/main/resources/unibo/citysimulation/data/TransportInfo.json"), JsonArray.class);

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                lines.add(
                    new TransportLineImpl(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("capacity").getAsInt(),
                        jsonObject.get("duration").getAsInt(),
                        new Pair<Zone, Zone>(
                                zones.get(jsonObject.get("zone").getAsJsonObject().get("a").getAsInt()),
                                zones.get(jsonObject.get("zone").getAsJsonObject().get("b").getAsInt())
                        )
                    )
                );
            }
            return lines;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
