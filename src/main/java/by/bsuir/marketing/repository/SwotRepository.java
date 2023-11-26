package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Swot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwotRepository extends JpaRepository<Swot, Integer> {
}
