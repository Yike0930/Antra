package hw2.service;


import hw2.domain.Project;
import hw2.domain.ProjectDTO;
import hw2.domain.Student;
import hw2.domain.StudentDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentService {

    StudentDTO getStudentById(int id);
    List<StudentDTO> getAllStudents();
    List<ProjectDTO> getProjectsByStudentId(int id);
    StudentDTO saveStudent(Student student);
    StudentDTO updateStudent(int id, String name);
}
