package unibo.citysimulation.controller;

import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.view.WindowView;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowController {
    private WindowModel model;
    private WindowView view;

    public WindowController(WindowModel model, WindowView view) {
        this.model = model;
        this.view = view;
        this.view.addResizeListener(new ResizeListener());
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


            model.setWidth(newWidth);
            model.setHeight(newHeight);
            view.setPreferredSize(new Dimension(newWidth, newHeight));

            view.updatePanelSize();
            view.pack();
        }        
    }
    
}
