package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.FieldAnswer;
import by.bsuir.marketing.service.FieldAnswerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field-answers")
public class FieldAnswerRestController {

    private final FieldAnswerDataService fieldAnswerDataService;

    @Autowired
    public FieldAnswerRestController(FieldAnswerDataService fieldAnswerDataService) {
        this.fieldAnswerDataService = fieldAnswerDataService;
    }

    @GetMapping
    public List<FieldAnswer> getAllFieldAnswers() {
        return fieldAnswerDataService.getAllFieldAnswers();
    }

    @GetMapping("/{id}")
    public FieldAnswer getFieldAnswerById(@PathVariable int id) {
        return fieldAnswerDataService.getFieldAnswerById(id);
    }

    @PostMapping
    public FieldAnswer createFieldAnswer(@RequestBody FieldAnswer fieldAnswer) {
        return fieldAnswerDataService.createFieldAnswer(fieldAnswer);
    }

    @PutMapping("/{id}")
    public FieldAnswer updateFieldAnswer(@PathVariable int id, @RequestBody FieldAnswer fieldAnswer) {
        return fieldAnswerDataService.updateFieldAnswer(id, fieldAnswer);
    }

    @DeleteMapping("/{id}")
    public void deleteFieldAnswer(@PathVariable int id) {
        fieldAnswerDataService.deleteFieldAnswer(id);
    }
}