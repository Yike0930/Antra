package hw2.controller;

import hw2.domain.Student_ProjectDTO;
import hw2.service.Student_ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Student_ProjectController {
    private final Student_ProjectService student_projectService;

    @Autowired
    public Student_ProjectController(Student_ProjectService student_projectService) {
        this.student_projectService = student_projectService;
    }

    @GetMapping("/student_project")
    public ResponseEntity<List<Student_ProjectDTO>> getStudentProject() {
        return new ResponseEntity<>(student_projectService.getAllStudent_Project(),HttpStatus.OK);
    }

    @PostMapping("/student_project/{stu_id}/{pro_id}")
    public ResponseEntity<Student_ProjectDTO> saveProjectToStudent(@PathVariable int stu_id, @PathVariable int pro_id) {
        return new ResponseEntity<>(student_projectService.saveProjectToStudent(stu_id, pro_id), HttpStatus.CREATED);
    }

}
