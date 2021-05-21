package mfudala.company.app.controller;

import mfudala.company.app.model.ActiveEmployee;
import mfudala.company.app.model.Employee;
import mfudala.company.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.addEmployee(employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEmployee);
    }

    @PostMapping("/employee/active")
    public ResponseEntity<Employee> addActiveEmployee(@RequestBody ActiveEmployee activeEmployee) {
        ActiveEmployee createdEmployee = employeeService.addActiveEmployee(activeEmployee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEmployee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<? extends Employee>> getEmployees(@RequestParam(required = false) boolean includeOnlyActive) {

        List<? extends Employee> employees;

        if (includeOnlyActive) {
            employees = employeeService.getAllActiveEmployees();
        } else {
            employees = employeeService.getAllEmployees();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employees);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/employee/active/{id}")
    public ResponseEntity<ActiveEmployee> deleteActiveEmployee(@PathVariable Long id) {
        employeeService.deleteActiveEmployeeById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmp = employeeService.updateEmployee(employee);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedEmp);
    }

    @PutMapping("/employee/active")
    public ResponseEntity<Employee> updateActiveEmployee(@RequestBody ActiveEmployee activeEmployee) {
        ActiveEmployee updatedEmp = employeeService.updateActiveEmployee(activeEmployee);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedEmp);
    }
}
