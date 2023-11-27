package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductsByAccount(Account account);
}
