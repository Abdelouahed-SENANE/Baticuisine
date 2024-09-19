package ma.youcode.baticuisine.repositories;

import ma.youcode.baticuisine.entities.Project;

import java.util.UUID;

public interface ProjectRepository {

    UUID save(Project project);

}
