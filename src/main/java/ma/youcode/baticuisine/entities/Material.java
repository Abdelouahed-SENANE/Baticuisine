package ma.youcode.baticuisine.entities;

public class Material extends  Component{

    private Double pricePerUnit;
    private Integer quantity;
    private Double transportationCost;
    private Double qualityCoefficient;


    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public Double getTransportationCost() {
        return transportationCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setQualityCoefficient(Double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTransportationCost(Double transportationCost) {
        this.transportationCost = transportationCost;
    }
}
