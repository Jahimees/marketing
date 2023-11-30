package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.service.BlankDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blanks")
@RequiredArgsConstructor
public class BlankRestController {

    private final BlankDataService blankDataService;

    @GetMapping
    public ResponseEntity<List<Blank>> getAllBlanks(@RequestParam(required = false) Integer idAccount) {
        if (idAccount == null) {

            List<Blank> blanks = blankDataService.getAllBlanks();
            return new ResponseEntity<>(blanks, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(blankDataService.getBlanksByIdAccount(idAccount));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blank> getBlankById(@PathVariable int id) {
        Optional<Blank> blankOptional = blankDataService.getBlankById(id);

        if (blankOptional.isPresent()) {
            return new ResponseEntity<>(blankOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Blank> createBlank(@RequestBody Blank blank) {
        Blank createdBlank = blankDataService.createBlank(blank);
        return new ResponseEntity<>(createdBlank, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blank> updateBlank(@PathVariable int id, @RequestBody Blank blank) {
        Blank updatedBlank = blankDataService.createBlank(blank);
        if (updatedBlank != null) {
            return new ResponseEntity<>(updatedBlank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlank(@PathVariable int id) {
        blankDataService.deleteBlank(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}