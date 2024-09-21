package ma.youcode.baticuisine.dto;

import ma.youcode.baticuisine.entities.Material;

public class MaterialDTO extends ComponentDTO{
    private Double quantity;
    private Double priceUnit;
    private Double quality;
    private Double  transportCost;

    public MaterialDTO(Material material) {
        super(material.getComponentName());
        this.quantity = material.getQuantity();
        this.priceUnit = material.getPricePerUnit();
        this.quality = material.getQualityCoefficient();
        this.transportCost = material.getTransportationCost();
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public Double getQuality() {
        return quality;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getTransportCost() {
        return transportCost;
    }

    @Override
    public Double getAmountHT() {
        return quality * quantity * priceUnit + transportCost;
    }
}
