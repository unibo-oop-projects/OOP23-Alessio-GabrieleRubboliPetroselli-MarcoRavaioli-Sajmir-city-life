package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.*;
import javax.swing.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.CYAN);
    private final JButton legendButton;
    
    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(final Color bgColor) {
        super(bgColor);

        
        this.legendButton = new JButton("?");
        this.legendButton.setPreferredSize(new Dimension(70, 40)); // Set the preferred size of the button to make it small

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Create a new panel for the button
        bottomPanel.setBackground(bgColor);
        bottomPanel.add(this.legendButton); // Add the button to the panel
    
        // Add the bottom panel to this panel
        this.setLayout(new BorderLayout()); // Set layout of GraphicsPanel
        this.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the SOUTH position
    }

    public JButton getLegendButton(){
        return this.legendButton;
    }

    public void createGraphics(final List<String> names, final List<XYSeriesCollection> datasets) {
        final List<XYPlot> plots = createCharts(names, datasets).stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(createRenderer(plot.getSeriesCount())))
                .collect(Collectors.toList());
    
        final JPanel chartsPanel = new JPanel();
        chartsPanel.setBackground(super.getBgColor());
        chartsPanel.setLayout(new GridLayout(plots.size(), 1)); // 1 colonna, tante righe quante sono i grafici
    
        
        plots.forEach(plot -> chartsPanel.add(new ChartPanel(plot.getChart())));
    
        this.add(chartsPanel, BorderLayout.CENTER);
    }
    

    public List<JFreeChart> createCharts(final List<String> names, final List<XYSeriesCollection> datasets) {
        final List<JFreeChart> charts = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            charts.add(createChart(names.get(i), datasets.get(i)));
        }
        return charts;
    }

    // Method to create a chart
    private JFreeChart createChart(final String title, final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        final XYPlot plot = chart.getXYPlot();
        final ValueAxis domainAxis = plot.getDomainAxis();
        final ValueAxis rangeAxis = plot.getRangeAxis();

        domainAxis.setTickLabelsVisible(false);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 105);

        return chart;
    }

    private XYLineAndShapeRenderer createRenderer(final int num) {
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < num; i++) {
            renderer.setSeriesPaint(i, colors.get(i));
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
        return renderer;
    }

    public List<Color> getColors(){
        return colors;
    }
}