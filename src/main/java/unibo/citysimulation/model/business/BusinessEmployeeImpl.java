package unibo.citysimulation.model.business;

import java.util.List;

public class BusinessEmployeeImpl implements BusinessEmployee{

    private record BusinessUse(List <Employee> employees) implements Business {

        @Override
        public Business updateEmployees(List<Employee> employees) {
            return new BusinessUse(employees);
        }

        @Override
        public List<Employee> getEmployees() {
            return employees;
        }     
    }
   

    @Override
    public Business hire(Business business, Employee employee) {
        List<Employee> updatedEmployees = business.getEmployees();
        if (!updatedEmployees.contains(employee)) {
            throw new IllegalArgumentException("Employee not found.");
        }
        updatedEmployees.add(employee);
        return new BusinessUse(updatedEmployees);
    }

    @Override
    public Business fire(Business business, Employee employee) {
        List<Employee> updatedEmployees = business.getEmployees();
        if (!updatedEmployees.contains(employee)) {
            throw new IllegalArgumentException("Employee not found.");
        }
        updatedEmployees.remove(employee);
        return new BusinessUse(updatedEmployees);
    }

    @Override
    public int countEmployees(Business business) {
        return business.getEmployees().size();
    }

    @Override
    public boolean hasEmployees(Business business) {
        return !business.getEmployees().isEmpty();
    }

    @Override   
    public Employee getEmployeeById(Business business, int id) {
        for (Employee employee : business.getEmployees()) {
            if (employee.getId() == id) {
                return (EmployeeImpl) employee;
            }
        }
        return null;
    }

    
}
