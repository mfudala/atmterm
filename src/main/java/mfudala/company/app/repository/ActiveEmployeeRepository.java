package mfudala.company.app.repository;

import mfudala.company.app.model.ActiveEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveEmployeeRepository extends JpaRepository<ActiveEmployee, Long> {

}
