package unibo.citysimulation.controller;

import unibo.citysimulation.model.MapModel;
//import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

public class MapController {
    private MapModel model;
    private InfoPanel infoPanel;

    public MapController(MapModel model, InfoPanel infoPanel) {
        this.model = model;
        this.infoPanel = infoPanel;

        // Aggiungi il listener del mouse alla mappa
        
    }
    public void handleMouseclick(int x, int y){
        System.out.println("Clicked at: (" + x + ", " + y + ")");
        infoPanel.setCoordinates(x, y);
    }
}