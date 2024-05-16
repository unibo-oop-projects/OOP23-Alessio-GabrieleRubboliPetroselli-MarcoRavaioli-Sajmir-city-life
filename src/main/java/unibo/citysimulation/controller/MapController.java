package unibo.citysimulation.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public class MapController implements ClockObserver{
    private InfoPanel infoPanel;
    private MapPanel mapPanel;
    private MapModel mapModel;
    private CityModel cityModel;

    /**
     * Constructs a MapController object.
     *
     * @param model     The MapModel object containing the map data.
     * @param infoPanel The InfoPanel object to display additional information.
     */
    public MapController(CityModel cityModel, InfoPanel infoPanel, MapPanel mapPanel) {
        this.cityModel = cityModel;
        this.infoPanel = infoPanel;
        this.mapPanel = mapPanel;
        this.mapModel = cityModel.getMapModel();
        //mapPanel.setZones(cityModel.getZones());

        mapPanel.setImage(mapModel.getImage());

        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseclick(e);
            }
        });

        mapModel.setTransportInfos(cityModel.getTransportLines());
        mapModel.setTransportNames(cityModel.getTransportLines());
        mapPanel.setLinesPoints(mapModel.getLinesPointsCoordinates());
        mapPanel.setCongestionsList(mapModel.getColorList());

        cityModel.getClockModel().addObserver(this);
        mapPanel.SetTransportNames(mapModel.getTransportNames());
    }

    /**
     * Handles mouse click events.
     *
     * @param e The MouseEvent object representing the mouse event.
     */
    public void handleMouseclick(MouseEvent e) {
        int x = mapModel.normalizeCoordinate(e.getX(), mapModel.getMaxX());
        int y = mapModel.normalizeCoordinate(e.getY(), mapModel.getMaxY());

        System.out.println("pressed coordinates: " + x + " " + y);  //queste rimangono uguali
        List<Zone> zones = cityModel.getZones();
        String zoneName = ""; // Declare zoneName here
        for (Zone zone : zones) {
            if (zone.boundary().isInside(x, y)) {
                zoneName = zone.name(); // Assign value here
                System.out.println("Clicked inside zone: " + zoneName);
                break;
            }
        }

        mapModel.setMaxCoordinates((int) mapPanel.getSize().getWidth(), (int) mapPanel.getSize().getHeight());

        mapModel.setLastClickedCoordinates(x, y);

        infoPanel.updatePositionInfo(mapModel.getNormX(), mapModel.getNormY());

        infoPanel.updateZoneName(zoneName);

    }

    public BufferedImage getImage() {
        return mapModel.getImage();
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {

        mapPanel.setPeopleMap(mapModel.getPersonInfos(cityModel.getAllPeople()));
        
        mapModel.setTransportInfos(cityModel.getTransportLines());
        mapPanel.setLinesPoints(mapModel.getLinesPointsCoordinates());
        mapPanel.setCongestionsList(mapModel.getColorList());

        //mapPanel.setBusinessPoints(mapModel.getBusinessInfos(cityModel.getBusinesses()));
        

        mapPanel.repaint();
    }
}
