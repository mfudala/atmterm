package mfudala.company.app.controller;

import com.sun.istack.NotNull;
import mfudala.company.app.dao.ActiveEmployee;
import mfudala.company.app.dao.Employee;
import mfudala.company.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        employeeService = service;
    }

    @PostMapping("/add")
    public void addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PostMapping("/add")
    public void addActiveEmployee(@ModelAttribute("activeEmployee") ActiveEmployee activeEmployee) {
        employeeService.addActiveEmployee(activeEmployee);
    }

    @GetMapping("/employees")
    public List<? extends Employee> getEmployees(@RequestParam boolean includeOnlyActive) {
        if (includeOnlyActive) {
            return employeeService.getAllActiveEmployees();
        } else {
            return employeeService.getAllEmployees();
        }
    }
}
