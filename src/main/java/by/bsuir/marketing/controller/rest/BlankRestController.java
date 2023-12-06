package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.service.BlankDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blanks")
@RequiredArgsConstructor
public class BlankRestController {

    private final BlankDataService blankDataService;

    @GetMapping
    public ResponseEntity<List<Blank>> getAllBlanks(@RequestParam(required = false) Integer idAccount,
                                                    @RequestParam(required = false) boolean isPublic) {
        if (idAccount == null) {

            List<Blank> blanks = blankDataService.getAllBlanks(isPublic);
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

    @PreAuthorize("hasRole('MARKETER')")
    @PostMapping
    public ResponseEntity<Blank> createBlank(@RequestBody Blank blank) {
        Blank createdBlank = blankDataService.createBlank(blank);
        return new ResponseEntity<>(createdBlank, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MARKETER')")
    @PutMapping("/{id}")
    public ResponseEntity<Blank> updateBlank(@PathVariable int id, @RequestBody Blank blank) {
        blank.setIdBlank(id);
        Blank updatedBlank = blankDataService.createBlank(blank);
        if (updatedBlank != null) {
            return new ResponseEntity<>(updatedBlank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('MARKETER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlank(@PathVariable int id) {
        blankDataService.deleteBlank(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}