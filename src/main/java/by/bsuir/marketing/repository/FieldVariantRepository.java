package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.FieldVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldVariantRepository extends JpaRepository<FieldVariant, Integer> {
}
