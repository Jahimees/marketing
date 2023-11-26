package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.BlankStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlankStatusRepository extends JpaRepository<BlankStatus, Integer> {
}
