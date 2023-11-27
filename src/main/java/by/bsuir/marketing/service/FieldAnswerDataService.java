package by.bsuir.marketing.service;

import by.bsuir.marketing.model.FieldAnswer;
import by.bsuir.marketing.repository.FieldAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldAnswerDataService {

    private final FieldAnswerRepository fieldAnswerRepository;

    public List<FieldAnswer> getAllFieldAnswers() {
        return fieldAnswerRepository.findAll();
    }

    public Optional<FieldAnswer> getFieldAnswerById(int id) {
        return fieldAnswerRepository.findById(id);
    }

    public FieldAnswer createFieldAnswer(FieldAnswer fieldAnswer) {
        return fieldAnswerRepository.save(fieldAnswer);
    }

    public FieldAnswer updateFieldAnswer(int id, FieldAnswer fieldAnswer) {
        FieldAnswer existingFieldAnswer = fieldAnswerRepository.findById(id).orElse(null);
        if (existingFieldAnswer != null) {
            existingFieldAnswer.setBlankAnswer(fieldAnswer.getBlankAnswer());
            existingFieldAnswer.setField(fieldAnswer.getField());
            existingFieldAnswer.setAnswer(fieldAnswer.getAnswer());
            return fieldAnswerRepository.save(existingFieldAnswer);
        }
        return null;
    }

    public void deleteFieldAnswer(int id) {
        fieldAnswerRepository.deleteById(id);
    }
}