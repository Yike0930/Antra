package hw2.repository.impl;

import hw2.domain.Project;
import hw2.domain.Student;
import hw2.domain.Student_Project;
import hw2.repository.StudentRepository;
import hw2.repository.Student_ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Student_ProjectRepo {

//    private final Map<Integer, Student_Project> student_projectMap= new HashMap<>();
    private StudentRepo studentRepo;
    private ProjectRepo projectRepo;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Autowired
    public Student_ProjectRepo(StudentRepo studentRepo, ProjectRepo projectRepo) {
        this.studentRepo = studentRepo;
        this.projectRepo = projectRepo;
    }

    public Student_Project saveProjectToStudent(int stu_id, int pro_id) {
        Student_Project student_project = new Student_Project(studentRepo.getStudentById(stu_id), projectRepo.getProjectById(pro_id));
//        student_project.setId(id);
//        student_projectMap.put(id, student_project);
//        studentRepo.getStudentById(stu_id).getStudent_Projects().add(student_project);
        student_projectRepository.saveAndFlush(student_project);
        return student_project;
    }

    public Collection<Student_Project> getAllStudent_Project() {
        return student_projectRepository.findAll();
    }

}
