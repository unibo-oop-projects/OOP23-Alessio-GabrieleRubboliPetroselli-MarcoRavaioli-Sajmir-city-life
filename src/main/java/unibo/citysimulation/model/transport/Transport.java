package unibo.citysimulation.model.transport;
/**
 * An interface for modelling a person
 * 
 * 
 */
public interface Transport {
    /**
	 * @return the congestion of the transport Line
	 */
    int getCongestion();
    /**
	 * @return the capacity of the transport Line
	 */
    int getCapacity();

}
