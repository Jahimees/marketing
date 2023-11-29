package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Blank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlankRepository extends JpaRepository<Blank, Integer> {

    List<Blank> findAllByAccount(Account account);
}