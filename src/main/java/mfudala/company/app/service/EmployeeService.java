package mfudala.company.app.service;

import mfudala.company.app.dao.ActiveEmployee;
import mfudala.company.app.dao.Employee;
import mfudala.company.app.repository.ActiveEmployeeRepository;
import mfudala.company.app.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private EmployeeRepository employeeRepository;
    private ActiveEmployeeRepository activeEmployeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repository, ActiveEmployeeRepository employeeRepository) {
        this.employeeRepository = repository;
        activeEmployeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
        LOGGER.info("Employee saved: " + employee.toString());
    }

    public void addActiveEmployee(ActiveEmployee activeEmployee){
        employeeRepository.save(activeEmployee);
        LOGGER.info("Active employee saved: " + activeEmployee.toString());
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public List<ActiveEmployee> getAllActiveEmployees(){
        return activeEmployeeRepository.findAll();
    }
}
