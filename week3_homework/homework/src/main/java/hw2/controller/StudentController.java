package hw2.controller;


//import hw2.service.StudentService;



import hw2.domain.ProjectDTO;
import hw2.domain.Student;
import hw2.domain.StudentDTO;
import hw2.exception.StudentAlreadyExistException;
import hw2.exception.StudentNotFoundException;
import hw2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @GetMapping("/student/{id}/projects")
    public ResponseEntity<List<ProjectDTO>> getProjectsByStudentId(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getProjectsByStudentId(id),HttpStatus.OK);
    }

    @PostMapping("/student/{name}")
    public ResponseEntity<StudentDTO> saveStudent(@PathVariable String name) {
        Student student = new Student();
//        student.setId(id);
        student.setName(name);
        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}/{newName}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @PathVariable String newName) {
        return new ResponseEntity<>(studentService.updateStudent(id, newName),HttpStatus.OK);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler() {
        return new ResponseEntity<>("we cannot find this student", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<?> alreadyExistExceptionHandler() {
        return new ResponseEntity<>("Student already exist!", HttpStatus.NOT_ACCEPTABLE);
    }
}
