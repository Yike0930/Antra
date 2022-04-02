//package entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name="student_project")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Student_Project {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "s_id")
//    private Student stu;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "p_id")
//    private Project project;
//
//    public Student_Project(Student stu, Project project) {
//        this.stu = stu;
//        this.project = project;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student_Project that = (Student_Project) o;
//        return Objects.equals(id, that.id) &&
//                Objects.equals(stu, that.stu) &&
//                Objects.equals(project, that.project);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, stu, project);
//    }
//
////    public Student getStu() {
////        return stu;
////    }
////
////    public void setStu(Student stu) {
////        this.stu = stu;
////    }
////
////    public Project getProject() {
////        return project;
////    }
////
////    public void setProject(Project project) {
////        this.project = project;
////    }
//
//
//}
