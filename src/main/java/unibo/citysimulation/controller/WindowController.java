package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.WindowView;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controller class responsible for managing the main window.
 */
public class WindowController {
    private WindowView windowView;
    private CityModel cityModel;

    /**
     * Constructs a WindowController with the specified models and view.
     *
     * @param windowModel The model representing the main window.
     * @param windowView  The view representing the main window.
     * @param mapModel    The model representing the map.
     */
    public WindowController(WindowView windowView, CityModel cityModel) {
        this.windowView = windowView;
        this.cityModel = cityModel;
        this.windowView.addResizeListener(new ResizeListener());

        new MapController(cityModel.getMapModel(), windowView.getInfoPanel(), windowView.getMapPanel());
        new ClockController(cityModel.getClockModel(), cityModel, windowView.getClockPanel(), windowView.getInputPanel());
        new InputController(cityModel, windowView.getInputPanel(),windowView.getClockPanel());
    }

    /**
     * Inner class responsible for handling component resize events.
     */
    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            int newWidth = e.getComponent().getWidth();
            int newHeight = e.getComponent().getHeight();

            // Adjust the window size based on aspect ratio
            if (newHeight * 2 > newWidth) {
                newHeight = newWidth / 2;
            } else {
                newWidth = newHeight * 2;
            }

            windowView.setPreferredSize(new Dimension(newWidth, newHeight));
            windowView.pack();
            windowView.updatePanelSize();
        }        
    }    
}
