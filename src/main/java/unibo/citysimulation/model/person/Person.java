package unibo.citysimulation.model.person;
/**
 * An interface for modelling a person
 * 
 * @param PersonState, the state of the person
 */
public interface Person<PersonState> {


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
    
    /**
     * @return the amount of money the person has
     */
    int getMoney();

    /**
     * @param state the new state of the person to set
     */
    void setState(PersonState state);
    
}
