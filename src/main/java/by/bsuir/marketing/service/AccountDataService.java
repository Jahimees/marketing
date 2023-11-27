package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDataService implements DataService<Account> {

    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(int id, Account account) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            existingAccount.setUsername(account.getUsername());
            existingAccount.setPassword(account.getPassword());
            existingAccount.setRole(account.getRole());
            existingAccount.setIdAccountStatus(account.getIdAccountStatus());
            return accountRepository.save(existingAccount);
        }
        return null;
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}