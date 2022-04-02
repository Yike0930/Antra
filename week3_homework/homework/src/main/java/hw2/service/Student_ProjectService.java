package hw2.service;

import hw2.domain.Student_ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Student_ProjectService {
    List<Student_ProjectDTO> getAllStudent_Project();
    Student_ProjectDTO saveProjectToStudent(int stu_id, int pro_id);
}
