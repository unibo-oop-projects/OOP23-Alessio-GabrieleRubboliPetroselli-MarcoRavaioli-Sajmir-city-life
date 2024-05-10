package unibo.citysimulation.model.person;

import java.time.LocalTime;

import unibo.citysimulation.model.person.PersonImpl.PersonData;

/**
 * An interface for modelling a person
 * 
 * @param PersonState, the state of the person
 */
public interface Person {


    /**
	 * The three standard states of a person
	 */
    enum PersonState {
        MOVING,
        WORKING,
        AT_HOME
    }

    /**
	 * @return the state of the person
	 */
    PersonState getState();

    PersonData getPersonData();

    void setState(PersonState state);

    void checkState(LocalTime currentTime);
    
}
