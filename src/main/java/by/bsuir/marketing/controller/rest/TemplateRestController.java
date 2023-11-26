package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.Template;
import by.bsuir.marketing.service.TemplateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateRestController {

    private final TemplateDataService templateDataService;

    @Autowired
    public TemplateRestController(TemplateDataService templateDataService) {
        this.templateDataService = templateDataService;
    }

    @GetMapping
    public List<Template> getAllTemplates() {
        return templateDataService.getAllTemplates();
    }

    @GetMapping("/{id}")
    public Template getTemplateById(@PathVariable int id) {
        return templateDataService.getTemplateById(id);
    }

    @PostMapping
    public Template createTemplate(@RequestBody Template template) {
        return templateDataService.createTemplate(template);
    }

    @PutMapping("/{id}")
    public Template updateTemplate(@PathVariable int id, @RequestBody Template template) {
        return templateDataService.updateTemplate(id, template);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable int id) {
        templateDataService.deleteTemplate(id);
    }
}