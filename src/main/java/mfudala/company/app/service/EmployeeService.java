package mfudala.company.app.service;

import mfudala.company.app.model.ActiveEmployee;
import mfudala.company.app.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    ActiveEmployee addActiveEmployee(ActiveEmployee activeEmployee);

    List<Employee> getAllEmployees();

    List<ActiveEmployee> getAllActiveEmployees();

    void deleteEmployeeById(Long id);

    void deleteActiveEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    ActiveEmployee updateActiveEmployee(ActiveEmployee activeEmployee);
}
