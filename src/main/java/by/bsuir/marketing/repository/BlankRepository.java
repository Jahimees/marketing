package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Blank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlankRepository extends JpaRepository<Blank, Integer> {
}