package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
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
            Color.PINK, Color.CYAN);        // da mettere nel model
    private final JButton legendButton;
    private List<XYPlot> plots;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        
        this.legendButton = new JButton("?");
        this.legendButton.setPreferredSize(new Dimension(70, 40)); // Set the preferred size of the button to make it small

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Create a new panel for the button
        bottomPanel.setBackground(bgColor);
        bottomPanel.add(this.legendButton); // Add the button to the panel
    
        // Add the bottom panel to this panel
        this.setLayout(new BorderLayout()); // Set layout of GraphicsPanel
        this.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the SOUTH position
    }

    public JButton getLegendButton(){
        return this.legendButton;
    }

    public void createGraphics(List<String> names, List<XYSeriesCollection> datasets) {
        plots = createCharts(names, datasets).stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(createRenderer(plot.getSeriesCount())))
                .collect(Collectors.toList());
    
        JPanel chartsPanel = new JPanel();
        chartsPanel.setBackground(bgColor);
        chartsPanel.setLayout(new GridLayout(plots.size(), 1)); // 1 colonna, tante righe quante sono i grafici
    
        synchronized (this) {
            plots.forEach(plot -> chartsPanel.add(new ChartPanel(plot.getChart())));
        }
    
    
        this.add(chartsPanel, BorderLayout.CENTER);
    }
    

    public List<JFreeChart> createCharts(List<String> names, List<XYSeriesCollection> datasets) {
        List<JFreeChart> charts = new ArrayList<JFreeChart>();

        for (int i = 0; i < names.size(); i++) {
            charts.add(createChart(names.get(i), datasets.get(i)));
        }
        return charts;
    }


    // Method to create a chart
    private JFreeChart createChart(String title, XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        XYPlot plot = chart.getXYPlot();
        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();

        domainAxis.setTickLabelsVisible(false);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 105);

        return chart;
    }

    private XYLineAndShapeRenderer createRenderer(int num) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

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