package unibo.citysimulation.model.zone;
import java.util.List;
import java.util.Map;

public interface ZoneCreation {
    public List<Zone> divideMapIntoZones(Map map, int numberOfZones);
}