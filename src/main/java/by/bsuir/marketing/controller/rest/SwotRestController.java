package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.Swot;
import by.bsuir.marketing.service.SwotDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/swot")
@RequiredArgsConstructor
public class SwotRestController {

    private final SwotDataService swotDataService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getSwotById(@PathVariable int id) {
        Optional<Swot> swotOptional = swotDataService.getSwotById(id);

        if (swotOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("SWOT не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(swotOptional.get());
    }

    @PostMapping
    public ResponseEntity<Swot> createSwot(@RequestBody Swot swot) {
        Swot createdSwot = swotDataService.createSwot(swot);
        return ResponseEntity.ok(createdSwot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Swot> updateSwot(@PathVariable int id, @RequestBody Swot swot) {
        Swot updatedSwot = swotDataService.updateSwot(id, swot);
        if (updatedSwot != null) {
            return ResponseEntity.ok(updatedSwot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteSwot(@PathVariable int id) {
        swotDataService.deleteSwot(id);
        return ResponseEntity.ok(new MyResponseEntity("SWOT удален"));
    }
}