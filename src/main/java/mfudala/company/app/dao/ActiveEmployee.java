package mfudala.company.app.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEmployee extends Employee {

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private LocalDate dateOfEmployment;
}
