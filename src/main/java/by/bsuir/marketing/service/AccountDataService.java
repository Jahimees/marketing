package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.AccountStatus;
import by.bsuir.marketing.model.Role;
import by.bsuir.marketing.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDataService implements DataService<Account> {

    private final AccountRepository accountRepository;
    private final RoleDataService roleDataService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAllAccountsByIdRole(int idRole) {
        Optional<Role> optionalRole = roleDataService.getRoleById(idRole);

        if (optionalRole.isEmpty()) {
            throw new NotFoundException("Role not found");
        }

        return accountRepository.findAllByRole(optionalRole.get());
    }

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public Account changeStatus(int idAccount) {
        Optional<Account> accountOptional = getAccountById(idAccount);

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        Account account = accountOptional.get();

        if (account.getAccountStatus() == null) {
            throw new IllegalArgumentException("Account status is null");
        }

        if (account.getAccountStatus().getIdAccountStatus() == 1) {
            account.setAccountStatus(new AccountStatus(2));
        } else {
            account.setAccountStatus(new AccountStatus(1));
        }

        return accountRepository.saveAndFlush(account);
    }

    public Account createAccount(Account account) {
        if (isAccountExistsByUsername(account.getUsername())) {
            throw new IllegalArgumentException("Same user already exists");
        }

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setAccountStatus(new AccountStatus(1));
        account.setRole(new Role(2));

        return accountRepository.save(account);
    }

    public Account updateAccount(int id, Account account) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            existingAccount.setUsername(account.getUsername());
            existingAccount.setPassword(account.getPassword());
            existingAccount.setRole(account.getRole());
            existingAccount.setAccountStatus(account.getAccountStatus());
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

    public boolean isAccountExistsByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        return accountOptional.isPresent();
    }
}