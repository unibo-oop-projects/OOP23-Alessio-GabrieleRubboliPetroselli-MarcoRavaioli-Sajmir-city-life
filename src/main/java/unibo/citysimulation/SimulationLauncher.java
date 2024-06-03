package unibo.citysimulation;

import unibo.citysimulation.controller.WindowController;
import unibo.citysimulation.model.CityModelImpl;
import unibo.citysimulation.view.WindowView;

/**
 * The main launcher of the Java application, used to build the JAR.
 */
public final class SimulationLauncher {

    /**
     * Starts the simulation by initializing the model, view, and controller.
     */
    public void start() {
        final CityModelImpl cityModel = new CityModelImpl();
        final WindowView windowView = new WindowView(cityModel.getFrameWidth(), cityModel.getFrameHeight());
        new WindowController(windowView, cityModel);
    }
}
