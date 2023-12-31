package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.FieldVariantAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldVariantAnswerRepository extends JpaRepository<FieldVariantAnswer, Integer> {
}
