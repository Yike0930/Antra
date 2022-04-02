package hw2.controller;

import hw2.domain.Project;
import hw2.domain.ProjectDTO;
import hw2.domain.StudentDTO;
import hw2.exception.ProjectAlreadyExistException;
import hw2.exception.ProjectNotFoundException;
import hw2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(),HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int id) {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByProjectId(@PathVariable int id) {
        return new ResponseEntity<>(projectService.getStudentsByProjectId(id),HttpStatus.OK);
    }

    @PostMapping("/project/{projectName}")
    public ResponseEntity<ProjectDTO> saveProject(@PathVariable String projectName) {
        Project project = new Project();
//        project.setId(id);
        project.setProjectName(projectName);
        return new ResponseEntity<>(projectService.saveProject(project),HttpStatus.CREATED);
    }

    @PutMapping("/project/{id}/{newProjectName}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable int id, @PathVariable String newProjectName) {
        return new ResponseEntity<>(projectService.updateProject(id, newProjectName),HttpStatus.OK);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler() {
        return new ResponseEntity<>("we cannot find this project", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectAlreadyExistException.class)
    public ResponseEntity<?> alreadyExistExceptionHandler() {
        return new ResponseEntity<>("Project already exist!", HttpStatus.NOT_ACCEPTABLE);
    }


}
