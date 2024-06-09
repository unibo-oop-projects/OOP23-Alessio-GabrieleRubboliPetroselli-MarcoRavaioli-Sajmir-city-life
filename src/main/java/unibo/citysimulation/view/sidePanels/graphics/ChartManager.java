package unibo.citysimulation.view.sidepanels.graphics;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.util.List;

/**
 * Interface for creating Charts with JFreeChart.
 */
public interface ChartManager {
        /**
     * Creates an XYLineAndShapeRenderer with the specified number of series and colors.
     *
     * @param num the number of series
     * @param colors the list of colors for the series
     * @return an XYLineAndShapeRenderer object
     */
    XYLineAndShapeRenderer createRenderer(int num, List<Color> colors);

    /**
     * Creates a list of charts with the specified names and datasets.
     *
     * @param names the list of names for the charts
     * @param datasets the list of datasets for the charts
     * @return a list of JFreeChart objects
     */
    List<JFreeChart> createCharts(List<String> names, List<XYSeriesCollection> datasets);
}
