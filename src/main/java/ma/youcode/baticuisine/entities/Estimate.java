package ma.youcode.baticuisine.entities;

import java.time.LocalDateTime;

public class Estimate {

    private String estimateId;
    private LocalDateTime issueAt;
    private LocalDateTime validateDate ;
    private Boolean isAccepted;

    public Boolean getAccepted() {
        return isAccepted;
    }

    public LocalDateTime getIssueAt() {
        return issueAt;
    }

    public LocalDateTime getValidateDate() {
        return validateDate;
    }

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public void setValidateDate(LocalDateTime validateDate) {
        this.validateDate = validateDate;
    }

    public void setIssueAt(LocalDateTime issueAt) {
        this.issueAt = issueAt;
    }
}
