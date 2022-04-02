package hw2.service.impl;

import hw2.domain.ProjectDTO;
import hw2.domain.Student;
import hw2.domain.StudentDTO;
import hw2.exception.StudentAlreadyExistException;
import hw2.exception.StudentNotFoundException;
import hw2.repository.impl.StudentRepo;
import hw2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    //inject bean
    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public StudentDTO getStudentById(int id) {
        Student stu = studentRepo.getStudentById(id);
        if(stu == null) {
            throw new StudentNotFoundException("we cannot find id " + id);
        }
        return new StudentDTO(stu);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = studentRepo.getAllStudents()
                .stream().map(s -> new StudentDTO(s))
                .collect(Collectors.toList());
        return students;

    }

    @Override
    public List<ProjectDTO> getProjectsByStudentId(int id) {
        Student stu = studentRepo.getStudentById(id);
        if(stu == null) {
            throw new StudentNotFoundException("we cannot find id " + id);
        }
        return studentRepo.getProjectsByStudentId(id)
                .stream().map(p -> new ProjectDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO saveStudent(Student student) {
        if (studentRepo.getStudentById(student.getId()) != null){
            throw new StudentAlreadyExistException("Student already exist!");
        }
        studentRepo.saveStudent(student);
        System.out.println("student id: " + student.getId() + ", name: " + student.getName());
        return new StudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(int id, String newName) {
        Student stu = studentRepo.getStudentById(id);
        if(stu == null) {
            throw new StudentNotFoundException("we cannot find id " + id);
        }
        return new StudentDTO(studentRepo.updateStudent(id, newName));
    }
}
