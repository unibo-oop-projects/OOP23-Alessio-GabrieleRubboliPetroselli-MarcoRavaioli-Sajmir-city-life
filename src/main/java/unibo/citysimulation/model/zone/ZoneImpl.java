package unibo.citysimulation.model.zone;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import unibo.citysimulation.model.WindowModel;

public class ZoneImpl implements Zone{

    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;

    public ZoneImpl(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.bottomRightX = bottomRightX;
        this.bottomRightY = bottomRightY;
    }

    @Override
    public List<Zone> divideMapIntoZones(Map map, int numberOfZones) {
        List<Zone> zones = new ArrayList<>();

    // Calculate the width and height of each zone.
    int zoneWidth = WindowModel.getWidth() / (int)Math.sqrt(numberOfZones);
    int zoneHeight = WindowModel.getHeight() / (int)Math.sqrt(numberOfZones);

    // Iterate over the map and create a new zone for each grid cell.
    for (int i = 0; i < Math.sqrt(numberOfZones); i++) {
        for (int j = 0; j < Math.sqrt(numberOfZones); j++) {
            // Calculate the top-left and bottom-right coordinates of the zone.
            int topLeftX = i * zoneWidth;
            int topLeftY = j * zoneHeight;
            int bottomRightX = topLeftX + zoneWidth;
            int bottomRightY = topLeftY + zoneHeight;

            // Create a new zone and add it to the list.
            Zone zone = new ZoneImpl(topLeftX, topLeftY, bottomRightX, bottomRightY);
            zones.add(zone);
        }
    }

    return zones;
    }
    
}
