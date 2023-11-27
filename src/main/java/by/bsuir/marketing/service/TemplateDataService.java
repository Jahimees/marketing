package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Template;
import by.bsuir.marketing.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateDataService implements DataService<Template> {

    private final TemplateRepository templateRepository;

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public Optional<Template> getTemplateById(int id) {
        return templateRepository.findById(id);
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