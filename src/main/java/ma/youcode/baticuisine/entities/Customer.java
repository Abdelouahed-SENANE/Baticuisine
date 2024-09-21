package ma.youcode.baticuisine.entities;

import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID customerId;
    private String customerName;
    private String address;
    private String phone;
    private Boolean isProfessional;
    private List<Project> projects;


    public Customer(){}

    public Boolean getProfessional() {
        return isProfessional;
    }

    public String getAddress() {
        return address;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfessional(Boolean professional) {
        isProfessional = professional;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
