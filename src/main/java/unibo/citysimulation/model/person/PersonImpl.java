package unibo.citysimulation.model.person;


public class PersonImpl<PersonState> implements Person<PersonState> {
    private PersonState state;
    private int money;

    public PersonImpl(PersonState state, int money) {
        this.state = state;
        this.money = money;
    }

    @Override
    public PersonState getState() {
        return state;
    }

    @Override
    public int getMoney() {
        return money;
    }

    public void setState(PersonState state) {
        this.state = state;
    }
    
}
