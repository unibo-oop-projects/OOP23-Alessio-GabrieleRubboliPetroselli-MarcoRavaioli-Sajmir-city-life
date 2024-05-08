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


        System.out.println("line duration: " + lineDuration);


        if (moveBool) {
            this.lastArrivingTime = currentTime + lineDuration;
        }
        return moveBool;
    }

    public boolean checkTimeToGoToWork(LocalTime currentTime) {
        int lineDuration;
        if (this.residenceZone == this.business.getZone()) {
            lineDuration = 0;
        } else {
            lineDuration = transportLine.getDuration() * 60;
        }
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                business.getOpeningTime().toSecondOfDay() - lineDuration,
                lineDuration)) {
                    
            if (lineDuration == 0) {
                this.setState(PersonState.WORKING);
                this.position = Optional.of(business.getPosition()); // così appaiono uno sopra l'altro, serve qualcosa per far vedere che è lì vicino
            } else {
                this.setState(PersonState.MOVING);
                this.transportLine.incrementPersonInLine();
                this.position = Optional.empty();
            }
            this.lastDestination = PersonState.WORKING;
            return true;
        }
        
        return false;
    }

    public boolean checkTimeToGoHome(LocalTime currentTime) {
        int lineDuration;
        if (this.residenceZone == this.business.getZone()) {
            lineDuration = 0;
        } else {
            lineDuration = transportLine.getDuration() * 60;
        }
        if (this.checkTimeToMove(currentTime.toSecondOfDay(),
                business.getClosingTime().toSecondOfDay(),
                lineDuration)) {
            if (lineDuration == 0) {
                this.setState(PersonState.AT_HOME);
                this.position = Optional.of(residenceZone.getRandomPosition()); // servirebbe che la posizione random sia solo all'inizio poi una volta che sa doc'è casa tenga quella come posizione
            } else {                                                            
                this.setState(PersonState.MOVING);
                this.transportLine.incrementPersonInLine();
                this.position = Optional.empty();

            }
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
            if(this.state == PersonState.AT_HOME) {
                this.position = Optional.of(residenceZone.getRandomPosition()); // così appaiono uno sopra l'altro, serve qualcosa per far vedere che è lì vicino
            } else {
                this.position = Optional.of(business.getPosition()); 
            }
            this.transportLine.decrementPersonInLine();
            return true;
        }
        return false;
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

}
