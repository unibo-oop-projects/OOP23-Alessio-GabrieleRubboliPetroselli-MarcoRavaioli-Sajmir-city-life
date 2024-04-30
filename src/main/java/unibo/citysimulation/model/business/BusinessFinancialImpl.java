package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import ch.qos.logback.core.util.Duration;

public class BusinessFinancialImpl implements BusinessFinancial{

    private record BusinessUse(List <Employee> employees, LocalTime openingTime, LocalTime closingTime, double wageRate, double income) implements Business {

        @Override
        public Business updateEmployees(List<Employee> employees) {
            return new BusinessUse(employees, openingTime, closingTime, wageRate, income);
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

        @Override
        public double getWageRate() {
            return wageRate;
        }

        @Override
        public double getIncome() {
            return income;
        }

    }

    private long calculateTotalBusinessHours(Business business) {
        return ChronoUnit.HOURS.between(business.getOpeningTime(), business.getClosingTime());
    }

    @Override
    public double calculatePersonnelCost(Business business) {
        long totalBusinessHours = calculateTotalBusinessHours(business);
        double totalPersonnelCost = totalBusinessHours * business.getEmployees().size() * business.getWageRate();
        return totalPersonnelCost;
    }

    @Override
    public double calculateIncome(Business business) {
        double totalIncome = business.getIncome();
        int numberOfEmployees = business.getEmployees().size();

        int employeeThreshold = 5;
        double growthRate = 0.1;

        if (numberOfEmployees > employeeThreshold) {
            totalIncome = totalIncome * (1 + growthRate);
        }

        return totalIncome;
    }

    @Override
    public double calculateProfit(Business business) {
        double totalProfit = calculateIncome(business) - calculatePersonnelCost(business);
        return totalProfit;
    }



    
}
