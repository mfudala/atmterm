package mfudala.company.app.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    @ManyToMany(mappedBy = "teams", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<Employee> employees;
}
