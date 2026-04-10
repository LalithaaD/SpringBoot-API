package com.fdmgroup.Sprint4UserStory.Controller;

import com.fdmgroup.Sprint4UserStory.DTO.AccountRequest;
import com.fdmgroup.Sprint4UserStory.Model.Account;
import com.fdmgroup.Sprint4UserStory.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//REST controller handles HTTP requests related to Account operations
@RestController
@RequestMapping("/accounts")
public class AccountController {

    //Creating Service object to access business logic
    private final AccountService accountService;

    //Constructor Injection
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //CreateAccount API
    //CustomerID is passed as a request parameter to link the account to a customer
    @Operation(summary = "Create a savings or checking account for a customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestParam Integer customerID, @Valid @RequestBody AccountRequest req){
        return accountService.createAccount(customerID, req);
    }

    //GetAllAccounts API
    @Operation(summary = "Retrieve all accounts")
    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    //GetAccountsById
    @Operation(summary = "Retrieve an account by ID")
    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId){
        return accountService.getAccountById(accountId);
    }

    //GetAllAccounts by customer city API
    @Operation(summary = "Fetch all accounts of customers whose city is Toronto")
    @GetMapping("/city")
    public List<Account> getAccountsByCustomerCity(@RequestParam String city) {
        return accountService.getAccountsByCustomerCity(city);
    }

    //UpdateAccount
    @Operation(summary = "Update an account")
    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable Long accountId, @Valid @RequestBody AccountRequest req){
        return accountService.updateAccount(accountId, req);
    }

    //DeleteAccount
    @Operation(summary = "Delete an account")
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
    }

}
