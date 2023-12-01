package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.model.FieldAnswer;
import by.bsuir.marketing.model.FieldVariantAnswer;
import by.bsuir.marketing.repository.BlankAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlankAnswerDataService implements DataService<BlankAnswer> {

    private final BlankAnswerRepository blankAnswerRepository;
    private final BlankDataService blankDataService;
    private final FieldAnswerDataService fieldAnswerDataService;
    private final FieldVariantAnswerDataService fieldVariantAnswerDataService;

    public List<BlankAnswer> getAllBlankAnswers() {
        return blankAnswerRepository.findAll();
    }

    public Optional<BlankAnswer> getBlankAnswerById(int id) {
        return blankAnswerRepository.findById(id);
    }

    public List<BlankAnswer> getAllBlankAnswersByIdBlank(int idBlank) {
        Optional<Blank> blankOptional = blankDataService.getBlankById(idBlank);

        if (blankOptional.isEmpty()) {
            throw new NotFoundException("blank not found");
        }

        return blankAnswerRepository.findAllByBlank(blankOptional.get());
    }

    @Transactional
    public BlankAnswer createBlankAnswer(BlankAnswer blankAnswer) {
        Optional<Blank> blankOptional = blankDataService.getBlankById(blankAnswer.getBlank().getIdBlank());

        if (blankOptional.isEmpty()) {
            throw new NotFoundException("Blank not found");
        }

        if (blankAnswerRepository.existsByIpAddressAndBlank(blankAnswer.getIpAddress(), blankOptional.get())) {
            throw new IllegalArgumentException("User already pass the survey");
        }

        blankAnswer.setAnswerDate(new Date());
        BlankAnswer createdBlankAnswer = blankAnswerRepository.saveAndFlush(blankAnswer);

        if (blankAnswer.getFieldAnswers() == null) {
            throw new IllegalArgumentException("Field answers cannot be null");
        }

        blankAnswer.getFieldAnswers().forEach(fieldAnswer -> {
            if (fieldAnswer == null) {
                throw new IllegalArgumentException("Field cannot be null");
            }

            fieldAnswer.setBlankAnswer(new BlankAnswer(createdBlankAnswer.getIdBlankAnswer()));
        });

        blankAnswer.setFieldAnswers(fieldAnswerDataService.createAll(blankAnswer.getFieldAnswers()));

        blankAnswer.getFieldAnswers().forEach(fieldAnswer -> {
            if (fieldAnswer.getFieldVariantAnswers() != null) {

                fieldAnswer.getFieldVariantAnswers().forEach(variantAnswer -> {
                    variantAnswer.setFieldAnswer(new FieldAnswer(fieldAnswer.getIdFieldAnswer()));
                });

                List<FieldVariantAnswer> fieldVariantAnswers = fieldVariantAnswerDataService.createAll(
                        fieldAnswer.getFieldVariantAnswers());

                fieldAnswer.setFieldVariantAnswers(fieldVariantAnswers);
            }
        });

        return blankAnswer;
    }

    public BlankAnswer updateBlankAnswer(int id, BlankAnswer blankAnswer) {
        BlankAnswer existingBlankAnswer = blankAnswerRepository.findById(id).orElse(null);
        if (existingBlankAnswer != null) {
            existingBlankAnswer.setBlank(blankAnswer.getBlank());
            existingBlankAnswer.setUsername(blankAnswer.getUsername());
            existingBlankAnswer.setIpAddress(blankAnswer.getIpAddress());
            return blankAnswerRepository.save(existingBlankAnswer);
        } else {
            return null;
        }
    }

    public void deleteBlankAnswer(int id) {
        blankAnswerRepository.deleteById(id);
    }
}