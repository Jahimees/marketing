package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.AccountStatus;
import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.AccountStatusDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account-status")
@RequiredArgsConstructor
public class AccountStatusRestController {

    private final AccountStatusDataService accountStatusDataService;

    @GetMapping
    public ResponseEntity<List<AccountStatus>> getAllAccountStatuses() {
        List<AccountStatus> accountStatuses = accountStatusDataService.getAllAccountStatuses();
        return new ResponseEntity<>(accountStatuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getAccountStatusById(@PathVariable int id) {
        Optional<AccountStatus> accountStatus = accountStatusDataService.getAccountStatusById(id);

        if (accountStatus.isEmpty()) {
            return new ResponseEntity<>(
                    new MyResponseEntity("Статус аккаунта не найден"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(accountStatus.get());
        }
    }

    @PostMapping
    public ResponseEntity<AccountStatus> createAccountStatus(@RequestBody AccountStatus accountStatus) {
        AccountStatus createdAccountStatus = accountStatusDataService.createAccountStatus(accountStatus);

        return ResponseEntity.ok(createdAccountStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseEntity> updateAccountStatus(@PathVariable int id, @RequestBody AccountStatus accountStatus) {
        AccountStatus updatedAccountStatus = accountStatusDataService.updateAccountStatus(id, accountStatus);

        if (updatedAccountStatus != null) {
            return new ResponseEntity<>(updatedAccountStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MyResponseEntity("Обновляемый аккаунт статус не найден"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteAccountStatus(@PathVariable int id) {
        accountStatusDataService.deleteAccountStatus(id);
        return new ResponseEntity<>(new MyResponseEntity("Аккаунт статус удален"), HttpStatus.OK);
    }
}