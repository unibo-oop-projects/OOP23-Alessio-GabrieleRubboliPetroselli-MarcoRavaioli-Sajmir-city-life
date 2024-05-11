package unibo.citysimulation.model.person;

import java.util.Optional;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;

public class StaticPersonImpl implements StaticPerson {
    protected final PersonData personData;
    protected PersonState state;
    protected Optional<Pair<Integer, Integer>> homePosition;
    protected TransportLine transportLine;
    protected int tripDuration;

    public StaticPersonImpl(PersonData personData) {
        this.personData = personData;
        this.state = PersonState.AT_HOME;
        this.homePosition = Optional.of(personData.residenceZone().getRandomPosition());
        this.transportLine = ZoneTable.getTransportLine(personData.residenceZone(), personData.business().getZone());
        this.getTrip();
    }

    public PersonData getPersonData() {
        return personData;
    }

    public PersonState getState() {
        return state;
    }

    protected void setState(PersonState state) {
        this.state = state;
    }

    private void getTrip() {
        if (personData.residenceZone() == personData.business().getZone()) {
            this.tripDuration = 0;
        } else {
            this.transportLine = ZoneTable.getTransportLine(personData.residenceZone(), personData.business().getZone());
            tripDuration = this.transportLine.getDuration() * 60;
        }
    }
}
