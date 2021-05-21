package mfudala.company.app.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

}
