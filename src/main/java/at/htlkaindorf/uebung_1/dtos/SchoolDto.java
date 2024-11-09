package at.htlkaindorf.uebung_1.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "school")
public class SchoolDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(localName = "STUDENTS")
    @JacksonXmlProperty(localName = "student")
    private List<StudentDto> students = new ArrayList<>();
}
