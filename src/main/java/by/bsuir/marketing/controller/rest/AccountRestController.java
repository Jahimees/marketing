package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.AccountDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountDataService accountDataService;

    @PostMapping
    public ResponseEntity<BaseEntity> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountDataService.createAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getAccountById(@PathVariable int id) {

        Optional<Account> accountOptional = accountDataService.getAccountById(id);

        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Аккаунт не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(accountOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteAccount(@PathVariable int id) {
        accountDataService.deleteAccount(id);

        return new ResponseEntity<>(new MyResponseEntity("Аккаунт удален"), HttpStatus.OK);
    }
}