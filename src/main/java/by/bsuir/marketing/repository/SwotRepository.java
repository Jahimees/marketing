package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Swot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwotRepository extends JpaRepository<Swot, Integer> {

    List<Swot> findAllByAccount(Account account);
}
