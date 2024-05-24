package unibo.citysimulation.model.person;

import java.util.Optional;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;

public record PersonData(String name, int age, Optional <Business> business, Zone residenceZone) {
}
