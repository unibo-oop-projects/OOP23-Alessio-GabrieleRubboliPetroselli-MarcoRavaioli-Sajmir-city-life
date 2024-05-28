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
    private final WindowView windowView;
    private final CityModel cityModel;

    /**
     * Constructor for initial window and initialize all the feature in the window.
     * 
     * @param windowView
     * @param cityModel
     */
    public WindowController(final WindowView windowView, final CityModel cityModel) {
        this.windowView = windowView;
        this.cityModel = cityModel;

        windowView.addResizeListener(new ResizeListener());
        initializeControllers();
        windowView.updateFrame(cityModel.getFrameWidth(), cityModel.getFrameHeight());
    }

    private void initializeControllers() {
        new MapController(cityModel, windowView.getInfoPanel(), windowView.getMapPanel());
        new ClockController(cityModel.getClockModel(), windowView.getClockPanel());
        new InputController(cityModel, cityModel.getInputModel(), windowView.getInputPanel(),
                windowView.getClockPanel());
        new GraphicsController(cityModel, windowView.getGraphicsPanel());
    }

    private void updateCityModel(final int newWidth, final int newHeight) {
        cityModel.getMapModel().setMaxCoordinates(windowView.getMapPanel().getWidth(),
                windowView.getMapPanel().getHeight());
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

    /**
     * Inner class responsible for handling component resize events.
     */
    private final class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(final ComponentEvent e) {
            int newWidth = e.getComponent().getWidth();
            int newHeight = e.getComponent().getHeight();
            final int oldWidth = cityModel.getFrameWidth();
            final int oldHeight = cityModel.getFrameHeight();

            final boolean widthChanged = newWidth != oldWidth;
            final boolean heightChanged = newHeight != oldHeight;

            // Maintain a 2:1 aspect ratio between width and height
            if (widthChanged && heightChanged) {
                // If both dimensions are changed, calculate the change ratios
                // Adjust the dimension based on the larger change ratio
                if ((double) newWidth / oldWidth > (double) newHeight / oldHeight) {
                    newHeight = newWidth / 2;
                } else {
                    newWidth = newHeight * 2;
                }
            } else if (heightChanged) {
                // If only the height has changed
                newWidth = newHeight * 2;
            } else if (widthChanged) {
                // If only the width has changed
                newHeight = newWidth / 2;
            }

            updateCityModel(newWidth, newHeight);
            updateMapPanel();
            windowView.updateFrame(newWidth, newHeight);
        }
    }
}
