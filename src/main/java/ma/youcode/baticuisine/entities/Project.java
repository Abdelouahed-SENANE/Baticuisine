package ma.youcode.baticuisine.entities;

import ma.youcode.baticuisine.enums.ProjectStatus;

public class Project {
    private String projectId;
    private String projectName;
    private Double profitMargin;
    private Double overallCost;
    private ProjectStatus projectStatus;

    public String getProjectId() {
        return projectId;
    }

    public Double getOverallCost() {
        return overallCost;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setOverallCost(Double overallCost) {
        this.overallCost = overallCost;
    }

    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
