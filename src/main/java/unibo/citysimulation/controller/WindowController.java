package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.WindowView;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controller class responsible for managing the main window.
 */
public class WindowController {
    final private WindowView windowView;
    final private CityModel cityModel;

    public WindowController(final WindowView windowView, final CityModel cityModel) {
        this.windowView = windowView;
        this.cityModel = cityModel;

        this.windowView.addResizeListener(new ResizeListener());

        cityModel.takeFrameSize();

        initializeControllers();

        windowView.updateFrame(cityModel.getFrameWidth(), cityModel.getFrameHeight());
    }

    private void initializeControllers() {
        new MapController(cityModel, windowView.getInfoPanel(), windowView.getMapPanel());
        new ClockController(cityModel.getClockModel(), windowView.getClockPanel());
        new InputController(cityModel, cityModel.getInputModel(), windowView.getInputPanel(), windowView.getClockPanel());
        new GraphicsController(cityModel, windowView.getGraphicsPanel());
    }

    /**
     * Inner class responsible for handling component resize events.
     */
    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(final ComponentEvent e) {
            super.componentResized(e);

            int newWidth = e.getComponent().getWidth();
            int newHeight = e.getComponent().getHeight();

            if (newHeight != cityModel.getFrameHeight()) {
                newWidth = newHeight * 2;
            } else {
                newHeight = newWidth / 2;
            }

            updateCityModel(newWidth, newHeight);
            updateMapPanel();
            windowView.updateFrame(newWidth, newHeight);
        }
    }

    private void updateCityModel(final int newWidth, final int newHeight) {
        cityModel.getMapModel().setMaxCoordinates(windowView.getMapPanel().getWidth(), windowView.getMapPanel().getHeight());
        cityModel.getMapModel().setTransportInfo(cityModel.getTransportLines());
        cityModel.setFrameSize(new Pair<>(newWidth, newHeight));
    }

    private void updateMapPanel() {
        final var mapModel = cityModel.getMapModel();
        final var mapPanel = windowView.getMapPanel();

        mapPanel.setLinesInfo(mapModel.getLinesPointsCoordinates(), mapModel.getTransportNames());

        if (cityModel.isPeoplePresent() && cityModel.isBusinessesPresent()) {
            mapPanel.setEntities(mapModel.getPersonInfos(cityModel.getAllPeople()), 
                                 mapModel.getBusinessInfos(cityModel.getBusinesses()));
        }
    }
}
