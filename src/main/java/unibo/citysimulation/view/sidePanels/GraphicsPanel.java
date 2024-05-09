package unibo.citysimulation.view.sidePanels;
import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private DefaultCategoryDataset dataset; // Make dataset a class member

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        dataset = new DefaultCategoryDataset(); // Initialize dataset here
        dataset.addValue(100, "People", "Selected"); // Add the number of selected people

        // Create a chart
        JFreeChart chart = ChartFactory.createBarChart(
            "People Selected", // chart title
            "Category", // domain axis label
            "Number", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL,
            false, // include legend
            false, // tooltips
            false // urls
        );

        // Create a panel and add the chart to it
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public void updateDataset(int number) {
        dataset.addValue(number, "People", "Selected");
    }
}