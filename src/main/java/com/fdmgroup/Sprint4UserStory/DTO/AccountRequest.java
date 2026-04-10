package com.fdmgroup.Sprint4UserStory.DTO;


//AccountRequest is a DTO as it carries and validates input from the user before it reaches the service

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRequest {
    //to find the acc balance
    @NotNull(message = "Balance is required")
    private Double balance;

    //to determine the acc type
    @NotBlank(message = "Account Type is required")
    private String accountType;

    //Extra field for SavingsAccount only
    private Double interestRate;

    //Extra field for CheckingAccount only
    private Integer nextCheckNumber;

    // Default Constructor
    public AccountRequest() {
    }

    //Constructor
    public AccountRequest(Double balance, String accountType, Double interestRate, Integer nextCheckNumber) {
        this.balance = balance;
        this.accountType = accountType;
        this.interestRate = interestRate;
        this.nextCheckNumber = nextCheckNumber;
    }

    //Getters and Setters
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getNextCheckNumber() {
        return nextCheckNumber;
    }

    public void setNextCheckNumber(Integer nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }

    //To String
    @Override
    public String toString() {
        return "AccountRequest{" +
                "balance=" + balance +
                ", accountType=" + accountType +
                ", interestRate=" + interestRate +
                ", nextCheckNumber=" + nextCheckNumber +
                '}';
    }
}
