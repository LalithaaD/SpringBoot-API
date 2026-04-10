package com.fdmgroup.Sprint4UserStory.Controller;

import com.fdmgroup.Sprint4UserStory.DTO.CustomerRequest;
import com.fdmgroup.Sprint4UserStory.Model.*;
import com.fdmgroup.Sprint4UserStory.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//REST Controller handles HTTP requests related to Customer operations
@RestController
@RequestMapping("/customers")
public class CustomerController {

    //Creating Service object to access business logic
    private final CustomerService customerService;

    //Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //CreateCustomer API
    @Operation(summary = "Create a new customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody CustomerRequest req) {
        //Build address obj from request
        Address address = new Address(req.getStreetNumber(),
                req.getPostalCode(), req.getCity(), req.getProvince());

        //Checking customer type and creating the correct customer object
        Customer customer = createCustomerByType(req, address);

        return customerService.createCustomer(customer);
    }

    //Helper method to create the correct customer object based on customer type
    private Customer createCustomerByType(CustomerRequest req, Address address) {

        Customer customer = null;

        if (req.getCustomerType().equalsIgnoreCase("person")) {
            customer = new Person(req.getName(), address);
        }
        else if (req.getCustomerType().equalsIgnoreCase("company")) {
            customer = new Company(req.getName(), address);
        }
        else {
            throw new IllegalArgumentException("Customer type must be person or company");
        }

        return customer;
    }

    //CreateCustomerwithGeocoder API
    //Consumer only needs to provide name, streetNumber and postalCode
    //Automatically fills city and province from postal code using geocoder.ca
    @Operation(summary = "Create a new customer and auto-fill city and province using postal code by Geocoder")
    @PostMapping("/geocoder")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomerWithGeocoder(@Valid @RequestBody CustomerRequest req) {
        //Build Address with only streetNumber and PostalCode, Geocoder fills city and province
        Address address = new Address();
        address.setPostalCode(req.getPostalCode());
        address.setStreetNumber(req.getStreetNumber());

        //Checking customer type and creating the correct customer object
        Customer customer = createCustomerByType(req, address);

        return customerService.createCustomerWithGeocoder(customer);
    }

    //GetCustomerByID API
    @Operation(summary = "Retrieve a customer by ID")
    @GetMapping("/{customerID}")
    public Customer getCustomerById(@PathVariable Integer customerID) {
        return customerService.getCustomerById(customerID);
    }

    //GetAllCustomers API
    @Operation(summary = "Retrieve all customers")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //Update Customer API
    @Operation(summary = "Update the existing customer")
    @PutMapping("/{customerID}")
    public Customer updateCustomer(@PathVariable Integer customerID,
                                   @Valid @RequestBody CustomerRequest req) {
        //Build address obj from request
        Address address = new Address(req.getStreetNumber(),
                req.getPostalCode(), req.getCity(), req.getProvince());

        //Checking customer type and creating the correct customer object
        Customer customer = createCustomerByType(req, address);

        return customerService.updateCustomer(customerID, customer);
    }

    //Patch Customer API
    //Partially updates only the fields provided in the request body
    @Operation(summary = "Partially update the existing customer")
    @PatchMapping("/{customerID}")
    public Customer patchCustomer(@PathVariable Integer customerID,
                                  @RequestBody Map<String, Object> updates) {
        return customerService.patchCustomer(customerID, updates);
    }

    //Delete Customer API
    @Operation(summary = "Delete the customer")
    @DeleteMapping("/{customerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer customerID) {
        customerService.deleteCustomer(customerID);
    }

    //GetCustomerByName
    @Operation(summary = "Retrieve customers by name")
    @GetMapping("/search")
    public List<Customer> getCustomerByName(@RequestParam String name){
        return customerService.getCustomerByName(name);
    }
}