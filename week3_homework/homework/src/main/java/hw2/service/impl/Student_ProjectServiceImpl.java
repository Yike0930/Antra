package hw2.service.impl;

import hw2.domain.Student_Project;
import hw2.domain.Student_ProjectDTO;
import hw2.repository.impl.Student_ProjectRepo;
import hw2.service.Student_ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Student_ProjectServiceImpl implements Student_ProjectService {

    private Student_ProjectRepo student_projectRepo;

    @Autowired
    public Student_ProjectServiceImpl(Student_ProjectRepo student_projectRepo) {
        this.student_projectRepo = student_projectRepo;
    }

    @Override
    public List<Student_ProjectDTO> getAllStudent_Project() {
        return student_projectRepo.getAllStudent_Project()
                .stream().map(sp -> new Student_ProjectDTO(sp))
                .collect(Collectors.toList());
    }

    @Override
    public Student_ProjectDTO saveProjectToStudent(int stu_id, int pro_id) {
        return new Student_ProjectDTO(student_projectRepo.saveProjectToStudent(stu_id,pro_id));
    }

}
