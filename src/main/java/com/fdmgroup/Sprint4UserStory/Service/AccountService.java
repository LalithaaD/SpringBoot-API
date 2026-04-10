package com.fdmgroup.Sprint4UserStory.Service;

import com.fdmgroup.Sprint4UserStory.DTO.AccountRequest;
import com.fdmgroup.Sprint4UserStory.ExceptionHandling.CustomerNotFoundException;
import com.fdmgroup.Sprint4UserStory.Model.Account;
import com.fdmgroup.Sprint4UserStory.Model.CheckingAccount;
import com.fdmgroup.Sprint4UserStory.Model.Customer;
import com.fdmgroup.Sprint4UserStory.Model.SavingsAccount;
import com.fdmgroup.Sprint4UserStory.Repository.AccountRepository;
import com.fdmgroup.Sprint4UserStory.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//AccountService contains the business logic and interacts with the repository or the database
public class AccountService {

    //Creating repository object to access account database operations
    private final AccountRepository accountRepository;

    //Creating repository object to find the customer the account belongs to
    private final CustomerRepository customerRepository;

    //Constructor Injection
    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    //CreateAccount method creates a savings account or a checking account for a given customer
    @Transactional
    public Account createAccount(Integer customerID, AccountRequest req) {

        //Find the customer first, throws exception if not found
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with ID: " + customerID));

        //Check account type and create the correct account object
        Account account = null;

        if (req.getAccountType().equalsIgnoreCase("savings")) {
            account = new SavingsAccount(req.getBalance(), customer, req.getInterestRate());
        } else if (req.getAccountType().equalsIgnoreCase("checking")) {
            account = new CheckingAccount(req.getBalance(), customer, req.getNextCheckNumber());
        } else {
            throw new IllegalArgumentException("Account type must be savings or checking");
        }

        //save the account to the database
        return accountRepository.save(account);
    }

    //GetAllAccounts method retrieves all accounts from the database
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    //GetAllAccounts where customer city is Toronto
    public List<Account> getAccountsByCustomerCity(String city) {
        return accountRepository.findAccountsByCustomerCity(city);
    }

    //GetAccountById
    public Account getAccountById(Long accountId){
        return accountRepository.findById(accountId)
                .orElseThrow(()->
                            new RuntimeException("Account not found by ID: " + accountId));
    }

    //UpdateAccount
    @Transactional
    public Account updateAccount(Long accountId, AccountRequest req){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Account not found with ID: " + accountId));

        account.setBalance(req.getBalance());

        if(account instanceof SavingsAccount savings){
            savings.setInterestRate(req.getInterestRate());
        }

        if(account instanceof CheckingAccount checking){
            checking.setNextCheckNumber(req.getNextCheckNumber());
        }

        return accountRepository.save(account);
    }

    //DeleteAccount
    @Transactional
    public void deleteAccount(Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Account not found with ID: " + accountId));

        accountRepository.delete(account);
    }

}