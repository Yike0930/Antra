package hw2.repository;

import hw2.domain.Student;
import hw2.domain.Student_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    @Query("select * from Student")
    List<Student> findAll();

    Student findStudentById(int id);

    List<Student_Project> findStudent_ProjectById(int id);

    Student saveAndFlush(Student student);
//
//    List<Project> getProjectsByStudentId(int id);
//
//    Student saveStudent(Student student);
//
//    Student updateStudent(int id, String newName);
}
