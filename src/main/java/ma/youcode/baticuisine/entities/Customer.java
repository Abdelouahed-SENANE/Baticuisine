package ma.youcode.baticuisine.entities;

import java.util.List;

public class Customer {

    private String customerId;
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

    public String getCustomerId() {
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

    public void setCustomerId(String customerId) {
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
