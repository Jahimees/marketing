package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.BlankAnswer;
import by.bsuir.marketing.model.BlankStatus;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.BlankStatusDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blank-status")
@RequiredArgsConstructor
public class BlankStatusRestController {

    private final BlankStatusDataService blankStatusDataService;

    @GetMapping
    public ResponseEntity<List<BlankStatus>> getAllBlankStatuses() {
        List<BlankStatus> blankStatuses = blankStatusDataService.getAllBlankStatuses();
        return new ResponseEntity<>(blankStatuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getBlankStatusById(@PathVariable int id) {
        Optional<BlankStatus> blankStatusOptional = blankStatusDataService.getBlankStatusById(id);

        if (blankStatusOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Бланк статус не найден"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(blankStatusOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlankStatus> createBlankStatus(@RequestBody BlankStatus blankStatus) {
        BlankStatus createdBlankStatus = blankStatusDataService.createBlankStatus(blankStatus);

        return ResponseEntity.ok(createdBlankStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseEntity> updateBlankStatus(@PathVariable int id, @RequestBody BlankStatus blankStatus) {
        BlankStatus updatedBlankStatus = blankStatusDataService.updateBlankStatus(id, blankStatus);

        if (updatedBlankStatus != null) {
            return ResponseEntity.ok(updatedBlankStatus);
        } else {
            return new ResponseEntity<>(new MyResponseEntity("Бланк статус не найден"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteBlankStatus(@PathVariable int id) {
        blankStatusDataService.deleteBlankStatus(id);

        return new ResponseEntity<>(new MyResponseEntity("Бланк статус удален"), HttpStatus.OK);
    }
}