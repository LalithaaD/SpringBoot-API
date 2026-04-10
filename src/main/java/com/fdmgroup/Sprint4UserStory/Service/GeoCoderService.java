package com.fdmgroup.Sprint4UserStory.Service;

import com.fdmgroup.Sprint4UserStory.ExceptionHandling.GeoCoderException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
    //GeoCoderService handles communication with the external geocoder.ca API
    //Responsible for resolving city and province from a given postal code
public class GeoCoderService {

    //WebClient
    private final WebClient webClient;

    //Constructor to initialize webclient with the baseURL of the API
    public GeoCoderService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://geocoder.ca").build();
    }

    //Method that calls the API and returns the city and province
    public String[] getCityAndProvince(String postalCode) {

        //Remove spaces and convert to uppercase
        String cleanPostalCode = postalCode.replace(" ", "").toUpperCase();

        //Send a GET request to the API with the postal code, the API returns a JSON response,
        // which we convert into a Map and then access the data

        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder

                        //Adding / at the end of URL Inorder to attach the query parameters, as it is the root URL
                        .path("/")

                        //postal code we want to resolve
                        .queryParam("postal", cleanPostalCode)

                        //API authentication key
                        .queryParam("auth", "360966353527850708237x214750980")

                        //request the response in JSON format
                        .queryParam("json", "1")

                        //whole URL
                        .build())

                        //send the http req and get the response
                        .retrieve()

                        //converting JSON response to java map
                        .bodyToMono(Map.class)

                        //block the thread and wait until the response arrives
                        .block();


        //Response Validation

        //If API returned nothing, throw an exception, handled by GeoCoder Exception
        if (response == null) {
            throw new GeoCoderException("No response received from geocoder.ca for postal code: " + postalCode);
        }

        //The city and province are inside a nested object called Standard
        Map<String, Object> standard = (Map<String, Object>) response.get("standard");

        //If something went wrong with the GeoCoder API, handled by Global Exception Handler
        if (standard == null || standard.get("city") == null || standard.get("prov") == null) {
            throw new GeoCoderException("Could not resolve location for postal code: " + postalCode);
        }
        //return city and province
        return new String[]{
                (String) standard.get("city"),
                (String) standard.get("prov")
        };
    }
}