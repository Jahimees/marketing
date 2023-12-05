package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.*;
import by.bsuir.marketing.service.SwotDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/swot")
@RequiredArgsConstructor
public class SwotRestController {

    private final SwotDataService swotDataService;

    @GetMapping
    public ResponseEntity<List<? extends BaseEntity>> getAllSwot(@RequestParam(required = false) Integer idAccount) {
        if (idAccount == null){
            return ResponseEntity.ok(swotDataService.getAllSwots());
        } else {
            return ResponseEntity.ok(swotDataService.getAllSwotsByIdAccount(idAccount));
        }
    }

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
        int idAccount = ((MyPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdAccount();
        swot.setAccount(new Account(idAccount));
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