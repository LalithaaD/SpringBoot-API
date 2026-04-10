package com.fdmgroup.Sprint4UserStory.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

//CustomerRequest is a DTO as it carries and validates input from the user before it reaches the service
public class CustomerRequest {

    //Implementing Attributes through validation
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street Number is required")
    private String streetNumber;

    @NotBlank(message = "Postal Code is required")
    private String postalCode;

    //not required for GeoCoder endpoint
    private String province;
    private String city;

    @NotBlank(message = "Customer type is required")
    private String customerType;

    //Default Constructor
    public CustomerRequest() {
    }

    //Constructor
    public CustomerRequest(String name, String streetNumber, String city, String province, String postalCode) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    //to string
    @Override
    public String toString() {
        return "CustomerRequest{" +
                "Name='" + name + '\'' +
                ", streetNumber=" + streetNumber +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    //HashCode and equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRequest that = (CustomerRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(streetNumber, that.streetNumber) && Objects.equals(city, that.city) && Objects.equals(province, that.province) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, streetNumber, city, province, postalCode);
    }
}
