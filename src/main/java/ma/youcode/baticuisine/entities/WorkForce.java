package ma.youcode.baticuisine.entities;

public class WorkForce extends Component{

    private Double hourlyRate;
    private Integer workHours;
    private Double workerProductivityCoefficient;

    public Integer getWorkHours() {
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

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
