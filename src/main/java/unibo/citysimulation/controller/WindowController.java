package unibo.citysimulation.controller;

import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.view.WindowView;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controller class responsible for managing the main window.
 */
public class WindowController {
    private WindowModel windowModel;
    private WindowView windowView;

    /**
     * Constructs a WindowController with the specified models and view.
     *
     * @param windowModel The model representing the main window.
     * @param windowView  The view representing the main window.
     * @param mapModel    The model representing the map.
     */
    public WindowController(WindowModel windowModel, WindowView windowView) {
        this.windowModel = windowModel;
        this.windowView = windowView;
        this.windowView.addResizeListener(new ResizeListener());
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

            // Update window model and view
            windowModel.setWidth(newWidth);
            windowModel.setHeight(newHeight);
            windowView.setPreferredSize(new Dimension(newWidth, newHeight));
            windowView.pack();
            windowView.updatePanelSize();
        }        
    }    
}
