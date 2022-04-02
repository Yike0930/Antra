package hw2.repository.impl;

import hw2.domain.Project;
import hw2.domain.Student;
import hw2.domain.StudentDTO;
import hw2.domain.Student_Project;
//import hw2.repository.StudentRepository;
import hw2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepo {

    @Autowired
    private StudentRepository studentRepository;

    private final Map<Integer, Student> studentMap = new HashMap<>();

//    public StudentRepo() {
//        studentMap.put(1, new Student(1, "Tom", new ArrayList<Student_Project>()));
//        studentMap.put(2, new Student(2, "Alex", new ArrayList<Student_Project>()));
//    }

    public Student getStudentById(int id) {
        return studentRepository.findStudentById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
//        return studentMap.values();
    }

    public List<Project> getProjectsByStudentId(int id) {

        return studentRepository.findStudent_ProjectById(id).stream().map((x) -> x.getProject()).collect(Collectors.toList());
//        return studentMap.get(id).getStudent_Projects()
//                .stream().map((x) -> x.getProject()).collect(Collectors.toList());
    }

    public Student saveStudent(Student student) {
//        studentMap.put(student.getId(), student);
//        return student;
        return studentRepository.saveAndFlush(student);
    }

    public Student updateStudent(int id, String newName) {
        Student stu = studentRepository.findStudentById(id);
        stu.setName(newName);
//        studentMap.put(id,stu);
        return studentRepository.saveAndFlush(stu);
    }

}
