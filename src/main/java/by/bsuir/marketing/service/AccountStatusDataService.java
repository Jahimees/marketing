package by.bsuir.marketing.service;

import by.bsuir.marketing.model.AccountStatus;
import by.bsuir.marketing.repository.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatusDataService implements DataService<AccountStatus> {

    private final AccountStatusRepository accountStatusRepository;

    @Autowired
    public AccountStatusDataService(AccountStatusRepository accountStatusRepository) {
        this.accountStatusRepository = accountStatusRepository;
    }

    public List<AccountStatus> getAllAccountStatuses() {
        return accountStatusRepository.findAll();
    }

    public AccountStatus getAccountStatusById(int id) {
        return accountStatusRepository.findById(id).orElse(null);
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