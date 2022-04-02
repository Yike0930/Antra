package hw2.repository.impl;

import hw2.domain.Project;
import hw2.domain.Student;
//import hw2.repository.ProjectRepository;
import hw2.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class ProjectRepo {
    private final Map<Integer, Project> projectMap = new HashMap<>();

//    public ProjectRepo() {
//        projectMap.put(1, new Project(1, "JPA"));
//        projectMap.put(2, new Project(2, "SpringBoot"));
//    }

    @Autowired
    private ProjectRepository projectRepository;

    public Collection<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findProjectById(id);
    }

    public List<Student> getStudentsByProjectId(int id) {
        return projectRepository.findStudent_ProjectById(id)
                .stream().map((x) -> x.getStu()).collect(Collectors.toList());
    }

    public Project saveProject(Project project) {
//        projectMap.put(project.getId(), project);
        projectRepository.saveAndFlush(project);
        return project;
    }

    public Project updateProject(int id, String newProjectName) {
        Project pro = projectRepository.findProjectById(id);
        pro.setProjectName(newProjectName);
        projectRepository.saveAndFlush(pro);
        return pro;
    }


}
