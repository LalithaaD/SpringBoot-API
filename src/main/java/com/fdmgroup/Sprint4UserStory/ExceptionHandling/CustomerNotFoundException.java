package com.fdmgroup.Sprint4UserStory.ExceptionHandling;

//This exception is thrown when a requested customer is not found in the database

public class CustomerNotFoundException extends RuntimeException {

    //Constructor
    //Passes the error message to the parent RuntimeException class

    public CustomerNotFoundException(String message) {
        super(message);
    }
}