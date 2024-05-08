package unibo.citysimulation;
import unibo.citysimulation.controller.WindowController;
import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.WindowView;

/**
 * The main launcher of the Java application, used to build the JAR.
 */
public final class SimulationLauncher {

    //method for starting the simulation
    public void start(){
        // Start the simulation
        System.out.println("Simulation started");
        

        CityModel cityModel = new CityModel();        
         // Create the window view with the window model and map model
        WindowView windowView = new WindowView();
                
        // Create the window controller with the window model, window view, and map model
         new WindowController(windowView, cityModel);
    }
}
