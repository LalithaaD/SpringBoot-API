package com.fdmgroup.Sprint4UserStory.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SAVINGS_ACCOUNT")
public class SavingsAccount extends Account{
    private double interestRate;

    //Default Constructor
    public SavingsAccount(){
    }

    // Constructor with all fields
    public SavingsAccount(double balance, Customer customer, double interestRate) {
        super(balance, customer);
        this.interestRate = interestRate;
    }

    //Getters and Setters
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
