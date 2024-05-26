package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.zone.Zone;

/**
 * Represents the static data of a person, containing the name, age, business, and residence zone.
 * 
 * @param name          the name of the person.
 * @param age           the age of the person.
 * @param business      the business where the person works.
 * @param residenceZone the zone where the person lives.
 */
public record PersonData(String name, int age, Business business, Zone residenceZone) {
}
