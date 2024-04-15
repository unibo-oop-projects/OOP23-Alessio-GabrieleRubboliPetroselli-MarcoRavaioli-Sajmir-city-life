package unibo.citysimulation.model.transport;

/**
 * Represents a transport line within the city simulation.
 */
public class TransportLine {
    private String name;
    // Altri campi e metodi necessari da aggiungere dopo..

    public TransportLine(String name) {
        this.name = name;
    }

    // Metodi getter e setter per gli altri campi, se necessario da aggungere..

    @Override
    public String toString() {
        return name;
    }
}
