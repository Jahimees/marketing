package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.service.BlankAnswerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blank-answers")
public class BlankAnswerRestController {

    private final BlankAnswerDataService blankAnswerDataService;

    @Autowired
    public BlankAnswerRestController(BlankAnswerDataService blankAnswerDataService) {
        this.blankAnswerDataService = blankAnswerDataService;
    }

    @GetMapping
    public List<BlankAnswer> getAllBlankAnswers() {
        return blankAnswerDataService.getAllBlankAnswers();
    }

    @GetMapping("/{id}")
    public BlankAnswer getBlankAnswerById(@PathVariable int id) {
        return blankAnswerDataService.getBlankAnswerById(id);
    }

    @PostMapping
    public BlankAnswer createBlankAnswer(@RequestBody BlankAnswer blankAnswer) {
        return blankAnswerDataService.createBlankAnswer(blankAnswer);
    }

    @PutMapping("/{id}")
    public BlankAnswer updateBlankAnswer(@PathVariable int id, @RequestBody BlankAnswer blankAnswer) {
        return blankAnswerDataService.updateBlankAnswer(id, blankAnswer);
    }

    @DeleteMapping("/{id}")
    public void deleteBlankAnswer(@PathVariable int id) {
        blankAnswerDataService.deleteBlankAnswer(id);
    }
}