package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;



public final class BusinessFactory{

    
    private static final int StartTimeBigBusiness = 12;
    private static final int EndTimeBigBusiness = 21;
    private static final int StartTimeMediumBusiness = 17;
    private static final int EndTimeMediumBusiness = 23;
    private static final int StartTimeSmallBusiness = 8;
    private static final int EndTimeSmallBusiness = 13;
    private static final int Minuts = 0;

    private static final double BIG_REVENUE = 25.0;
    private static final double MEDIUM_REVENUE = 12.0;
    private static final double SMALL_REVENUE = 7.5;

    
    private static final LocalTime BIG_OPENING_TIME = LocalTime.of(StartTimeBigBusiness, Minuts);
    private static final LocalTime BIG_CLOSING_TIME = LocalTime.of(EndTimeBigBusiness, Minuts);
    private static final LocalTime MEDIUM_OPENING_TIME = LocalTime.of(StartTimeMediumBusiness, Minuts);
    private static final LocalTime MEDIUM_CLOSING_TIME = LocalTime.of(EndTimeMediumBusiness, Minuts);
    private static final LocalTime SMALL_OPENING_TIME = LocalTime.of(StartTimeSmallBusiness, Minuts);
    private static final LocalTime SMALL_CLOSING_TIME = LocalTime.of(EndTimeSmallBusiness, Minuts);

    private static final int MAX_EMPLOYEES_BIG_BUSINESS = 300;
    private static final int MAX_EMPLOYEES_MEDIUM_BUSINESS = 100;
    private static final int MAX_EMPLOYEES_SMALL_BUSINESS = 20;
    
    
    public Optional<Business> createBusiness(BusinessType type) {
        switch (type) {
            case BIG:
                return Optional.of(createBigBusiness());
            case MEDIUM:
                return Optional.of(createMediumBusiness());
            case SMALL:
                return Optional.of(createSmallBusiness());     
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

    //business abstarct businessfactory

    public final Business createBigBusiness(){
        return new Business(){
            private static final int MAX_TARDINESS = 3; // Example maximum allowed tardiness for employees
            private static final int MIN_AGE = 25;

            {
            this.opLocalTime = BIG_OPENING_TIME;
            this.clLocalTime = BIG_CLOSING_TIME;
            this.revenue = BIG_REVENUE;
            this.maxEmployees = MAX_EMPLOYEES_BIG_BUSINESS;
            }

        
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for big business
               if(employee.getPerson().getPersonData().age() > MIN_AGE) {
                   business.getEmployees().add(employee);
               }
                
            }
        
            @Override
            public void fire(Employee employee, Business business) {
                // Fire employee if they exceed maximum tardiness
                if (employee.getcountDelay(employee) > MAX_TARDINESS) {
                    business.getEmployees().remove(employee);
                }
            }
        };
    }

    public Business createMediumBusiness(){ 
        return new Business(){
            private static final int MAX_TARDINESS = 5; // Example maximum allowed tardiness for employees
            private static final int MIN_AGE = 35;

            {
            this.opLocalTime = MEDIUM_OPENING_TIME;
            this.clLocalTime = MEDIUM_CLOSING_TIME;
            this.revenue = MEDIUM_REVENUE;
            this.maxEmployees = MAX_EMPLOYEES_MEDIUM_BUSINESS;
            }
        
            
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for medium business
                if(employee.getPerson().getPersonData().age() > MIN_AGE) {
                    business.getEmployees().add(employee);
                }
            }
        
            @Override
            public void fire(Employee employee, Business business) {
                // Fire employee if they exceed maximum tardiness
                if (employee.getcountDelay(employee) > MAX_TARDINESS) {
                    business.getEmployees().remove(employee);
                }
            }
        };
    }


    public Business createSmallBusiness(){

        
        return new Business(){
            private static final int MAX_TARDINESS = 10; // Example maximum allowed tardiness for employees
            private static final int MIN_AGE = 18;
            private static final int MAX_AGE = 25;

            {
            this.opLocalTime = SMALL_OPENING_TIME;
            this.clLocalTime = SMALL_CLOSING_TIME;
            this.revenue = SMALL_REVENUE;
            this.maxEmployees = MAX_EMPLOYEES_SMALL_BUSINESS;
            }
            
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for small business
                int employeeAge = employee.getPerson().getPersonData().age();
                if (employeeAge >= MIN_AGE && employeeAge <= MAX_AGE) {
                    business.getEmployees().add(employee);
                }
            }
        
            @Override
            public void fire(Employee employee, Business business) {
                // Fire employee if they exceed maximum tardiness
                if (employee.getcountDelay(employee) > MAX_TARDINESS) {
                    business.getEmployees().remove(employee);
                }
            }
        };
    }
    
}
    
