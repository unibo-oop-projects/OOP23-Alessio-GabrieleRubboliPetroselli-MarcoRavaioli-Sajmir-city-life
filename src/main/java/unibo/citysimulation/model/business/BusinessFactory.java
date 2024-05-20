package unibo.citysimulation.model.business;

import java.util.Optional;
import java.util.Random;

/**
 * The BusinessFactory class is responsible for creating instances of Business objects.
 */
public final class BusinessFactory {

    /**
     * Creates a new Business object based on the specified BusinessType.
     *
     * @param type The type of business to create.
     * @return An Optional containing the created Business object, or an empty Optional if the type is invalid.
     */
    public static Optional<Business> createBusiness(final BusinessType type) {
        switch (type) {
            case BIG:
                return Optional.of(new BigBusiness());
            case MEDIUM:
                return Optional.of(new MediumBusiness());
            case SMALL:
                return Optional.of(new SmallBusiness());     
            default:
                break;
        }
        return Optional.empty();
    }

    /**
     * Creates a random Business object.
     *
     * @return An Optional containing the created Business object.
     */
    public static Optional<Business> getRandomBusiness() {
        final Random random = new Random();
        final BusinessType type =  BusinessType.values()[random.nextInt(BusinessType.values().length)];  
        return createBusiness(type);
    }
}
    
