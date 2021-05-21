package mfudala.company.app.service;

import mfudala.company.app.dao.ActiveEmployee;
import mfudala.company.app.dao.Employee;
import org.springframework.stereotype.Service;

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
