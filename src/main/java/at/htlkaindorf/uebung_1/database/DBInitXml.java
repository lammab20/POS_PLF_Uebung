package at.htlkaindorf.uebung_1.database;

import at.htlkaindorf.uebung_1.dal.IDal;
import at.htlkaindorf.uebung_1.dtos.SchoolDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;

@Profile("xml")
@Configuration
@Slf4j
public class DBInitXml implements ApplicationRunner {

    @Autowired
    private IDal dal;

    @Value("classpath:students.xml")
    private Resource resource;

    @PostConstruct
    public void init() {
        log.info("Start reading from xml");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dal.deleteAllStudents();
        XmlMapper mapper = new XmlMapper();

        mapper.registerModule(new JavaTimeModule());

        SchoolDto sd = mapper.readValue(resource.getFile(), SchoolDto.class);
        sd.getStudents().forEach(dto -> dal.save(dto));
    }

    @PreDestroy
    public void onDestroy()
    {
        log.info("--> DBInitXML / onDestroy");
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path xmlPath = Path.of("data.xml");

            SchoolDto sd = dal.getAllStudents();
            String xml = xmlMapper.writeValueAsString(sd);
            Files.writeString(xmlPath, xml);
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
