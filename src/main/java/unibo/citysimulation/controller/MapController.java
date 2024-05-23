package unibo.citysimulation.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.MapModelImpl;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidepanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public class MapController implements ClockObserver{
    private final InfoPanel infoPanel;
    private final MapPanel mapPanel;
    private final MapModelImpl mapModel;
    private final CityModel cityModel;

    /**
     * Constructs a MapController object.
     *
     * @param cityModel The CityModel object containing the city data.
     * @param infoPanel The InfoPanel object to display additional information.
     * @param mapPanel The MapPanel object to display the map.
     */
    public MapController(final CityModel cityModel, final InfoPanel infoPanel, final MapPanel mapPanel) {
        this.cityModel = cityModel;
        this.infoPanel = infoPanel;
        this.mapPanel = mapPanel;
        this.mapModel = cityModel.getMapModel();

        initialize();
        

    }

    private void initialize(){
        cityModel.getClockModel().addObserver(this);

        mapModel.setTransportInfo(cityModel.getTransportLines());
        mapModel.setTransportCongestion(cityModel.getTransportLines());


        mapPanel.setImage(mapModel.getImage());

        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                handleMouseclick(e);
            }
        });

        

        mapPanel.setLinesInfo(mapModel.getLinesPointsCoordinates(), mapModel.getTransportNames());
        mapPanel.setLinesColor(mapModel.getColorList());
        
    }

    /**
     * Handles mouse click events.
     *
     * @param e The MouseEvent object representing the mouse event.
     */
    public void handleMouseclick(final MouseEvent e) {
        int x = mapModel.normalizeCoordinate(e.getX(), mapModel.getMaxX());
        int y = mapModel.normalizeCoordinate(e.getY(), mapModel.getMaxY());

        final List<Zone> zones = cityModel.getZones();
        String zoneName = ""; // Declare zoneName here
        for (final Zone zone : zones) {
            if (zone.boundary().isInside(x, y)) {
                zoneName = zone.name();
                break;
            }
        }

        mapModel.setMaxCoordinates((int) mapPanel.getSize().getWidth(), (int) mapPanel.getSize().getHeight());

        mapModel.setLastClickedCoordinates(x, y);

        infoPanel.updatePositionInfo(mapModel.getNormX(), mapModel.getNormY());
        infoPanel.updateZoneName(zoneName);

        infoPanel.updateNumberOfPeople(cityModel.getPeopleInZone(zoneName));

        infoPanel.updateNumberOfBusiness(cityModel.getBusinessesInZone(zoneName));

    }

    public BufferedImage getImage() {
        return mapModel.getImage();
    }

    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {

        mapModel.setTransportCongestion(cityModel.getTransportLines());

        mapPanel.setLinesColor(mapModel.getColorList());
        mapPanel.setEntities(mapModel.getPersonInfos(cityModel.getAllPeople()), mapModel.getBusinessInfos(cityModel.getBusinesses()));
    }
}
