package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.utilities.Pair;
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

        cityModel.setFrameSize(cityModel.getScreenSize());      //questo comando è inutile se non si vogliono salvare le dimensioni nel cityModel


        new MapController(cityModel, windowView.getInfoPanel(), windowView.getMapPanel());

        new ClockController(cityModel.getClockModel(), windowView.getClockPanel(), windowView.getInputPanel());

        new InputController(cityModel, windowView.getInputPanel(),windowView.getClockPanel(),windowView.getGraphicsPanel(),windowView.getMapPanel());

        new GraphicsController(cityModel, windowView.getGraphicsPanel());
        
        windowView.setFrame(cityModel.getScreenSize());
        windowView.repaint();
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

            if (newWidth != cityModel.getFrameWidth() && newHeight != cityModel.getFrameHeight()) {
                // Trova la dimensione più piccola tra nuova larghezza e altezza
                int minSize = Math.min(newWidth, newHeight * 2);
                // Imposta le nuove dimensioni mantenendo la proporzione 2:1
                newWidth = minSize;
                newHeight = minSize / 2;
            } else {
                if (newHeight != cityModel.getFrameHeight() && newWidth == cityModel.getFrameWidth()) {
                    newWidth = (int) ((double) newHeight * 2);
                } else {
                    newHeight = (int) ((double) newWidth / 2);
                }
            }

            cityModel.setFrameSize(new Pair<Integer,Integer>(newWidth, newHeight));        //questa informazione è inutile, ricontrollare, al massimo la togli anche da cityModel
            windowView.updateFrame(newWidth, newHeight);
            windowView.repaint();
        }
    }


}
