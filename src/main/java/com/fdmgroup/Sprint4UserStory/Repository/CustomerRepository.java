package com.fdmgroup.Sprint4UserStory.Repository;

import com.fdmgroup.Sprint4UserStory.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Repository interface used to perform CRUD operations on Customer entity

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findCustomerByName(@Param("name") String name);

}