package by.bsuir.marketing.service;

import by.bsuir.marketing.model.FieldVariant;
import by.bsuir.marketing.repository.FieldVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldVariantDataService implements DataService<FieldVariant> {

    private final FieldVariantRepository fieldVariantRepository;

    public List<FieldVariant> createAll(List<FieldVariant> fieldVariantList) {
        return fieldVariantRepository.saveAllAndFlush(fieldVariantList);
    }
}
