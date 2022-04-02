package hw2.domain;

import lombok.Data;


@Data
public class ProjectDTO {
    private int id;
    private String project_name;


    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.project_name = project.getProjectName();
    }
}
