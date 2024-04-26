package unibo.citysimulation.controller;

import java.awt.event.MouseEvent;

import unibo.citysimulation.model.MapModel;
//import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

public class MapController {
    private MapModel model;
    private InfoPanel infoPanel;

    public MapController(MapModel model, InfoPanel infoPanel) {
        this.model = model;
        this.infoPanel = infoPanel;

        // Aggiungi il listener del mouse alla mappa
        
    }
    public void handleMouseclick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        //System.out.println("Clicked at: (" + x + ", " + y + ")");

        model.setLastClickedCoordinates(x, y);

        infoPanel.updatePositionInfo(x, y);
    }
}