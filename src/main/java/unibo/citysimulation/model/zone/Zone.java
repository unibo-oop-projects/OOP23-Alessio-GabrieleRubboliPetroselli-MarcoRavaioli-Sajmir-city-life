package unibo.citysimulation.model.zone;
import java.util.List;

public class Zone {
    private String name;
    private List<Pair> boundary;

    public Zone(String name, List<Pair> boundary) {
        this.name = name;
        this.boundary = boundary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pair> getBoundary() {
        return boundary;
    }

    public void setBoundary(List<Pair> boundary) {
        this.boundary = boundary;
    }
}
