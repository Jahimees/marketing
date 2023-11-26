package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Template;
import by.bsuir.marketing.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateDataService implements DataService<Template> {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateDataService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public Template getTemplateById(int id) {
        return templateRepository.findById(id).orElse(null);
    }

    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    public Template updateTemplate(int id, Template template) {
        Template existingTemplate = templateRepository.findById(id).orElse(null);
        if (existingTemplate != null) {
            existingTemplate.setName(template.getName());
            existingTemplate.setAccount(template.getAccount()); // Set the Account
            return templateRepository.save(existingTemplate);
        }
        return null;
    }

    public void deleteTemplate(int id) {
        templateRepository.deleteById(id);
    }
}