package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.FieldDataService;
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
@RequestMapping("/api/fields")
@RequiredArgsConstructor
public class FieldRestController {

    private final FieldDataService fieldDataService;

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        return ResponseEntity.ok(fieldDataService.getAllFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getFieldById(@PathVariable int id) {
        Optional<Field> fieldOptional = fieldDataService.getFieldById(id);

        if (fieldOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Поле не найдено"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(fieldOptional.get());
    }
}