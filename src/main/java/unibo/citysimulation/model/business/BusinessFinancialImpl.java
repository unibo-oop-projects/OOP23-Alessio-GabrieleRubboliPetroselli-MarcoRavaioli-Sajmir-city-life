package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BusinessFinancialImpl implements BusinessFinancial{

    private record BusinessUse(List <Employee> employees, LocalTime openingTime, LocalTime closingTime) implements Business {

        @Override
        public Business updateEmployees(List<Employee> employees) {
            return new BusinessUse(employees, openingTime, closingTime);
        }

        @Override
        public List<Employee> getEmployees() {
            return employees;
        }

        @Override
        public LocalTime getOpeningTime() {
            return openingTime;
        }
        
        @Override
        public LocalTime getClosingTime() {
            return closingTime;
        }

    }

    private long calculateTotalBusinessHours(Business business) {
        return ChronoUnit.HOURS.between(business.getOpeningTime(), business.getClosingTime());
    }
    
}
