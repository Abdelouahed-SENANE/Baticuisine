package ma.youcode.baticuisine.repositories;

import ma.youcode.baticuisine.entities.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

    UUID save(Project project);
    List<Project> findAll();
    Optional<Project> findById(UUID id);
    void delete(UUID id);
    void update(Project project);
}
