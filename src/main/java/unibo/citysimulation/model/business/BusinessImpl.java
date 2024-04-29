package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;

public class BusinessImpl implements Business{

    private List<Employee> employees;

    public BusinessImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public void fire(Employee employee) {
       this.employees.remove(employee);
    }

    @Override
    public boolean hasEmployees() {
        return !this.employees.isEmpty();
    }

    @Override
    public int countEmployees() {
        return this.employees.size();
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees);
    }

    @Override
    public EmployeeImpl getEmployeeById(int id) {
        for (Employee employee : this.employees) {
            if (employee.getId() == id) {
                return (EmployeeImpl) employee;
            }
        }
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        for (Employee e : this.employees) {
            if (e.getId() == employee.getId()) {
                e = employee;
            }
        }
    }

    @Override
    public double calculateIncome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateIncome'");
    }

    @Override
    public double calculatePersonnelCost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculatePersonnelCost'");
    }

    @Override
    public double calculateProfit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateProfit'");
    }

    @Override
    public boolean isProfit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isProfit'");
    }

    @Override
    public int getIncome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIncome'");
    }

   
    
    
}
