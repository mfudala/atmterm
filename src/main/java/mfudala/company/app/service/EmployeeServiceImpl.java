package mfudala.company.app.service;

import mfudala.company.app.model.ActiveEmployee;
import mfudala.company.app.model.Employee;
import mfudala.company.app.repository.ActiveEmployeeRepository;
import mfudala.company.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ActiveEmployeeRepository activeEmployeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, ActiveEmployeeRepository employeeRepository) {
        this.employeeRepository = repository;
        activeEmployeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public ActiveEmployee addActiveEmployee(ActiveEmployee activeEmployee) {
        employeeRepository.save(activeEmployee);
        return activeEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<ActiveEmployee> getAllActiveEmployees() {
        return activeEmployeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteActiveEmployeeById(Long id) {
        activeEmployeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public ActiveEmployee updateActiveEmployee(ActiveEmployee activeEmployee) {
        return activeEmployeeRepository.save(activeEmployee);
    }
}
