package at.htlkaindorf.uebung_1.service;

import at.htlkaindorf.uebung_1.dal.IDal;
import at.htlkaindorf.uebung_1.dtos.SchoolClassDto;
import at.htlkaindorf.uebung_1.dtos.SchoolDto;
import at.htlkaindorf.uebung_1.dtos.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MyService {
    @Autowired
    private IDal dal;

    public SchoolDto getAllStudents() {
        return dal.getAllStudents();
    }

    public StudentDto getStudentById(int id){
        return dal.getAllStudentsById(id);
    }

    public List<SchoolClassDto> getALlClasses() {
        return dal.getAllClasses();
    }
}
