package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Component;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.entities.WorkForce;
import ma.youcode.baticuisine.services.WorkForceService;

public class WorkForceServiceImp implements WorkForceService {

    @Override
    public Double calculateCostForWorkForceHT(Project project) {
        return project.getComponents().stream()
                .filter(component -> component instanceof WorkForce)
                .map(component -> (WorkForce) component)
                .mapToDouble(workForce -> workForce.getWorkHours() * workForce.getHourlyRate() * workForce.getWorkerProductivityCoefficient())
                .sum();
    }

}
