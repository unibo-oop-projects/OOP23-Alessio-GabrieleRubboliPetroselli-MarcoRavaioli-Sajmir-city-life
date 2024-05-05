package unibo.citysimulation.model.person;

import unibo.citysimulation.model.clock.ClockModel;

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
     * @return the name of the person
     */
    String getName();

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

    /**
     * @param money the amount of money to sum/sub to the actual amount of money the person has
     */
    void setMoney(int money);

    ClockModel getClock();
    
}
