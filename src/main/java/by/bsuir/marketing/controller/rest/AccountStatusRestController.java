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
}