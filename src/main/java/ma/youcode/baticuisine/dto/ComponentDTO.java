package ma.youcode.baticuisine.dto;

public abstract class ComponentDTO {
    private String componentName;

    public ComponentDTO(String componentName) {
        this.componentName = componentName;
    }

    public String getName() {
        return componentName;
    }
    public abstract Double getAmountHT();
}
