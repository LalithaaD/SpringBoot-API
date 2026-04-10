package com.fdmgroup.Sprint4UserStory.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CUSTOMER_TYPE")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerID")
public abstract class Customer {

    //Implementing Attributes, and Validation for each column in the customer entity table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;

    @NotBlank(message = "Name is required")
    private String name;


    //Customer owns the foreign key to address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    // One customer can have many accounts
    // mappedBy = "customer" means Account owns the foreign key
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    // JsonIgnore breaks the circular reference - Customer no longer serializes its accounts list
    @JsonIgnore
    private List<Account> accounts;

    //Default Constructor
    public Customer() {
    }

    //Constructor with all attributes except ID
    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    //Getters and Setters
    public Integer getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    //toString method
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                '}';
    }
}