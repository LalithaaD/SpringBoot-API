package com.fdmgroup.Sprint4UserStory.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Address {
    //Implementing Attributes, and Validation for each column in the customer entity table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer addressId;

    @NotBlank(message = "Street Number is required")
    private String streetNumber;

    @NotBlank(message = "Postal Code is required")
    private String postalCode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    //Default Constructor
    public Address() {
    }

    //Constructor with all attributes except ID
    public Address(String streetNumber, String postalCode, String city, String province) {
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    //Getters and Setters
    public Integer getAddressId() {
        return addressId;
    }

    // No setter for Address

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetNumber='" + streetNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

}
