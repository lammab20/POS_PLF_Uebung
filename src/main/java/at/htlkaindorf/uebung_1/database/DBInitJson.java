package at.htlkaindorf.uebung_1.database;

import at.htlkaindorf.uebung_1.dal.IDal;
import at.htlkaindorf.uebung_1.dtos.SchoolDto;
import at.htlkaindorf.uebung_1.dtos.StudentDto;
import at.htlkaindorf.uebung_1.entities.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Profile("json")
@Slf4j
@Configuration
public class DBInitJson implements CommandLineRunner {
    @Value("classpath:students.json")
    private Resource resource;

    @Autowired
    private IDal dal;

    @Autowired
    private ObjectMapper om;

    @PostConstruct
    private void init() {
        log.info("--Starting read from JSON");
    }

    @Override
    public void run(String... args) throws Exception {
        dal.deleteAllStudents();
        List<StudentDto> list = om.readValue(resource.getFile(), new TypeReference<List<StudentDto>>() {});

        list.forEach(dto -> dal.save(dto));
    }

    @PreDestroy
    public void onDestroy()
    {
        try
        {
            SchoolDto sd = dal.getAllStudents();
            Path JSON_FILE = Path.of("data.json");
            String jsonString =
                    om.writerWithDefaultPrettyPrinter().writeValueAsString(sd);
            Files.writeString(JSON_FILE, jsonString);
        }
        catch(Exception ex)
        {
            log.error(ex.toString());
        }
    }
}
