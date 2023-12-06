package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.BlankAnswerDataService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<BlankAnswer>> getAllBlankAnswers(@RequestParam(required = false) Integer idBlank) {

        if (idBlank != null) {
            return ResponseEntity.ok(blankAnswerDataService.getAllBlankAnswersByIdBlank(idBlank));
        } else {
            return ResponseEntity.ok(blankAnswerDataService.getAllBlankAnswers());
        }
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
    public ResponseEntity<BlankAnswer> createBlankAnswer(@RequestBody BlankAnswer blankAnswer, HttpServletRequest request) {
        blankAnswer.setIpAddress(request.getRemoteAddr());
        return ResponseEntity.ok(blankAnswerDataService.createBlankAnswer(blankAnswer));
    }
}