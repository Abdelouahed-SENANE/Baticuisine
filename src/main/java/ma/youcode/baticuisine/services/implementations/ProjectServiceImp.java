package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Component;
import ma.youcode.baticuisine.entities.Material;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.entities.WorkForce;
import ma.youcode.baticuisine.repositories.MaterialRepository;
import ma.youcode.baticuisine.repositories.ProjectRepository;
import ma.youcode.baticuisine.repositories.WorkForceRepository;
import ma.youcode.baticuisine.repositories.implementations.MaterialRepositoryimp;
import ma.youcode.baticuisine.repositories.implementations.ProjectRepositoryImp;
import ma.youcode.baticuisine.repositories.implementations.WorkForceRepositoryImp;
import ma.youcode.baticuisine.services.ProjectService;

import java.util.UUID;

public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MaterialRepository materialRepository;
    private final WorkForceRepository workForceRepository;
    public ProjectServiceImp() {
        projectRepository = new ProjectRepositoryImp();
        materialRepository = new MaterialRepositoryimp();
        workForceRepository = new WorkForceRepositoryImp();
    }

    @Override
    public void addProject(Project project) {
        UUID projectId = projectRepository.save(project);
        project.setProjectId(projectId);

        for (Component component : project.getComponents()) {
            component.setProject(project);
            if (component instanceof Material) {
                materialRepository.save((Material) component);
            }else {
                workForceRepository.save((WorkForce) component);
            }
        }



    }
}
