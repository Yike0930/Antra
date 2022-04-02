package hw2.domain;

import lombok.Data;

@Data
public class Student_ProjectDTO {
    private int id;
    private String studentName;
    private String projectName;

    public Student_ProjectDTO(Student_Project student_project) {
        this.id = student_project.getId();
        this.studentName = student_project.getStu().getName();
        this.projectName = student_project.getProject().getProjectName();
    }

}
