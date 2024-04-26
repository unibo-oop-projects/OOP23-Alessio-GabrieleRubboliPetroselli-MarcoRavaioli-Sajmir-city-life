package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.controller.MapController;
import unibo.citysimulation.model.MapModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MapPanel extends StyledPanel {
    private MapController mapController;
    private MapModel mapModel;

    public MapPanel(MapModel mapModel, InfoPanel infoPanel) {
        super(bgColor); // Chiama il costruttore di StyledPanel per applicare lo stile al pannello
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
        // Disegna l'immagine sulla JPanel
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