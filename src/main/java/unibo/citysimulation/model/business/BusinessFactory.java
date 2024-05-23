package unibo.citysimulation.model.business;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import unibo.citysimulation.model.zone.Zone;

/**
 * The BusinessFactory class is responsible for creating instances of Business objects.
 */
public final class BusinessFactory {

    private BusinessFactory() {
    }

    /**
     * Creates a new Business object based on the specified BusinessType.
     *
     * @param type The type of business to create.
     * @param zone The zone where the business is located.
     * @return An Optional containing the created Business object, or an empty Optional if the type is invalid.
     */
    public static Optional<Business> createBusiness(final BusinessType type, final Zone zone) {
        switch (type) {
            case BIG:
                return Optional.of(new BigBusiness(zone));
            case MEDIUM:
                return Optional.of(new MediumBusiness(zone));
            case SMALL:
                return Optional.of(new SmallBusiness(zone));     
            default:
                break;
        }
        return Optional.empty();
    }

    /**
     * Creates a random Business object.
     *
     * @param zones The list of available zones.
     * @return An Optional containing the created Business object.
     */
    public static Optional<Business> getRandomBusiness(final List<Zone> zones) {
        final Random random = new Random();
        final BusinessType type = BusinessType.values()[random.nextInt(BusinessType.values().length)];
        final Zone zone = zones.get(random.nextInt(zones.size()));
        return createBusiness(type, zone);
    }
}
    
