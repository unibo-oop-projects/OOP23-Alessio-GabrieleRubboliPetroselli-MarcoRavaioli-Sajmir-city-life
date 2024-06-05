package unibo.citysimulation.model.transport.api;

import java.util.List;
import unibo.citysimulation.model.zone.Zone;
/**
 * This interface represents a factory for creating transport objects.
 */
public interface TransportFactory {
    /**
     * Create a list of TransportLine objects based on a list of Zone objects.
     * 
     * @param zones List of Zone objects.
     * @return List of TransportLine objects.
     */
    List<TransportLine> createTransportsFromFile(final List<Zone> zones);
  
    
}
