
package restaurant;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
   
    private List<Employee> employees;
    
    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee.getName());
    }

    public boolean removeEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employees.remove(employee);
                System.out.println("Employee removed: " + employee.getName());
                return true;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
        return false;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
        return null;
    }

    
    public List<Employee> getEmployeesWithHighestSalary() {
        List<Employee> result = new ArrayList<>();
        double highestSalary = 0;

        for (Employee employee : employees) {
            if (employee.getSalary() > highestSalary) {
                highestSalary = employee.getSalary();
            }
        }

        for (Employee employee : employees) {
            if (employee.getSalary() == highestSalary) {
                result.add(employee);
            }
        }
        return result;
    }
    public List<Employee> findEmployeesByJobTitle(String jobTitle) {
        List<Employee> result = new ArrayList<>();
       for (Employee employee : employees) {
            if (employee.getJobTitle().equalsIgnoreCase(jobTitle)) {
                result.add(employee);
            }
        }
        return result;
    }
}
