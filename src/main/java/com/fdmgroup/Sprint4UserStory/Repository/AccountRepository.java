package com.fdmgroup.Sprint4UserStory.Repository;

import com.fdmgroup.Sprint4UserStory.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //Custom query to fetch all accounts by customer city
    @Query("SELECT a FROM Account a WHERE a.customer.address.city = :city")
    List<Account> findAccountsByCustomerCity(@Param("city") String city);
}
