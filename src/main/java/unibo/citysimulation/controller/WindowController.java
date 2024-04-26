package unibo.citysimulation.controller;

import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.view.WindowView;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowController {
    private WindowModel windowModel;
    private WindowView windowView;
    private MapModel mapModel;

    public WindowController(WindowModel model, WindowView view, MapModel mapModel) {
        this.windowModel = model;
        this.windowView = view;
        this.mapModel = mapModel;
        this.windowView.addResizeListener(new ResizeListener());
        System.out.println("windowController initialized");
    }

    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            int newWidth = e.getComponent().getWidth();
            int newHeight = e.getComponent().getHeight();

            if (newHeight * 2 > newWidth) {
                newHeight = newWidth / 2;
            } else {
                newWidth = newHeight * 2;
            }
            windowModel.setWidth(newWidth);
            windowModel.setHeight(newHeight);
            windowView.setPreferredSize(new Dimension(newWidth, newHeight));
            windowView.pack();
            windowView.updatePanelSize();

            mapModel.setMaxCoordinates(newWidth, newHeight);
            
        }        
    }
    
}
