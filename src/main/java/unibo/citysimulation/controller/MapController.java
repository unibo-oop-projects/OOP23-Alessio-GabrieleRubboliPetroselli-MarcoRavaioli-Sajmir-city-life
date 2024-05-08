package unibo.citysimulation.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public class MapController {
    private InfoPanel infoPanel;
    private MapPanel mapPanel;
    private MapModel mapModel;

    /**
     * Constructs a MapController object.
     *
     * @param model     The MapModel object containing the map data.
     * @param infoPanel The InfoPanel object to display additional information.
     */
    public MapController(MapModel mapModel, InfoPanel infoPanel, MapPanel mapPanel) {
        this.mapModel = mapModel;
        this.infoPanel = infoPanel;
        this.mapPanel = mapPanel;

        mapPanel.setImage(mapModel.getImage());
        
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseclick(e);
            }
        });
    }
    /**
     * Handles mouse click events.
     *
     * @param e The MouseEvent object representing the mouse event.
     */
    public void handleMouseclick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        System.out.println("pressed coordinates: " + x + " " + y);

        mapModel.setLastClickedCoordinates(x, y);

        infoPanel.updatePositionInfo(mapModel.getNormX(), mapModel.getNormY());
    }
}
