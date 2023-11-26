package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.AccountStatus;
import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.service.AccountStatusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-status")
public class AccountStatusRestController {

    private final AccountStatusDataService accountStatusDataService;

    @Autowired
    public AccountStatusRestController(AccountStatusDataService accountStatusDataService) {
        this.accountStatusDataService = accountStatusDataService;
    }

    @GetMapping
    public ResponseEntity<List<AccountStatus>> getAllAccountStatuses() {
        List<AccountStatus> accountStatuses = accountStatusDataService.getAllAccountStatuses();
        return new ResponseEntity<>(accountStatuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatus> getAccountStatusById(@PathVariable int id) {
        AccountStatus accountStatus = accountStatusDataService.getAccountStatusById(id);
        if (accountStatus != null) {
            return new ResponseEntity<>(accountStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AccountStatus> createAccountStatus(@RequestBody AccountStatus accountStatus) {
        AccountStatus createdAccountStatus = accountStatusDataService.createAccountStatus(accountStatus);
        return new ResponseEntity<>(createdAccountStatus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountStatus> updateAccountStatus(@PathVariable int id, @RequestBody AccountStatus accountStatus) {
        AccountStatus updatedAccountStatus = accountStatusDataService.updateAccountStatus(id, accountStatus);
        if (updatedAccountStatus != null) {
            return new ResponseEntity<>(updatedAccountStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountStatus(@PathVariable int id) {
        accountStatusDataService.deleteAccountStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}