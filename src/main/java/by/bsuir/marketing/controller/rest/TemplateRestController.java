package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.Template;
import by.bsuir.marketing.service.TemplateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateRestController {

    private final TemplateDataService templateDataService;

    @GetMapping
    public ResponseEntity<List<Template>> getAllTemplates() {
        return ResponseEntity.ok(templateDataService.getAllTemplates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getTemplateById(@PathVariable int id) {
        Optional<Template> templateOptional = templateDataService.getTemplateById(id);

        if (templateOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Шаблон не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(templateOptional.get());
    }

    @PostMapping
    public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
        return ResponseEntity.ok(templateDataService.createTemplate(template));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable int id, @RequestBody Template template) {
        return ResponseEntity.ok(templateDataService.updateTemplate(id, template));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteTemplate(@PathVariable int id) {
        templateDataService.deleteTemplate(id);

        return ResponseEntity.ok(new MyResponseEntity("Шаблон удален"));
    }
}