package mfudala.company.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEmployee extends Employee {

    private Double salary;

    private LocalDate dateOfEmployment;

    public ActiveEmployee buildActiveEmployee(String name, LocalDate dateOfEmployment, Double salary) {
        ActiveEmployee activeEmployee = new ActiveEmployee();
        activeEmployee.setName(name);
        activeEmployee.setDateOfEmployment(dateOfEmployment);
        activeEmployee.setSalary(salary);
        return activeEmployee;
    }
}
