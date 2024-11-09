package at.htlkaindorf.uebung_1.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="school_class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER)//Eager läd die Beziehung sofort
    @ToString.Exclude //verhindert recursion
    private List<Student> studentList = new ArrayList<>();

}
