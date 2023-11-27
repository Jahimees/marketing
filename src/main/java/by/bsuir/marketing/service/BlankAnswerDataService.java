package by.bsuir.marketing.service;

import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.repository.BlankAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlankAnswerDataService implements DataService<BlankAnswer> {

    private final BlankAnswerRepository blankAnswerRepository;

    public List<BlankAnswer> getAllBlankAnswers() {
        return blankAnswerRepository.findAll();
    }

    public Optional<BlankAnswer> getBlankAnswerById(int id) {
        return blankAnswerRepository.findById(id);
    }

    public BlankAnswer createBlankAnswer(BlankAnswer blankAnswer) {
        return blankAnswerRepository.save(blankAnswer);
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