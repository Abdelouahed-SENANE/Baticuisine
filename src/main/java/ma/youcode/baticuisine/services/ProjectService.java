package ma.youcode.baticuisine.services;

import ma.youcode.baticuisine.entities.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {

    UUID addProject(Project project);
    List<Project> getAllProjects();
    Optional<Project> getProjectById(UUID id);
    void updateProject(Project project);
    void deleteProject(UUID id);
}
