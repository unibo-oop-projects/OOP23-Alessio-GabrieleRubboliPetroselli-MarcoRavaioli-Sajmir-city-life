package unibo.citysimulation.model.person;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;

public record PersonData(String name, int age, int money, Business business, Zone residenceZone, ZoneTable zoneTable) {
}
