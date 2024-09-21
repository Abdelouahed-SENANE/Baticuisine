package ma.youcode.baticuisine.dto;

import ma.youcode.baticuisine.entities.WorkForce;

public class WorkForceDTO extends ComponentDTO {
    private Double hourlyRate;
    private Double workHours;
    private Double workerProductivityCoefficient;
    private String workForceType;

    public WorkForceDTO(WorkForce workForce) {
        super(workForce.getComponentName());
        this.hourlyRate = workForce.getHourlyRate();
        this.workHours = workForce.getWorkHours();
        this.workerProductivityCoefficient = workForce.getWorkerProductivityCoefficient();
        this.workForceType = workForce.getWorkForceType().name();  // Converting enum to String
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public Double getWorkHours() {
        return workHours;
    }

    public Double getWorkerProductivityCoefficient() {
        return workerProductivityCoefficient;
    }

    public String getWorkForceType() {
        return workForceType;
    }

    @Override
    public Double getAmountHT() {
        return workHours * hourlyRate * workerProductivityCoefficient;
    }
}
