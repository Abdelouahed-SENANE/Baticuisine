package ma.youcode.baticuisine.entities;

import ma.youcode.baticuisine.enums.ProjectStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
    private UUID projectId;
    private String projectName;
    private Double profitMargin;
    private ProjectStatus projectStatus;
    private Customer customer;
    private Double surface;
    private List<Component> components = new ArrayList<>();
    private Double discount;
    private Estimate estimate;
    public Project(){}

    public Double getDiscount() {
        return discount;
    }

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getSurface() {
        return surface;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }
    public List<Component> getComponents() {
        return components;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
