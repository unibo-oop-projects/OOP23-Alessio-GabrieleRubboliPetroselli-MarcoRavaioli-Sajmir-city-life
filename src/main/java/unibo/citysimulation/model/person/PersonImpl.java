package unibo.citysimulation.model.person;


public class PersonImpl<PersonState> implements Person<PersonState> {
    private PersonState state;
    private int money;
    private Business business;
    private Zone residenceZone;



    public PersonImpl(PersonState state, int money, Business business, Zone residenceZone) {
        this.state = state;
        this.money = money;
        this.business = business;
        this.residenceZone = residenceZone;
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

    @Override
    public void setMoney(int amount) {
        this.money = this.money + amount;
    }

    public Business getBusiness() {
        return business;
    }
    
}
