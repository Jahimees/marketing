package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.BlankAnswerDataService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blank-answers")
@RequiredArgsConstructor
public class BlankAnswerRestController {

    private final BlankAnswerDataService blankAnswerDataService;

    @GetMapping
    public ResponseEntity<List<BlankAnswer>> getAllBlankAnswers() {
        return new ResponseEntity<>(blankAnswerDataService.getAllBlankAnswers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getBlankAnswerById(@PathVariable int id) {
        Optional<BlankAnswer> blankAnswerOptional = blankAnswerDataService.getBlankAnswerById(id);

        if (blankAnswerOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Ответ на бланк не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(blankAnswerOptional.get());
    }

    @PostMapping
    public ResponseEntity<BlankAnswer> createBlankAnswer(@RequestBody BlankAnswer blankAnswer) {
        return ResponseEntity.ok(blankAnswerDataService.createBlankAnswer(blankAnswer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlankAnswer> updateBlankAnswer(@PathVariable int id, @RequestBody BlankAnswer blankAnswer) {
        return ResponseEntity.ok(blankAnswerDataService.updateBlankAnswer(id, blankAnswer));
    }

    @DeleteMapping("/{id}")
    public void deleteBlankAnswer(@PathVariable int id) {
        blankAnswerDataService.deleteBlankAnswer(id);
    }
}