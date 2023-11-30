package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.model.FieldVariant;
import by.bsuir.marketing.repository.FieldVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FieldVariantDataService implements DataService<FieldVariant> {

    private final FieldVariantRepository fieldVariantRepository;

    public List<FieldVariant> createAll(List<FieldVariant> fieldVariantList) {
        return fieldVariantRepository.saveAllAndFlush(fieldVariantList);
    }

    public void deleteAllByFieldAndNotInIdSet(Field field, Set<Integer> keepFieldVariantSet) {
        fieldVariantRepository.deleteAllByFieldAndIdFieldVariantNotIn(field, keepFieldVariantSet);
    }
}
