package unibo.citysimulation.model.person;

/**
 * An interface for modelling a person
 * 
 * @param PersonState, the state of the person
 */
public interface StaticPerson {

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
    
}
