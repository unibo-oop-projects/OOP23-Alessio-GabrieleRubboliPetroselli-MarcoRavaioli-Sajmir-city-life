package unibo.citysimulation.model.person;

import java.time.LocalTime;

/**
 * Represents a dynamic person that can change state based on the current time and move in order to work.
 */
public interface DynamicPerson extends StaticPerson {

    /**
     * Checks if the state of the person has to change based on the current time.
     * 
     * @param currentTime the current time.
     */
    void checkState(LocalTime currentTime);

    /**
     * Gets the business beginning time.
     */
    int getBusinessBegin();

    /**
     * Gets the business ending time.
     */
    int getBusinessEnd();
}
