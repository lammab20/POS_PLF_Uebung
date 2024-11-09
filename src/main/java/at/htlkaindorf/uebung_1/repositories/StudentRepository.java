package at.htlkaindorf.uebung_1.repositories;

import at.htlkaindorf.uebung_1.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
