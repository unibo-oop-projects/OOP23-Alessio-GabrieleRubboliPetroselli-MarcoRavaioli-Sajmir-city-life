package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;
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
    private Optional<Pair<Integer, Integer>> position;
    private TransportLine transportLine;
    private int tripDuration;

    public PersonImpl(String name, int age, int money, Business business, Zone residenceZone, ZoneTable zonetable) {
        this.age = age;
        this.name = name;
        this.state = PersonState.AT_HOME;
        this.lastDestination = PersonState.WORKING;
        this.money = money;
        this.business = business;
        this.residenceZone = residenceZone;
        this.zoneTable = zonetable;
        this.position = Optional.of(new Pair<>(0, 0));
        this.transportLine = zonetable.getTransportLine(residenceZone, business.getZone());
        this.tripDuration = this.getTripDuration();

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
    public void setState(PersonState state) {
        this.state = state;
    }

    @Override
    public int getMoney() {
        return money;
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

    public boolean checkTimeToMove(int currentTime, int timeToMove, int lineDuration) {
        boolean moveBool = (currentTime == timeToMove);
        System.out.println("line duration: " + lineDuration);
        if (moveBool) {
            this.lastArrivingTime = currentTime + lineDuration;
        }
        return moveBool;
    }

    public void checkTimeToGoToWork(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), business.getOpeningTime().toSecondOfDay() - tripDuration,
                tripDuration)) {
            movePerson(PersonState.WORKING);
        }
    }

    public void checkTimeToGoHome(LocalTime currentTime) {
        if (this.checkTimeToMove(currentTime.toSecondOfDay(), business.getClosingTime().toSecondOfDay(),
                tripDuration)) {
            movePerson(PersonState.AT_HOME);
        }
    }

    public void incrementLastArrivingTime(int duration) {
        this.lastArrivingTime += duration;
    }

    public void checkArrivingTime(LocalTime currentTime) {
        if (currentTime.toSecondOfDay() == this.lastArrivingTime) {
            this.setState(this.lastDestination);
            updatePosition();
            this.transportLine.decrementPersonInLine();
        }
    }

    public void checkState(LocalTime currentTime) {
        switch (this.state) {
            case MOVING:
                this.checkArrivingTime(currentTime);
                break;
            case WORKING:
                this.checkTimeToGoHome(currentTime);
                break;
            case AT_HOME:
                this.checkTimeToGoToWork(currentTime);
                break;
        }
        System.out.println(this.getName() + ", " + this.getState());
        System.out.println("coordinate position: " + (position.isPresent() ? (position.get().getFirst() + ", " + position.get().getSecond()) : ""));
    }

    private int getTripDuration() {
        int lineDuration;
        if (this.residenceZone == this.business.getZone()) {
            lineDuration = 0;
        } else {
            lineDuration = transportLine.getDuration() * 60;
        }
        return lineDuration;
    }

    private void movePerson(PersonState newState) {
        if (this.tripDuration == 0) {
            this.setState(newState);
        } else {
            this.setState(PersonState.MOVING);
            this.transportLine.incrementPersonInLine();
        }
        this.lastDestination = newState;
        this.updatePosition();
    }

    private void updatePosition() {
        switch (this.state) {
            case MOVING:
                this.position = Optional.empty();
                break;
            case WORKING:
                this.position = Optional.of(business.getPosition());
                break;
            case AT_HOME:
                this.position = Optional.of(residenceZone.getRandomPosition());
                break;
        }
    }
}
