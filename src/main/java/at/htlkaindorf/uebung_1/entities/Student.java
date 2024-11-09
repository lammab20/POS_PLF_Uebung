package at.htlkaindorf.uebung_1.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="student")
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String initialLetter;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private String name;
    private int yearOfBirth;
    @ManyToOne
    private SchoolClass schoolClass;
}
