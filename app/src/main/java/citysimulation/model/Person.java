/**
 * An interface for modelling a person
 * 
 * @param S, the state of the person
 */
public interface Person<S> {

    /**
	 * @return the state of the person
	 */
    <S> getState();
    
    /**
     * @return the amount of money the person has
     */
    int getMoney();
    
}
