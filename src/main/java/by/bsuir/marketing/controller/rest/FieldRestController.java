package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.service.FieldDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldRestController {

    private final FieldDataService fieldDataService;

    @Autowired
    public FieldRestController(FieldDataService fieldDataService) {
        this.fieldDataService = fieldDataService;
    }

    @GetMapping
    public List<Field> getAllFields() {
        return fieldDataService.getAllFields();
    }

    @GetMapping("/{id}")
    public Field getFieldById(@PathVariable int id) {
        return fieldDataService.getFieldById(id);
    }

    @PostMapping
    public Field createField(@RequestBody Field field) {
        return fieldDataService.createField(field);
    }

    @PutMapping("/{id}")
    public Field updateField(@PathVariable int id, @RequestBody Field field) {
        return fieldDataService.updateField(id, field);
    }

    @DeleteMapping("/{id}")
    public void deleteField(@PathVariable int id) {
        fieldDataService.deleteField(id);
    }
}