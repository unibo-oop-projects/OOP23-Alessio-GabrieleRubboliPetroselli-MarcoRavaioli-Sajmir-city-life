package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.WindowView;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controller class responsible for managing the main window.
 */
public class WindowController {
    private WindowView windowView;

    /**
     * Constructs a WindowController with the specified models and view.
     *
     * @param windowModel The model representing the main window.
     * @param windowView  The view representing the main window.
     * @param mapModel    The model representing the map.
     */
    public WindowController(WindowView windowView, CityModel cityModel) {
        this.windowView = windowView;

        this.windowView.addResizeListener(new ResizeListener());

        new MapController(cityModel, windowView.getInfoPanel(), windowView.getMapPanel());

        new ClockController(cityModel.getClockModel(), windowView.getClockPanel(), windowView.getInputPanel());

        new InputController(cityModel, windowView.getInputPanel(),windowView.getClockPanel(),windowView.getGraphicsPanel(),windowView.getMapPanel());

        new GraphicsController(cityModel, windowView.getGraphicsPanel());
    }

    /**
     * Inner class responsible for handling component resize events.
     */
    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            int newWidth = e.getComponent().getBounds().width;
            int newHeight = e.getComponent().getBounds().height;

            if (newWidth != windowView.getWidth() && newHeight != windowView.getHeight()) {
                // Trova la dimensione più piccola tra nuova larghezza e altezza
                int minSize = Math.min(newWidth, newHeight * 2);
                // Imposta le nuove dimensioni mantenendo la proporzione 2:1
                newWidth = minSize;
                newHeight = minSize / 2;
            } else {
                if (newHeight != windowView.getHeight() && newWidth == windowView.getWidth()) {
                    newWidth = (int) ((double) newHeight * 2);
                } else {
                    newHeight = (int) ((double) newWidth / 2);
                }
            }

            windowView.setWidth(newWidth);
            windowView.setHeight(newHeight);
            windowView.updatePanelSize();
            windowView.repaint();
        }
    }
}
