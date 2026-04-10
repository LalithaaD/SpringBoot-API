package com.fdmgroup.Sprint4UserStory.ExceptionHandling;

//This exception is thrown when geocoder.ca fails or returns invalid data

public class GeoCoderException extends RuntimeException{

    //Constructor
    //Passes the error message to the parent RuntimeException class

    public GeoCoderException(String message){
        super(message);
    }
}
