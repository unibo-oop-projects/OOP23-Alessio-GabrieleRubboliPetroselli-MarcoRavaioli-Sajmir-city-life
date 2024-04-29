package unibo.citysimulation.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import unibo.citysimulation.model.ClockModel;


public class PersonImpl<PersonState> implements Person<PersonState> {
    private String name;
    private PersonState state;
    private int money;
    private Business business;
    private Zone residenceZone;
    private ClockModel clock;



    public PersonImpl(PersonState state, int money, Business business, Zone residenceZone) {
        this.state = state;
        this.money = money;
        this.business = business;
        this.residenceZone = residenceZone;
        this.clock = new Clock();
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

    public Zone getResidenceZone() {
        return residenceZone;
    }

    public Zone getBusinessZone() {
        return business.getZone();
    }

    public TransportLine getTransportLine() {
        return linesMatrix[residenceZone.getName()][getBusinessZone().getName()];
    }

    public boolean checkTimeToGoToWork() {
        return clock.getCurrentTime() == business.getOpeningTime() - getTransportLine().getDuration();
    }
    
}
