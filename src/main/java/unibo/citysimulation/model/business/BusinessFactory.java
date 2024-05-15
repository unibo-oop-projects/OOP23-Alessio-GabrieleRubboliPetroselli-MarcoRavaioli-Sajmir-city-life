package unibo.citysimulation.model.business;

import java.util.Optional;

public final class BusinessFactory{
    
  
    public Optional<Business> createBusiness(BusinessType type) {
        switch (type) {
            case SMALL:
                return Optional.of(createSmallBusiness());
            case BIG:
                return Optional.of(createBigBusiness());
            case MEDIUM:
                return Optional.of(createMediumBusiness());     
            default:
                break;
        
        }
        return Optional.empty();
        
    }

    //business abstarct businessfactory

    public Business createSmallBusiness(){
        return new Business(){
            private static final int MAX_TARDINESS = 10; // Example maximum allowed tardiness for employees
            private static final int MIN_AGE = 18;
            private static final int MAX_AGE = 25;
        
            
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for small business
                int employeeAge = employee.getPerson().getAge();
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
    public final Business createBigBusiness(){
        return new Business(){
            private static final int MAX_TARDINESS = 3; // Example maximum allowed tardiness for employees
            private static final int MIN_AGE = 25;
        
            
        
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for big business
               if(employee.getPerson().getAge() > MIN_AGE) {
                   employees.add(employee);
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
        
            
            @Override
            public void hire(Employee employee, Business business) {
                // Implement hire method for medium business
                if(employee.getPerson().getAge() > MIN_AGE) {
                    employees.add(employee);
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
    
