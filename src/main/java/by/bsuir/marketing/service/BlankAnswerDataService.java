package by.bsuir.marketing.service;

import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.repository.BlankAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankAnswerDataService implements DataService<BlankAnswer> {

    private final BlankAnswerRepository blankAnswerRepository;

    @Autowired
    public BlankAnswerDataService(BlankAnswerRepository blankAnswerRepository) {
        this.blankAnswerRepository = blankAnswerRepository;
    }

    public List<BlankAnswer> getAllBlankAnswers() {
        return blankAnswerRepository.findAll();
    }

    public BlankAnswer getBlankAnswerById(int id) {
        return blankAnswerRepository.findById(id).orElse(null);
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