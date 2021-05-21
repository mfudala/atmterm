package mfudala.company.app.controller;

import com.sun.istack.NotNull;
import mfudala.company.app.dao.ActiveEmployee;
import mfudala.company.app.dao.Employee;
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
        employeeService = service;
    }

    @PostMapping("/addEmployee")
    public void addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PostMapping("/addActiveEmployee")
    public void addActiveEmployee(@ModelAttribute("activeEmployee") ActiveEmployee activeEmployee) {
        employeeService.addActiveEmployee(activeEmployee);
    }

    @GetMapping("/employees")
    public ResponseEntity<? extends Employee> getEmployees(@RequestParam(required = false) boolean includeOnlyActive) {
        ResponseEntity responseEntity;
        ResponseBody responseBody;
        if (includeOnlyActive) {
            responseBody = employeeService.getAllActiveEmployees().to;
        } else {
            return employeeService.getAllEmployees();
        }

        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
}
