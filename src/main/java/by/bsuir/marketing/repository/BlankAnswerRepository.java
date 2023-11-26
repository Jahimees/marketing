package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.BlankAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlankAnswerRepository extends JpaRepository<BlankAnswer, Integer> {
}
