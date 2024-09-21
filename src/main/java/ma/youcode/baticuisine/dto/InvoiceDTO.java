package ma.youcode.baticuisine.dto;

import ma.youcode.baticuisine.entities.Component;
import ma.youcode.baticuisine.entities.Material;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.entities.WorkForce;

import java.util.List;

public class InvoiceDTO {

    private String projectName;
    private String clientName;
    private String address;
    private Double surface;
    private Double amountHT;
    private List<Component> components;
    private Double amountTTC;
    private Double profitMargin;
    private Double amountWithProfit;
    private Double tax;
    private Double profitRate;
    private Double amountTax;
    private Double discountValue;
    private Double discountRate;
    private Double netAmout;
    public InvoiceDTO(Project project , Double amountHT , Double amountTTC  , Double amountWithProfit , Double profitMargin , Double amountTax , Double discountValue , Double netAmout) {
        this.projectName = project.getProjectName();
        this.clientName = project.getCustomer().getCustomerName();
        this.address = project.getCustomer().getAddress();
        this.surface = project.getSurface();
        this.components = project.getComponents();
        this.discountRate = project.getDiscount();
        this.discountValue = discountValue;
        this.getMaterials();
        this.getWorkForces();
        this.amountHT = amountHT;
        this.amountTTC = amountTTC;
        this.profitMargin = profitMargin ;
        this.amountWithProfit = amountWithProfit;
        this.netAmout = netAmout;
        this.amountTax = amountTax;
        this.profitRate = project.getProfitMargin();
        this.tax = project.getComponents().get(0).getVat();
    }

    public Double getNetAmout() {
        return netAmout;
    }

    public List<MaterialDTO> getMaterials() {
        return components.stream()
                .filter(component -> component instanceof Material)
                .map(component -> new MaterialDTO((Material) component))
                .toList();
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public List<WorkForceDTO> getWorkForces() {
        return components.stream()
                .filter(component -> component instanceof WorkForce)
                .map(component -> new WorkForceDTO((WorkForce) component))
                .toList();
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public Double getTax() {
        return tax;
    }

    public Double getAmountWithTax() {
        return amountTax;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public Double getSurface() {
        return surface;
    }


    public Double getAmountHT() {
        return amountHT;
    }

    public Double getAmountTTC() {
        return amountTTC;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public Double getAmountWithProfit() {
        return amountWithProfit;
    }

}
