package com.fdmgroup.Sprint4UserStory.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//DiscriminatorValue inherits everything from Customer. When a Person is saved, Hibernate puts PERSON in the CUSTOMER_TYPE column.
@DiscriminatorValue("PERSON")
public class Person extends Customer{
    //Default Constructor
    public Person() {
    }
    //Constructor with all inherited attributes except ID
    public Person(String name, Address address) {
        super(name, address);
    }

}
