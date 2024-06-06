package unibo.citysimulation.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.Objects;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.InfoModelImpl;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.map.impl.MapModelImpl;
import unibo.citysimulation.view.WindowView;
import unibo.citysimulation.view.map.MapPanel;

/**
 * Controller class responsible for handling mouse events on the map.
 */
public final class MapController implements MouseListener, ClockObserver {
    private final MapPanel mapPanel;
    private final MapModelImpl mapModel;
    private final CityModel cityModel;
    private final InfoModelImpl infoModel;

    /**
     * Constructs a MapController object.
     *
     * @param cityModel  The CityModel interface containing the method to access and modify city data.
     * @param windowView The WindowView interface containing the method to access and modify info and map panels.
     */
    public MapController(final CityModel cityModel, final WindowView windowView) {
        this.cityModel = Objects.requireNonNull(cityModel, "CityModel cannot be null");
        this.mapPanel = windowView.getMapPanel();
        this.mapModel = cityModel.getMapModel();
        this.infoModel = new InfoModelImpl(cityModel, windowView.getInfoPanel());

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

        infoModel.updateZoneInfo(x, y);
        mapModel.setMaxCoordinates((int) cityModel.getFrameWidth() / 2, (int) cityModel.getFrameHeight());
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
