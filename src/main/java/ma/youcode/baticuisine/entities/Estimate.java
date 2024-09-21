package ma.youcode.baticuisine.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Estimate {

    private UUID estimateId;
    private LocalDate issueAt;
    private LocalDate validateAt ;
    private Boolean isAccepted;
    private Project project;
    public Estimate(){}

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public LocalDate getIssueAt() {
        return issueAt;
    }

    public LocalDate getValidateAt() {
        return validateAt;
    }

    public UUID getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(UUID estimateId) {
        this.estimateId = estimateId;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public void setValidateAt(LocalDate validateAt) {
        this.validateAt = validateAt;
    }

    public void setIssueAt(LocalDate issueAt) {
        this.issueAt = issueAt;
    }
}
