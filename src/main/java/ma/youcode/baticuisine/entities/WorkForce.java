package ma.youcode.baticuisine.entities;

import ma.youcode.baticuisine.enums.WorkForceType;

public class WorkForce extends Component{

    private Double hourlyRate;
    private Double workHours;
    private Double workerProductivityCoefficient;
    private WorkForceType workForceType;
    public WorkForce(){}

    public Double getWorkHours() {
        return workHours;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public Double getWorkerProductivityCoefficient() {
        return workerProductivityCoefficient;
    }

    public void setWorkerProductivityCoefficient(Double workerProductivityCoefficient) {
        this.workerProductivityCoefficient = workerProductivityCoefficient;
    }

    public void setWorkHours(Double workHours) {
        this.workHours = workHours;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public WorkForceType getWorkForceType() {
        return workForceType;
    }

    public void setWorkForceType(WorkForceType workForceType) {
        this.workForceType = workForceType;
    }
}
