package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.AccountDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<? extends BaseEntity>> getAllAccounts(@RequestParam(required = false) Integer idRole) {
        if (idRole == null) {
            return ResponseEntity.ok(accountDataService.getAllAccounts());
        } else {
            return ResponseEntity.ok(accountDataService.getAllAccountsByIdRole(idRole));
        }
    }

    @GetMapping("/checkby")
    public ResponseEntity<Boolean> getAccountByName(@RequestParam String username) {
        return ResponseEntity.ok(accountDataService.isAccountExistsByUsername(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getAccountById(@PathVariable int id) {

        Optional<Account> accountOptional = accountDataService.getAccountById(id);

        if (accountOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Аккаунт не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(accountOptional.get());
    }

    @PutMapping("/{idAccount}")
    public ResponseEntity<Account> updateAccountStatus(@PathVariable int idAccount) {
        return ResponseEntity.ok(accountDataService.changeStatus(idAccount));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteAccount(@PathVariable int id) {
        accountDataService.deleteAccount(id);

        return new ResponseEntity<>(new MyResponseEntity("Аккаунт удален"), HttpStatus.OK);
    }
}