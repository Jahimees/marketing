package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.BlankStatus;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.BlankStatusDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}