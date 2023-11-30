package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.model.FieldVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FieldVariantRepository extends JpaRepository<FieldVariant, Integer> {

    void deleteAllByFieldAndIdFieldVariantNotIn(Field field, Set<Integer> keepFieldVariantsIdSet);
}
