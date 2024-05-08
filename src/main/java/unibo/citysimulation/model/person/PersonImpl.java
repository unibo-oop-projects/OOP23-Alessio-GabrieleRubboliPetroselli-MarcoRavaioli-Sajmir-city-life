package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import java.time.LocalTime;
import java.util.Optional;

public class PersonImpl implements Person {
    private String name;
    private int age;
    private PersonState state;
    private int money;
    private Business business;
    private Zone residenceZone;
    private ZoneTable zoneTable;
    private int lastArrivingTime = 0;
    private PersonState lastDestination;

    public PersonImpl(String name, int age, int money, Business business, Zone residenceZone, ZoneTable zonetable) {
        this.age = age;
        this.name = name;
        this.state = PersonState.AT_HOME;
        this.lastDestination = PersonState.WORKING;
        this.money = money;
        this.business = business;
        this.residenceZone = residenceZone;
        this.zoneTable = zonetable;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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

    public Optional<Zone> getCurrentZone() {
        switch (this.state) {
            case WORKING:
                return Optional.of(business.getZone());
            case AT_HOME:
                return Optional.of(residenceZone);
            default:
                return Optional.empty();
        }
    }

    public Zone getBusinessZone() {
        return business.getZone();
    }

    public boolean checkTimeToMove(int currentTime, int timeToMove, int lineDuration) {
        boolean moveBool = (currentTime == timeToMove);
        System.out.println("current time: " + currentTime);
        System.out.println("time to move: " + timeToMove);
        System.out.println("line duration: " + lineDuration);
        if (moveBool) {
            this.setState(PersonState.MOVING);
            this.lastArrivingTime = currentTime + lineDuration;
        }
        return moveBool;
    }

    public boolean checkTimeToGoToWork(LocalTime currentTime) {
        int lineDuration = zoneTable.getMinutesForPair(residenceZone, business.getZone()) * 60;
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
            business.getOpeningTime().toSecondOfDay() - lineDuration,
            lineDuration)) {
            System.out.println("time to move to work");
            this.lastDestination = PersonState.WORKING;
            return true;
            }
        System.out.println("Not moving");
        return false;
    }

    public boolean checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
            business.getClosingTime().toSecondOfDay(),
            zoneTable.getMinutesForPair(business.getZone(), residenceZone) * 60)) {
            this.lastDestination = PersonState.AT_HOME;
            return true;
            }
            return false;
    }

    public void incrementLastArrivingTime(int duration) {
        this.lastArrivingTime += duration;
    }

    public boolean checkArrivingTime(LocalTime currentTime) {
        if (currentTime.toSecondOfDay() == this.lastArrivingTime) {
            this.setState(this.lastDestination);
            return true;
        }
        return false;
    }

    public void checkState(LocalTime currentTime) {
        switch (this.state) {
            case MOVING:
                System.out.println("state moving");
                this.checkArrivingTime(currentTime);
                break;
            case WORKING:
                System.out.println("state working");
                this.checkTimeToGoHome(currentTime);
                break;
            case AT_HOME:
                System.out.println("state at home");
                this.checkTimeToGoToWork(currentTime);
                break;
        }
    }
    
}
