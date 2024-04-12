package unibo.citysimulation.model.person;


public class PersonImpl<S> implements Person<S> {
    private S state;
    private int money;

    public PersonImpl(S state, int money) {
        this.state = state;
        this.money = money;
    }

    @Override
    public S getState() {
        return state;
    }

    @Override
    public int getMoney() {
        return money;
    }
}
