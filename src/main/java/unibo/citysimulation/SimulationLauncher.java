package unibo.citysimulation;
import unibo.citysimulation.model.ClockModel;
import unibo.citysimulation.view.NewSimulationView;

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
        new NewSimulationView();
        //ClockModel simulazioneTimer = new ClockModel(7);
        //simulazioneTimer.startSimulation();
    }
}