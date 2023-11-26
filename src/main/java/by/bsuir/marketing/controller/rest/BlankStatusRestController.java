package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BlankStatus;
import by.bsuir.marketing.service.BlankStatusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blank-status")
public class BlankStatusRestController {

    private final BlankStatusDataService blankStatusDataService;

    @Autowired
    public BlankStatusRestController(BlankStatusDataService blankStatusDataService) {
        this.blankStatusDataService = blankStatusDataService;
    }

    @GetMapping
    public ResponseEntity<List<BlankStatus>> getAllBlankStatuses() {
        List<BlankStatus> blankStatuses = blankStatusDataService.getAllBlankStatuses();
        return new ResponseEntity<>(blankStatuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlankStatus> getBlankStatusById(@PathVariable int id) {
        BlankStatus blankStatus = blankStatusDataService.getBlankStatusById(id);
        if (blankStatus != null) {
            return new ResponseEntity<>(blankStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BlankStatus> createBlankStatus(@RequestBody BlankStatus blankStatus) {
        BlankStatus createdBlankStatus = blankStatusDataService.createBlankStatus(blankStatus);
        return new ResponseEntity<>(createdBlankStatus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlankStatus> updateBlankStatus(@PathVariable int id, @RequestBody BlankStatus blankStatus) {
        BlankStatus updatedBlankStatus = blankStatusDataService.updateBlankStatus(id, blankStatus);
        if (updatedBlankStatus != null) {
            return new ResponseEntity<>(updatedBlankStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlankStatus(@PathVariable int id) {
        blankStatusDataService.deleteBlankStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}