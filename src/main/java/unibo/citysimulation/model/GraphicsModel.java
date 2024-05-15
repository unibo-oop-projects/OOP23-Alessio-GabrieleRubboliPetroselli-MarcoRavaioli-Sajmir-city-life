package unibo.citysimulation.model;

import java.awt.Color;
import java.util.List;

import org.jfree.data.xy.XYSeriesCollection;

public class GraphicsModel {
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN);
    private List<XYSeriesCollection> datasets;
    private List<String> names;

    public GraphicsModel() {
        
    }
}
