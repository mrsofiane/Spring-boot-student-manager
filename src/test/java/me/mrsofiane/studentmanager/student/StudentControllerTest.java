package me.mrsofiane.studentmanager.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mrsofiane.studentmanager.exception.ApiRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;


    @Test
    void getStudentById() {
        Student student = new Student();
        student.setName("sofiane");
        student.setEmail("sofiane@gmail.com");
        student.setDob(LocalDate.of(1997, MARCH,07));

        when(studentService.getStudentById(anyLong())).thenReturn(student);

        assertEquals("sofiane",studentService.getStudentById(anyLong()).getName());


    }

    @Test
    void getStudents() {
        List<Student> students = Arrays.asList(
                new Student("sofiane","sofianelilou@gmail.com",LocalDate.of(1997,MARCH,07)),
                new Student("amir","amir@gmail.com",LocalDate.of(1997,MAY,07)));

        when(studentService.getStudents()).thenReturn(students);

        assertEquals(2,studentService.getStudents().size());

    }

    @Test
    void registerNewStudent() {
        Student student = new Student();
        student.setName("sofiane");
        student.setEmail("sofiane@gmail.com");
        student.setDob(LocalDate.of(1997, MARCH,07));

        when(studentService.addNewStudent(student)).thenReturn(1);

        assertEquals(1,studentService.addNewStudent(student));

        // if it's void use this
        //verify(studentService,times(1)).addNewStudent(student);

    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        student.setName("sofiane");
        student.setEmail("sofiane@gmail.com");
        student.setDob(LocalDate.of(1997, MARCH,07));

        studentService.deleteStudentById(anyLong());

        verify(studentService, times(1)).deleteStudentById(anyLong());
    }

    @Test
    void updateStudent() {
        Student student = new Student();
        student.setName("sofiane");
        student.setEmail("sofiane@gmail.com");
        student.setDob(LocalDate.of(1997, MARCH,07));

        when(studentService.updateStudent(anyLong(),anyString(),anyString())).thenReturn(1);

        assertEquals(1, studentService.updateStudent(anyLong(), anyString(), anyString()));

    }
}