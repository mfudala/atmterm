package mfudala.company.app.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class ActiveEmployee extends Employee{
    private Double salary;
    @Column
    private LocalDate dateOfEmployment;
}
