package ma.youcode.baticuisine.entities;

public abstract class Component {

    private String componentId;
    private String componentName;
    private Double vat;

    public String getComponentId() {
        return componentId;
    }

    public Double getVat() {
        return vat;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }
}
