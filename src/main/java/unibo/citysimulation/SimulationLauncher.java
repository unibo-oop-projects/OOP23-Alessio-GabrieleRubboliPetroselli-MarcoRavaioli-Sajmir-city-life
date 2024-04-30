package unibo.citysimulation;

import unibo.citysimulation.controller.WindowController;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.WindowView;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * The main launcher of the Java application, used to build the JAR.
 */
public final class SimulationLauncher {

    private SimulationLauncher() {
    }

    /**
     * The main method.
     *
     * @param args No arguments should be passed to this program.
     */
    public static void main(final String[] args) {
        // Create the initial window model
        WindowModel windowModel = createInitialWindowModel();
        
        // Create the map model
        MapModel mapModel = new MapModel();
        
        // Create the window view with the window model and map model
        WindowView windowView = new WindowView(windowModel, mapModel);
        
        // Create the window controller with the window model, window view, and map model
        new WindowController(windowModel, windowView, mapModel);
    }

    /**
     * Create the initial window model with dimensions based on the screen size.
     *
     * @return The initialized window model.
     */
    private static WindowModel createInitialWindowModel() {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the maximum dimensions based on the screen size and a constant percentage
        int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        // Calculate the frame dimensions based on the maximum dimensions
        int frameHeight = maxHeight > maxWidth / 2 ? maxWidth / 2 : maxHeight;
        int frameWidth = frameHeight * 2;

        // Create and return the window model with the calculated dimensions
        return new WindowModel(frameWidth, frameHeight);
    }
}
