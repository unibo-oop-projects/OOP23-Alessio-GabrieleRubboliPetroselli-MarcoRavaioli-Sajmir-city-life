package unibo.citysimulation;

import unibo.citysimulation.controller.WindowController;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.WindowView;

import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * The main launcher of the Java application, made to build the JAR.
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
        WindowModel windowModel = createInitialWindowModel();
        MapModel mapModel = new MapModel();
        WindowView windowView = new WindowView(windowModel, mapModel);
        new WindowController(windowModel, windowView, mapModel);
    }

    private static WindowModel createInitialWindowModel() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        int frameHeight = maxHeight > maxWidth / 2 ? maxWidth / 2 : maxHeight;
        int frameWidth = frameHeight * 2;
        System.out.println("Simulation Launcher createInitialWindowModel");
        return new WindowModel(frameWidth, frameHeight);
    }
}