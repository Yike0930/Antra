package hw2.repository;

import hw2.domain.Project;
import hw2.domain.Student;
import hw2.domain.Student_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();

    Project findProjectById(int id);

    List<Student_Project> findStudent_ProjectById(int id);

    Project saveAndFlush(Project project);
}
