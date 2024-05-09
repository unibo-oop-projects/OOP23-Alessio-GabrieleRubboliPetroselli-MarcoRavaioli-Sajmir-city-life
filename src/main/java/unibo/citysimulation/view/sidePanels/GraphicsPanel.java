package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private CategoryAxis domainAxis;
    private NumberAxis rangeAxis;
    private DefaultCategoryDataset line0dataset; // Make dataset a class member
    private DefaultCategoryDataset line1dataset;
    private DefaultCategoryDataset businessDataset;

    private int columnCount = 0;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        // Initialize datasets
        line0dataset = new DefaultCategoryDataset();
        line1dataset = new DefaultCategoryDataset();
        businessDataset = new DefaultCategoryDataset();

        // Initialize charts
        JFreeChart peopleChart = createChart("People Selected", "", "", line0dataset);
        JFreeChart transportChart = createChart("Transport", "", "", null);
        JFreeChart businessChart = createChart("Business", "", "", businessDataset);

        peopleChart.getCategoryPlot().setDataset(1, line1dataset);
        
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) peopleChart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);


        // Create chart panels
        ChartPanel peopleChartPanel = new ChartPanel(peopleChart);
        ChartPanel transportChartPanel = new ChartPanel(transportChart);
        ChartPanel businessChartPanel = new ChartPanel(businessChart);

        // Add chart panels to the panel
        add(peopleChartPanel);
        add(transportChartPanel);
        add(businessChartPanel);

        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(3, 1));
    }

    // Method to create a chart
    private JFreeChart createChart(String title, String domainLabel, String rangeLabel,
            DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                title,
                domainLabel,
                rangeLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        domainAxis = chart.getCategoryPlot().getDomainAxis();
        rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();

        domainAxis.setTickLabelsVisible(false);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 100);

        return chart;
    }

    public void updateDataset(int cong0, int cong1, String time) {
        
        line0dataset.addValue(cong0, "Cong0", time);
        line1dataset.addValue(cong1, "Cong1", time);

        columnCount++;

        if (columnCount > 200) {
            
            int columnsToRemove = columnCount - 200;


            System.out.println(columnsToRemove + " columns to remove");
            // Rimuovi le colonne in eccesso dal dataset
            for (int i = 0; i < columnsToRemove; i++) {
            Comparable<?> columnKey = line0dataset.getColumnKey(i);
            line0dataset.removeColumn(columnKey);
            line1dataset.removeColumn(columnKey);
            }

            columnCount = 200;
        }

    }
}