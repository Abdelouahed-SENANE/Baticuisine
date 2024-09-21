package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Component;
import ma.youcode.baticuisine.entities.Material;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.entities.WorkForce;
import ma.youcode.baticuisine.services.MaterialService;

public class MaterialServiceImp implements MaterialService {
    @Override
    public Double caculateCostMaterialsHT(Project project) {
        return project.getComponents().stream()
                .filter(component -> component instanceof Material)
                .map(component -> (Material) component)
                .mapToDouble(material -> material.getQuantity() * material.getPricePerUnit() * material.getQualityCoefficient())
                .sum();
    }

}
