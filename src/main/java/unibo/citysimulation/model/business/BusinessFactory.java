package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

/**
 * Factory for creating Business objects.
 * This factory creates a list of Business objects based on a list of Zone objects.
 */
public class BusinessFactory {
    /**
     * Creates a list of Business objects based on a list of Zone objects.
     *
     * @param zones the list of Zone objects
     * @return a list of Business objects
     */
    public static List<Business> createBusinesses(List<Zone> zones) {
        List<Business> businesses = new ArrayList<>();

        List<Object> infos = new ArrayList<>();

        infos.add("ProjectSRL");
        infos.add(1500);
        infos.add(20.0);
        infos.add(LocalTime.of(8, 0));
        infos.add(LocalTime.of(18, 0));
        infos.add(zones.get(0));
        businesses.add(createBusiness(infos));

        infos.clear();

        infos.add("Golden Gym");
        infos.add(1100);
        infos.add(10.0);
        infos.add(LocalTime.of(11, 0));
        infos.add(LocalTime.of(20, 0));
        infos.add(zones.get(1));
        businesses.add(createBusiness(infos));

        infos.clear();

        return businesses;
    }

    private static Business createBusiness(List<Object> infos) {
        return new BusinessImpl((String) infos.get(0), (int) infos.get(1), (double) infos.get(2),
        (LocalTime) infos.get(3), (LocalTime) infos.get(4), (Zone) infos.get(5));
    }
}

