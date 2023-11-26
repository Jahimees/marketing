package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Swot;
import by.bsuir.marketing.service.SwotDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swot")
public class SwotRestController {

    private final SwotDataService swotDataService;

    @Autowired
    public SwotRestController(SwotDataService swotDataService) {
        this.swotDataService = swotDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Swot> getSwotById(@PathVariable int id) {
        Swot swot = swotDataService.getSwotById(id);
        if (swot != null) {
            return ResponseEntity.ok(swot);
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public void deleteSwot(@PathVariable int id) {
        swotDataService.deleteSwot(id);
    }
}