package unibo.citysimulation.controller;

import java.awt.event.MouseEvent;

import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public class MapController {
    private InfoPanel infoPanel;
    private MapModel model;

    /**
     * Constructs a MapController object.
     *
     * @param model     The MapModel object containing the map data.
     * @param infoPanel The InfoPanel object to display additional information.
     */
    public MapController(MapModel model, InfoPanel infoPanel) {
        this.model = model;
        this.infoPanel = infoPanel;
    }

    /**
     * Handles mouse click events.
     *
     * @param e The MouseEvent object representing the mouse event.
     */
    public void handleMouseclick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        model.setLastClickedCoordinates(x, y);

        infoPanel.updatePositionInfo(model.getNormX(), model.getNormY());
    }
}
