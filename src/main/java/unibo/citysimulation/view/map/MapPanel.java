package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.controller.ClockController;
import unibo.citysimulation.controller.MapController;
import unibo.citysimulation.model.MapModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private MapController mapController;
    private MapModel mapModel;

    /**
     * Constructs a MapPanel object.
     *
     * @param mapModel  The MapModel object containing the map data.
     * @param infoPanel The InfoPanel object to display additional information.
     */
    public MapPanel(MapModel mapModel, InfoPanel infoPanel) {
        super(bgColor); // Calls the constructor of StyledPanel to apply the panel's style
        this.mapModel = mapModel;
        this.mapController = new MapController(mapModel, infoPanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mapController.handleMouseclick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the image on the JPanel
        BufferedImage image = mapModel.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        BufferedImage image = mapModel.getImage();
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
