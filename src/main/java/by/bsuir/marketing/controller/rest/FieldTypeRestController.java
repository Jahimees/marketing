package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.FieldType;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.service.FieldTypeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/field-types")
@RequiredArgsConstructor
public class FieldTypeRestController {

    private final FieldTypeDataService fieldTypeDataService;

    @GetMapping
    public ResponseEntity<List<FieldType>> getAllFieldTypes() {
        return ResponseEntity.ok(fieldTypeDataService.getAllFieldTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getFieldTypeById(@PathVariable int id) {
        Optional<FieldType> fieldTypeOptional = fieldTypeDataService.getFieldTypeById(id);

        if (fieldTypeOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Тип поля не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(fieldTypeOptional.get());
    }

    @PostMapping
    public ResponseEntity<FieldType> createFieldType(@RequestBody FieldType fieldType) {
        return ResponseEntity.ok(fieldTypeDataService.createFieldType(fieldType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldType> updateFieldType(@PathVariable int id, @RequestBody FieldType fieldType) {
        return ResponseEntity.ok(fieldTypeDataService.updateFieldType(id, fieldType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteFieldType(@PathVariable int id) {
        fieldTypeDataService.deleteFieldType(id);
        return new ResponseEntity<>(new MyResponseEntity("Тип поля удален"), HttpStatus.OK);
    }
}