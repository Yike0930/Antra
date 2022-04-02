package hw2.repository;

import hw2.domain.Project;
import hw2.domain.Student_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student_ProjectRepository extends JpaRepository<Student_Project, Integer> {

    List<Student_Project> findAll();

    Student_Project saveAndFlush(Student_Project student_project);
}
