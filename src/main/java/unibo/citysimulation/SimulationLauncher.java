package unibo.citysimulation;

import unibo.citysimulation.controller.WindowController;
import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.WindowView;

/**
 * The main launcher of the Java application, used to build the JAR.
 */
public final class SimulationLauncher {

    /**
     * Starts the simulation by initializing the model, view, and controller.
     */
    public void start() {
        final CityModel cityModel = new CityModel();
        final WindowView windowView = new WindowView();
        new WindowController(windowView, cityModel);
    }
}
