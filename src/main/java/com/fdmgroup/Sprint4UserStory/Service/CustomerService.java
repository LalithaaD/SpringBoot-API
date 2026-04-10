package com.fdmgroup.Sprint4UserStory.Service;

import com.fdmgroup.Sprint4UserStory.DTO.CustomerRequest;
import com.fdmgroup.Sprint4UserStory.ExceptionHandling.CustomerNotFoundException;
import com.fdmgroup.Sprint4UserStory.Model.Address;
import com.fdmgroup.Sprint4UserStory.Model.Company;
import com.fdmgroup.Sprint4UserStory.Model.Customer;
import com.fdmgroup.Sprint4UserStory.Model.Person;
import com.fdmgroup.Sprint4UserStory.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//CustomerService contains the business logic and interacts with the repository or the database
public class CustomerService {

    //Creating repository object to access database operations
    private final CustomerRepository customerRepository;

    //Creating GeoCoderService object to access Geocoder API logic
    private final GeoCoderService geoCoderService;

    //Constructor Injection
    public CustomerService(CustomerRepository customerRepository,
                           GeoCoderService geoCoderService) {
        this.customerRepository = customerRepository;
        this.geoCoderService    = geoCoderService;
    }

    //CreateCustomer method saves a new customer into the database
    @Transactional //each operation runs inside a transaction
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //CreateCustomer with Geocoder, automatically fills the city and province
    //user only needs to provide name, streetNumber and postalCode

    @Transactional
    public Customer createCustomerWithGeocoder(Customer customer) {

        //Calls GeoCoderService with the postal code from the address.
        String[] cityProvince = geoCoderService.getCityAndProvince(customer.getAddress().getPostalCode());

        //It then returns a String array with city at index 0 and province at index 1.
        customer.getAddress().setCity(cityProvince[0]);
        customer.getAddress().setProvince(cityProvince[1]);

        //sets them on address obj, and then saves the customer
        return customerRepository.save(customer);
    }

    //Get CustomerbyID to get a customer using the customer ID
    public Customer getCustomerById(Integer customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with ID: " + customerID));
    }

    //GetAllCustomers method to retrieve all customers from the database
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //UpdateCustomer method to update all the details, only if the customer exists
    @Transactional
    public Customer updateCustomer(Integer customerID, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(customerID);
        //Name is NOT updated, as it is just read only.
        existingCustomer.getAddress().setStreetNumber(updatedCustomer.getAddress().getStreetNumber());
        existingCustomer.getAddress().setPostalCode(updatedCustomer.getAddress().getPostalCode());
        existingCustomer.getAddress().setCity(updatedCustomer.getAddress().getCity());
        existingCustomer.getAddress().setProvince(updatedCustomer.getAddress().getProvince());

        return customerRepository.save(existingCustomer);
    }

    //PatchCustomer method updates only the provided fields
    @Transactional
    public Customer patchCustomer(Integer customerID, Map<String, Object> updates) {

        Customer existingCustomer = getCustomerById(customerID);

        if (updates.containsKey("streetNumber")) {
            existingCustomer.getAddress().setStreetNumber((String) updates.get("streetNumber"));
        }

        if (updates.containsKey("postalCode")) {
            existingCustomer.getAddress().setPostalCode((String) updates.get("postalCode"));
        }

        if (updates.containsKey("city")) {
            existingCustomer.getAddress().setCity((String) updates.get("city"));
        }

        if (updates.containsKey("province")) {
            existingCustomer.getAddress().setProvince((String) updates.get("province"));
        }

        return customerRepository.save(existingCustomer);
    }

    //DeleteCustomer using customer ID
    @Transactional
    public void deleteCustomer(Integer customerID) {
        Customer customer = getCustomerById(customerID);
        customerRepository.delete(customer);
    }

    //GetCustomerByName
    public List<Customer> getCustomerByName(String name){
        return customerRepository.findCustomerByName(name);
    }
}