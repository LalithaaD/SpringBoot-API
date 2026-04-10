package com.fdmgroup.Sprint4UserStory.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//DiscriminatorValue inherits everything from Customer. When a Company  is saved, Hibernate puts COMPANY in the CUSTOMER_TYPE column.
@DiscriminatorValue("COMPANY")
public class Company extends Customer{
    //Default Constructor
    public Company() {
    }

    //Constructor with all inherited attributes except ID
    public Company(String name, Address address) {
        super(name, address);
    }
}
