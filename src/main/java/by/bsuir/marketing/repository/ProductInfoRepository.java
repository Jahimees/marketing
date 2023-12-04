package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    List<ProductInfo> findAllByProduct(Product product);

    boolean existsByMonthAndProduct(Date month, Product product);
}
