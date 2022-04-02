package hw2.service.impl;

import hw2.domain.Project;
import hw2.domain.ProjectDTO;
import hw2.domain.StudentDTO;
import hw2.exception.ProjectAlreadyExistException;
import hw2.exception.ProjectNotFoundException;
import hw2.repository.impl.ProjectRepo;
import hw2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepo.getAllProjects().stream().map(p -> new ProjectDTO(p)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(int id) {
        Project project = projectRepo.getProjectById(id);
        if(project == null) {
            throw new ProjectNotFoundException("we cannot find id " + id);
        }
        return new ProjectDTO(project);
    }

    @Override
    public List<StudentDTO> getStudentsByProjectId(int id) {
        Project pro = projectRepo.getProjectById(id);
        if(pro == null) {
            throw new ProjectNotFoundException("we cannot find id " + id);
        }
        return projectRepo.getStudentsByProjectId(id).stream().map(s -> new StudentDTO(s))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO saveProject(Project project) {
        if (projectRepo.getProjectById(project.getId()) != null) {
            throw new ProjectAlreadyExistException("Project already exist!");
        }
        projectRepo.saveProject(project);
        return new ProjectDTO(project);
    }

    @Override
    public ProjectDTO updateProject(int id, String newProjectName) {
        Project pro = projectRepo.getProjectById(id);
        if(pro == null) {
            throw new ProjectNotFoundException("we cannot find id " + id);
        }

        return new ProjectDTO(projectRepo.updateProject(id, newProjectName));
    }
}
