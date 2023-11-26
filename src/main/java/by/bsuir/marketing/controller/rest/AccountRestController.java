package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.service.AccountDataService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountDataService accountDataService;

    @PostMapping
    public ResponseEntity<? extends BaseEntity> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountDataService.save(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BaseEntity> getAccountById(@PathVariable int id) {
        return ResponseEntity.ok(accountDataService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        Account account = accountDataService.findById(id);
        accountDataService.delete(account);
    }
}