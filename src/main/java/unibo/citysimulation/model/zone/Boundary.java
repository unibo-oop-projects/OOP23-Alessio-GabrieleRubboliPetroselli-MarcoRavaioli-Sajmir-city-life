package unibo.citysimulation.model.zone;
import unibo.citysimulation.utilities.Pair;

public interface Boundary {
    
    int getWidth();
    
    int getHeight();
   
    boolean contains(Pair<Integer, Integer> point);
    

}
