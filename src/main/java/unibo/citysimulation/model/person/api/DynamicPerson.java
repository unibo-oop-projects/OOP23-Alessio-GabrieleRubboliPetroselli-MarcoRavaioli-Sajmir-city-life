package unibo.citysimulation.model.person.api;

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
     * @return the time when the person has to go to work, in seconds.
     */
    int getBusinessBegin();

    /**
     * @return the time when the person has to go back home, in seconds.
     */
    int getBusinessEnd();
}
