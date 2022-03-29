package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String projectName;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Student_Project> student_projects = new ArrayList<>();

    public List<Student_Project> getStudent_Projects() {
        return student_projects;
    }

    public void setStudent_Projects(List<Student_Project> student_projects) {
        this.student_projects = student_projects;
    }

    public void addStudent_Projects(Student_Project sp) {
        this.student_projects.add(sp);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getProjectName() {
//        return projectName;
//    }
//
//    public void setProjectName(String projectName) {
//        this.projectName = projectName;
//    }
}
