package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

    void deleteAllByBlankAndIdFieldNotIn(Blank blank, Set<Integer> keepFieldIds);
}
