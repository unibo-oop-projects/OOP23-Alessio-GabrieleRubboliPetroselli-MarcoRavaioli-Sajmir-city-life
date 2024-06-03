package unibo.citysimulation.view.sidepanels.graphics;

import java.awt.event.ActionListener;
import java.util.List;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.Color;

/**
 * Interface for displaying Charts in WindowView.
 */
public interface GraphicsPanel {
    /**
     * Adds an action listener to the legend button.
     *
     * @param listener The action listener to be added to the legend button.
     */
    void addLegendButtonActionListener(ActionListener listener);

    /**
     * Creates and displays a series of XY charts based on the provided names,
     * datasets, and colors.
     * Each chart is added to a vertically arranged grid layout within a panel.
     *
     * @param names    the list of names for each chart
     * @param datasets the list of datasets for each chart, where each dataset
     *                 corresponds to a chart
     * @param colors   the list of colors for rendering the datasets in the charts
     */
    void createGraphics(List<String> names, List<XYSeriesCollection> datasets, 
            List<Color> colors);

    /**
     * Sets the preferred size of the view Panel that displays charts.
     * 
     * @param width the new width of the panel
     * @param height the new height of the panel
     */
    void setPreferredSize(int width, int height);

    void updateDatasets(List<XYSeriesCollection> datasets);
}
