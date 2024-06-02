package unibo.citysimulation.view.sidepanels.graphics;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

/**
 * Panel for displaying graphics.
 */
public final class GraphicsPanelImpl extends StyledPanel implements GraphicsPanel {
    private static final long serialVersionUID = 1L;
    private final JButton legendButton;
    private final ChartManager chartManager = new ChartManager();

    private static final Pair<Integer, Integer> BUTTON_DIMENSIONS = new Pair<>(70, 40);

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanelImpl(final Color bgColor) {
        super(bgColor);

        this.legendButton = new JButton("?");
        this.legendButton.setPreferredSize(new Dimension(BUTTON_DIMENSIONS.getFirst(), BUTTON_DIMENSIONS.getSecond()));

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Create a new panel for the button
        bottomPanel.setBackground(bgColor);
        bottomPanel.add(this.legendButton); // Add the button to the panel

        // Add the bottom panel to this panel
        this.setLayout(new BorderLayout()); // Set layout of GraphicsPanel
        this.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the SOUTH position
    }

    @Override
    public void addLegendButtonActionListener(final ActionListener listener) {
        legendButton.addActionListener(listener);
    }

    @Override
    public void createGraphics(final List<String> names, final List<XYSeriesCollection> datasets,
            final List<Color> colors) {
        final List<XYPlot> plots = chartManager.createCharts(names, datasets).stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(chartManager.createRenderer(plot.getSeriesCount(), colors)))
                .collect(Collectors.toList());

        final JPanel chartsPanel = new JPanel();
        chartsPanel.setBackground(super.getBgColor());
        chartsPanel.setLayout(new GridLayout(plots.size(), 1));

        plots.forEach(plot -> chartsPanel.add(new ChartPanel(plot.getChart())));

        this.add(chartsPanel, BorderLayout.CENTER);
    }

    @Override
    public void setPreferredSize(final int width, final int height) {
        this.setPreferredSize(new Dimension(width, height));
    }
}
