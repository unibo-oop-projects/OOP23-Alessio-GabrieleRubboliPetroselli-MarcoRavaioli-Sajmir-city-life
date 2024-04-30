package unibo.citysimulation.model.person;

import unibo.citysimulation.model.ClockModel;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.transport.Zone;
import unibo.citysimulation.model.transport.ZoneTable;


public class PersonImpl implements Person {
    private String name;
    private PersonState state;
    private int money;
    private Business business;
    private Zone residenceZone;
    private ClockModel clock;
    private ZoneTable zoneTable;



    public PersonImpl(int money, Business business, Zone residenceZone, ClockModel clock) {
        this.state = PersonState.AT_HOME;
        this.money = money;
        this.business = business;
        this.residenceZone = residenceZone;
        this.clock = clock;
        zoneTable = new ZoneTable();
    }

    public String getName() {
        return name;
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

    public boolean checkTimeToGoToWork() {
        int currentTime = clock.getCurrentTime().toSecondOfDay();
        int openingTime = business.getOpeningTime().toSecondOfDay();
        int lineDuration = zoneTable.getMinutesForPair(residenceZone, getBusinessZone()) * 60;
        return currentTime == openingTime - lineDuration;
    }


    
}
