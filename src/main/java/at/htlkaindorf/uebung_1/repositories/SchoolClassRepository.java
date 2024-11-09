package at.htlkaindorf.uebung_1.repositories;

import at.htlkaindorf.uebung_1.entities.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    SchoolClass findSchoolClassByName(String className);
}
