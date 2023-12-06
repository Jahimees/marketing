package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.FieldType;
import by.bsuir.marketing.service.FieldTypeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/field-types")
@RequiredArgsConstructor
public class FieldTypeRestController {

    private final FieldTypeDataService fieldTypeDataService;

    @GetMapping
    public ResponseEntity<List<FieldType>> getAllFieldTypes() {
        return ResponseEntity.ok(fieldTypeDataService.getAllFieldTypes());
    }
}