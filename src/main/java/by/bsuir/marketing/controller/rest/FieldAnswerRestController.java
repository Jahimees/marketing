package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.FieldAnswer;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.FieldAnswerDataService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<FieldAnswer> createFieldAnswer(@RequestBody FieldAnswer fieldAnswer) {
        return ResponseEntity.ok(fieldAnswerDataService.createFieldAnswer(fieldAnswer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldAnswer> updateFieldAnswer(@PathVariable int id, @RequestBody FieldAnswer fieldAnswer) {
        return ResponseEntity.ok(fieldAnswerDataService.updateFieldAnswer(id, fieldAnswer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteFieldAnswer(@PathVariable int id) {
        fieldAnswerDataService.deleteFieldAnswer(id);

        return new ResponseEntity<>(new MyResponseEntity("Ответ на поле удален"), HttpStatus.OK);
    }
}