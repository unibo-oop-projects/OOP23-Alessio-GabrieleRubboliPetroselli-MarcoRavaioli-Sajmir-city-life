package unibo.citysimulation.view.sidepanels;

import java.awt.event.ActionListener;
import java.util.List;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.Color;

public interface GraphicsPanel {
    /**
     * Adds an action listener to the legend button.
     *
     * @param listener The action listener to be added to the legend button.
     */
    void addLegendButtonActionListener(final ActionListener listener);

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
    void createGraphics(final List<String> names, final List<XYSeriesCollection> datasets, 
            final List<Color> colors);

    void setPreferredSize(final int width, final int height);
}
