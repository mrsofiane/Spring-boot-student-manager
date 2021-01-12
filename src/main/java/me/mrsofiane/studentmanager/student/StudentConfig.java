package me.mrsofiane.studentmanager.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student sofiane = new Student(
                    "Sofiane",
                    "sofiane@gmail.com",
                    LocalDate.of(1997, MARCH, 7)
            );
            Student amir = new Student(
                    "Amir",
                    "amir@gmail.com",
                    LocalDate.of(1997, APRIL, 24)

            );

            repository.saveAll(
                    List.of(sofiane, amir)
            );
        };
    }
}
