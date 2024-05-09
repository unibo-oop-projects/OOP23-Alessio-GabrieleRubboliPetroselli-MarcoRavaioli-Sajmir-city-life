package unibo.citysimulation.view.sidePanels;
import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private DefaultCategoryDataset peopleDataset; // Make dataset a class member
    private CategoryAxis domainAxis;
    private NumberAxis rangeAxis;
    private DefaultCategoryDataset transportDataset;
    private DefaultCategoryDataset businessDataset;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        // Initialize datasets
        peopleDataset = new DefaultCategoryDataset();
        transportDataset = new DefaultCategoryDataset();
        businessDataset = new DefaultCategoryDataset();

        // Initialize charts
        JFreeChart peopleChart = createChart("People Selected","","", peopleDataset);
        JFreeChart transportChart = createChart("Transport", "", "", transportDataset);
        JFreeChart businessChart = createChart("Business", "", "", businessDataset);

        // Create chart panels
        ChartPanel peopleChartPanel = new ChartPanel(peopleChart);
        ChartPanel transportChartPanel = new ChartPanel(transportChart);
        ChartPanel businessChartPanel = new ChartPanel(businessChart);

        // Add chart panels to the panel
        add(peopleChartPanel);
        add(transportChartPanel);
        add(businessChartPanel);

        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(3,1));
        }

        // Method to create a chart
        private JFreeChart createChart(String title, String domainLabel, String rangeLabel, DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                domainLabel,
                rangeLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        domainAxis = chart.getCategoryPlot().getDomainAxis();
        rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();

        domainAxis.setLowerMargin(0.1);
        domainAxis.setUpperMargin(0.1);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 200);

        return chart;
    }

    public void updateDataset(int number) {
        peopleDataset.addValue(number, "People", "Selected");
    }
}