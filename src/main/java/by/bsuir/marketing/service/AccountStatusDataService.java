package by.bsuir.marketing.service;

import by.bsuir.marketing.model.AccountStatus;
import by.bsuir.marketing.repository.AccountStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountStatusDataService implements DataService<AccountStatus> {

    private final AccountStatusRepository accountStatusRepository;

    public List<AccountStatus> getAllAccountStatuses() {
        return accountStatusRepository.findAll();
    }

    public Optional<AccountStatus> getAccountStatusById(int id) {
        return accountStatusRepository.findById(id);
    }

    public AccountStatus createAccountStatus(AccountStatus accountStatus) {
        return accountStatusRepository.save(accountStatus);
    }

    public AccountStatus updateAccountStatus(int id, AccountStatus accountStatus) {
        AccountStatus existingAccountStatus = accountStatusRepository.findById(id).orElse(null);
        if (existingAccountStatus != null) {
            existingAccountStatus.setName(accountStatus.getName());
            return accountStatusRepository.save(existingAccountStatus);
        }
        return null;
    }

    public void deleteAccountStatus(int id) {
        accountStatusRepository.deleteById(id);
    }
}