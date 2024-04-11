package unibo.citysimulation.model;
/**
 * An interface for modelling a person
 * 
 * 
 */
public interface TransportManagement {
    /**
	 * @return the congestion of the transportLine
	 */
    int getCongestion();
    /**
	 * @return the capacity of the transportLine
	 */
    int getCapacity();

}
