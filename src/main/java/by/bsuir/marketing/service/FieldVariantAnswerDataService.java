package by.bsuir.marketing.service;

import by.bsuir.marketing.model.FieldVariantAnswer;
import by.bsuir.marketing.repository.FieldVariantAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldVariantAnswerDataService implements DataService<FieldVariantAnswer> {

    private final FieldVariantAnswerRepository fieldVariantAnswerRepository;

    public List<FieldVariantAnswer> createAll(List<FieldVariantAnswer> fieldVariantAnswers) {
        return fieldVariantAnswerRepository.saveAllAndFlush(fieldVariantAnswers);
    }
}
