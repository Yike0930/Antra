package hw2.service;

import hw2.domain.Project;
import hw2.domain.ProjectDTO;
import hw2.domain.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    ProjectDTO getProjectById(int id);
    List<ProjectDTO> getAllProjects();
    List<StudentDTO> getStudentsByProjectId(int id);
    ProjectDTO saveProject(Project project);
    ProjectDTO updateProject(int id, String newProjectName);
}
