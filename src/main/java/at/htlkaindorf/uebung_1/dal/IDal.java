package at.htlkaindorf.uebung_1.dal;

import at.htlkaindorf.uebung_1.dtos.SchoolClassDto;
import at.htlkaindorf.uebung_1.dtos.SchoolDto;
import at.htlkaindorf.uebung_1.dtos.StudentDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IDal {
    public void save(StudentDto dto);
    public void deleteAllStudents();
    public SchoolDto getAllStudents();

    StudentDto getAllStudentsById(int id);
    List<SchoolClassDto> getAllClasses();
}
