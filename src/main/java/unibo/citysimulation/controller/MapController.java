package unibo.citysimulation.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.Optional;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.map.impl.MapModelImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidepanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public class MapController implements ClockObserver {
    private final InfoPanel infoPanel;
    private final MapPanel mapPanel;
    private final MapModelImpl mapModel;
    private final CityModel cityModel;

    /**
     * Constructs a MapController object.
     *
     * @param cityModel The CityModel object containing the city data.
     * @param infoPanel The InfoPanel object to display additional information.
     * @param mapPanel  The MapPanel object to display the map.
     */
    public MapController(final CityModel cityModel, final InfoPanel infoPanel, final MapPanel mapPanel) {
        this.cityModel = cityModel;
        this.infoPanel = infoPanel;
        this.mapPanel = mapPanel;
        this.mapModel = cityModel.getMapModel();

        initialize();
    }

    private void initialize() {
        cityModel.getClockModel().addObserver(this);

        mapModel.setTransportInfo(cityModel.getTransportLines());
        mapModel.setTransportCongestion(cityModel.getTransportLines());

        mapPanel.setImage(mapModel.getImage());

        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                handleMouseClick(e);
            }
        });

        mapPanel.setLinesInfo(mapModel.getLinesPointsCoordinates(), mapModel.getTransportNames());
        mapPanel.setLinesColor(mapModel.getColorList());
    }

    private void handleMouseClick(final MouseEvent e) {
        final int x = (int) ((double) e.getX() / mapPanel.getWidth() * 1000);
        final int y = (int) ((double) e.getY() / mapPanel.getHeight() * 1000);

        updateZoneInfo(x, y);

        System.out.println("mapController.handleMouseClick.cityModel.getFrameWidth: " + cityModel.getFrameWidth() / 2);
        mapModel.setMaxCoordinates((int) cityModel.getFrameWidth() / 2, (int) cityModel.getFrameHeight());
    }

    private void updateZoneInfo(final int x, final int y) {
        final Optional<Zone> selectedZone = cityModel.getZoneByPosition(new Pair<>(x, y));
        selectedZone.ifPresentOrElse(zone -> updateInfoPanelWithZone(zone, x, y), () -> clearInfoPanel(x, y));
    }

    private void updateInfoPanelWithZone(final Zone zone, final int x, final int y) {
        infoPanel.updatePositionInfo(x, y);
        infoPanel.updateZoneName(zone.name());
        cityModel.getPeopleInZone(zone.name()).ifPresentOrElse(
            infoPanel::updateNumberOfPeople,
            () -> infoPanel.updateNumberOfPeople(0)
        );
        infoPanel.updateNumberOfBusiness(cityModel.getBusinessesInZone(zone.name()));
        infoPanel.updateAvaragePay(cityModel.avaragePayZone(zone));
        infoPanel.updateNumberOfDirectLines(cityModel.getNumberOfDirectLinesFromZone(zone));
    }

    private void clearInfoPanel(final int x, final int y) {
        infoPanel.updatePositionInfo(x, y);
        infoPanel.updateZoneName("");
        infoPanel.updateNumberOfPeople(0);
        infoPanel.updateNumberOfBusiness(0);
        infoPanel.updateAvaragePay(0);
        infoPanel.updateNumberOfDirectLines(0);
    }

    /**
     * Updates the map model when the time is updated.
     *
     * @param currentTime the current time
     * @param currentDay  the current day
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {

        mapModel.setTransportCongestion(cityModel.getTransportLines());
        mapPanel.setLinesColor(mapModel.getColorList());
        mapPanel.setEntities(mapModel.getPersonInfos(cityModel.getAllPeople()),
                mapModel.getBusinessInfos(cityModel.getBusinesses()));
    }
}
