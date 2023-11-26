package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldDataService implements DataService<Field> {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldDataService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Field getFieldById(int id) {
        return fieldRepository.findById(id).orElse(null);
    }

    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    public Field updateField(int id, Field field) {
        Field existingField = fieldRepository.findById(id).orElse(null);
        if (existingField != null) {
            existingField.setText(field.getText());
            existingField.setFieldType(field.getFieldType());
            existingField.setBlank(field.getBlank());
            existingField.setTemplate(field.getTemplate());
            return fieldRepository.save(existingField);
        }
        return null;
    }

    public void deleteField(int id) {
        fieldRepository.deleteById(id);
    }
}