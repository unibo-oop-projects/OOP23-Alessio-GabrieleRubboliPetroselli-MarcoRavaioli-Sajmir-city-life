package unibo.citysimulation.model.business;

import java.util.Optional;
import java.util.Random;

public final class BusinessFactory{

    
    public Optional<Business> createBusiness(BusinessType type) {
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

    public static BusinessType getRandomBusinessType() {
        // Creazione di un oggetto Random
        Random random = new Random();

        // Generazione di un numero casuale tra 0 e 2 (inclusi)
        int typeIndex = random.nextInt(3);

        // Restituzione del tipo di business corrispondente al numero casuale generato
        switch (typeIndex) {
            case 0:
                return BusinessType.SMALL;
            case 1:
                return BusinessType.MEDIUM;
            case 2:
                return BusinessType.BIG;
            default:
                return BusinessType.SMALL; // Default a small business se qualcosa va storto
        }
    }

    
}
    
