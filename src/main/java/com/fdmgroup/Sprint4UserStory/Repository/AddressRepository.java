package com.fdmgroup.Sprint4UserStory.Repository;

import com.fdmgroup.Sprint4UserStory.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for performing database operations on Address entity
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
