package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

    List<Account> findAllByRole(Role role);
}
