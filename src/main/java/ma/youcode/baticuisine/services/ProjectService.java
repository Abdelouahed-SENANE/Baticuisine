package ma.youcode.baticuisine.services;

import ma.youcode.baticuisine.entities.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    UUID addProject(Project project);
    List<Project> getAllProjects();
}
