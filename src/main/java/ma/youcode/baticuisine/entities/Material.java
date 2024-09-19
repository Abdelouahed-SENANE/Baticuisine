package ma.youcode.baticuisine.entities;

public class Material extends  Component{

    private Double pricePerUnit;
    private Double quantity;
    private Double transportationCost;
    private Double qualityCoefficient;
    public Material(){}


    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public Double getTransportationCost() {
        return transportationCost;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setQualityCoefficient(Double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setTransportationCost(Double transportationCost) {
        this.transportationCost = transportationCost;
    }
}
