package com.fdmgroup.Sprint4UserStory.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHECKING_ACCOUNT")
public class CheckingAccount extends Account{

    private int nextCheckNumber;

    //Default Constructor
    public CheckingAccount(){
    }

    //Constructor with all fields
    public CheckingAccount(double balance, Customer customer, int nextCheckNumber) {
        super(balance, customer);
        this.nextCheckNumber = nextCheckNumber;
    }

    //Getters and Setters

    public int getNextCheckNumber() {
        return nextCheckNumber;
    }

    public void setNextCheckNumber(int nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }
}
