package mfudala.company.app;

import mfudala.company.app.model.ActiveEmployee;
import mfudala.company.app.model.Employee;
import mfudala.company.app.model.Team;
import mfudala.company.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AppApplication {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public CommandLineRunner dbInflater() {
        return args -> uploadDataToDB();
    }

    private void uploadDataToDB() {
        employeeService.addActiveEmployee(buildActiveEmployee("Mary", LocalDate.of(2020, 1, 2), 100d));
        employeeService.addActiveEmployee(buildActiveEmployee("John", LocalDate.of(2021, 2, 2), 200d));
        employeeService.addActiveEmployee(buildActiveEmployee("Harry", LocalDate.of(2019, 3, 2), 300d));
        employeeService.addEmployee(Employee.builder().name("Adam").teams(getMockTeam()).build());
        employeeService.addEmployee(Employee.builder().name("Tom").build());
    }

    private ActiveEmployee buildActiveEmployee(String name, LocalDate dateOfEmployment, Double salary) {
        ActiveEmployee activeEmployee = new ActiveEmployee();

        activeEmployee.setName(name);
        activeEmployee.setDateOfEmployment(dateOfEmployment);
        activeEmployee.setSalary(salary);

        return activeEmployee;
    }

    private List<Team> getMockTeam() {
        List<Team> teams = new ArrayList<>();

        Team dreamTeam = new Team();
        dreamTeam.setName("DreamTeam");

        Team starTeam = new Team();
        starTeam.setName("StarTeam");

        teams.add(dreamTeam);
        teams.add(starTeam);

        return teams;
    }
}
