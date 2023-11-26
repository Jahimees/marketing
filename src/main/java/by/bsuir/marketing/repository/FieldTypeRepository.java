package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTypeRepository extends JpaRepository<FieldType, Integer> {
}