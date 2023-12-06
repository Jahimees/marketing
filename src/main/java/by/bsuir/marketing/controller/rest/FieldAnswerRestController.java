package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.FieldAnswer;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.FieldAnswerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/field-answers")
@RequiredArgsConstructor
public class FieldAnswerRestController {

    private final FieldAnswerDataService fieldAnswerDataService;

    @GetMapping
    public ResponseEntity<List<FieldAnswer>> getAllFieldAnswers() {
        return ResponseEntity.ok(fieldAnswerDataService.getAllFieldAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getFieldAnswerById(@PathVariable int id) {
        Optional<FieldAnswer> fieldAnswerOptional = fieldAnswerDataService.getFieldAnswerById(id);

        if (fieldAnswerOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Ответ на поле не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(fieldAnswerOptional.get());
    }
}