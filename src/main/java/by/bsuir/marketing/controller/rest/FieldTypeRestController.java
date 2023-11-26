package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.FieldType;
import by.bsuir.marketing.service.FieldTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/field-types")
public class FieldTypeRestController {

    private final FieldTypeDataService fieldTypeDataService;

    @Autowired
    public FieldTypeRestController(FieldTypeDataService fieldTypeDataService) {
        this.fieldTypeDataService = fieldTypeDataService;
    }

    @GetMapping
    public List<FieldType> getAllFieldTypes() {
        return fieldTypeDataService.getAllFieldTypes();
    }

    @GetMapping("/{id}")
    public FieldType getFieldTypeById(@PathVariable int id) {
        return fieldTypeDataService.getFieldTypeById(id);
    }

    @PostMapping
    public FieldType createFieldType(@RequestBody FieldType fieldType) {
        return fieldTypeDataService.createFieldType(fieldType);
    }

    @PutMapping("/{id}")
    public FieldType updateFieldType(@PathVariable int id, @RequestBody FieldType fieldType) {
        return fieldTypeDataService.updateFieldType(id, fieldType);
    }

    @DeleteMapping("/{id}")
    public void deleteFieldType(@PathVariable int id) {
        fieldTypeDataService.deleteFieldType(id);
    }
}