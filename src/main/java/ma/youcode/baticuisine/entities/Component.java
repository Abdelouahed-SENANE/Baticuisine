package ma.youcode.baticuisine.entities;

import ma.youcode.baticuisine.enums.ComponentType;

import java.util.UUID;

public abstract class Component {

    private UUID componentId;
    private String componentName;
    private Project project;
    private Double vat;
    private ComponentType componentType;
    public Component(){}
    public UUID getComponentId() {
        return componentId;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Project getProject() {
        return project;
    }
    public Double getVat() {
        return vat;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentId(UUID componentId) {
        this.componentId = componentId;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }




}
