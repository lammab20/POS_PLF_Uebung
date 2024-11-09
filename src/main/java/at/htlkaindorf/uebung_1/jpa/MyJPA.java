package at.htlkaindorf.uebung_1.jpa;

import at.htlkaindorf.uebung_1.dal.IDal;
import at.htlkaindorf.uebung_1.dtos.SchoolClassDto;
import at.htlkaindorf.uebung_1.dtos.SchoolDto;
import at.htlkaindorf.uebung_1.dtos.StudentDto;
import at.htlkaindorf.uebung_1.entities.SchoolClass;
import at.htlkaindorf.uebung_1.entities.Student;
import at.htlkaindorf.uebung_1.repositories.SchoolClassRepository;
import at.htlkaindorf.uebung_1.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Profile("postgresql | mysql")
public class MyJPA implements IDal {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;


    @Override
    public void save(StudentDto dto) {
        log.info("New Student " + studentRepository.save(convertToStudent(dto)));
    }

    private Student convertToStudent(StudentDto dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setInitialLetter(String.valueOf(dto.getInitialLetter().charAt(0)));
        s.setLocalDate(dto.getLocalDate());
        s.setLocalDateTime(dto.getLocalDateTime());
        s.setYearOfBirth(dto.getYearOfBirth());
        s.setSchoolClass(findSchoolClassByString(dto.getClassName(), s));

        return s;
    }

    private SchoolClass findSchoolClassByString(String className, Student s) {
        SchoolClass sc = schoolClassRepository.findSchoolClassByName(className);

        if (sc == null) {
            sc = new SchoolClass();
            sc.setName(className);
        }

        sc.getStudentList().add(s);

        return schoolClassRepository.save(sc);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    @Override
    public SchoolDto getAllStudents() {
        List<Student> students = studentRepository.findAll();

        SchoolDto schoolDto = new SchoolDto();

        schoolDto.setStudents(studentRepository.findAll()
                .stream().map(this::convertToStudentDto)
                .collect(Collectors.toList()));

        return schoolDto;
    }

    @Override
    public StudentDto getAllStudentsById(int id) {
        if (studentRepository.findById(id).isPresent()){
            return convertToStudentDto(studentRepository.findById(id).get());
        } else {
            //throw new StudentNotFoundException("Student with id " + id + " does not exist!");
            return null;
        }
    }

    @Override
    public List<SchoolClassDto> getAllClasses() {
        return schoolClassRepository.findAll().stream().map(this::convertToSchoolClassDto).collect(Collectors.toList());
    }

    private StudentDto convertToStudentDto(Student student){
        StudentDto dto = new StudentDto();

        dto.setLocalDate(student.getLocalDate());
        dto.setInitialLetter(student.getInitialLetter());
        dto.setLocalDateTime(student.getLocalDateTime());
        dto.setName(student.getName());
        dto.setYearOfBirth(student.getYearOfBirth());
        dto.setClassName(student.getSchoolClass().getName());

        return dto;
    }

    private SchoolClassDto convertToSchoolClassDto(SchoolClass sc) {
        SchoolClassDto dto = new SchoolClassDto();
        dto.setName(sc.getName());

        return dto;
    }
}
