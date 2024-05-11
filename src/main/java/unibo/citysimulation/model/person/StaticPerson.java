package unibo.citysimulation.model.person;

import java.util.Optional;
import unibo.citysimulation.utilities.Pair;
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

    int getMoney();

    void addMoney(int amount);

    int getTripDuration();

    Optional<Pair<Integer, Integer>> getPosition();
    
}
