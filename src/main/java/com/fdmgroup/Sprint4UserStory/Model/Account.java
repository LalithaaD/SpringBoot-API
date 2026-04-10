package com.fdmgroup.Sprint4UserStory.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountId")
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private double balance;

    //Many accounts can belong to one customer
    //Acc owns the foreign key FK_CUST_ID
    @ManyToOne
    @JoinColumn(name = "FK_CUST_ID")
    private Customer customer;

    //Default Constructor
    public Account() {
    }

    // Constructor with all fields
    public Account(double balance, Customer customer) {
        this.balance = balance;
        this.customer = customer;
    }

    //Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //To string
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }
}
