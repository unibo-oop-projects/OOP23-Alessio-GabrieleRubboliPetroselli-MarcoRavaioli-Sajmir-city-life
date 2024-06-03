package unibo.citysimulation.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import unibo.citysimulation.model.CityModelImpl;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.map.impl.MapModelImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.WindowView;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidepanels.InfoPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public final class MapController implements MouseListener, ClockObserver {
    private final InfoPanel infoPanel;
    private final MapPanel mapPanel;
    private final MapModelImpl mapModel;
    private final CityModelImpl cityModel;

    /**
     * Constructs a MapController object.
     *
     * @param cityModel  The CityModel object containing the city data.
     * @param windowView The WindowView object containing the info and map panels.
     */
    public MapController(final CityModelImpl cityModel, final InfoPanel infoPanel, final MapPanel mapPanel) {
        this.cityModel = cityModel;
        this.infoPanel = infoPanel;
        this.mapPanel = mapPanel;
        this.mapModel = cityModel.getMapModel();

        mapPanel.addMouseListener(this);
    }

    /**
     * Initializes the map controller.
     */
    public void initialize() {
        cityModel.getClockModel().addObserver(this);
        mapModel.setTransportInfo(cityModel.getTransportLines());
        mapModel.setTransportCongestion(cityModel.getTransportLines());

        mapPanel.setImage(mapModel.getImage());

        mapPanel.setLinesInfo(mapModel.getLinesPointsCoordinates(), mapModel.getTransportNames());
        mapPanel.setLinesColor(mapModel.getColorList());
    }

    private void handleMouseClick(final MouseEvent e) {
        final int x = (int) ((double) e.getX() / mapPanel.getWidth() * 1000);
        final int y = (int) ((double) e.getY() / mapPanel.getHeight() * 1000);

        updateZoneInfo(x, y);
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
                () -> infoPanel.updateNumberOfPeople(0));
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

    /**
     * When the mouse clicks on the map in the MapPanel this method is called and
     * updates the map model thanks to method handleMouseClick. This method updates
     * also the InfoPanel.
     * 
     * @param e
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        handleMouseClick(e);
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
