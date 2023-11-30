package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.model.Field;
import by.bsuir.marketing.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FieldDataService implements DataService<Field> {

    private final FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(int id) {
        return fieldRepository.findById(id);
    }

    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    public List<Field> createAll(List<Field> fieldList) {
        return fieldRepository.saveAllAndFlush(fieldList);
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

    public void deleteAllByBlankAndNotInIdSet(Blank blank, Set<Integer> keepFieldSet) {
        fieldRepository.deleteAllByBlankAndIdFieldNotIn(blank, keepFieldSet);
    }

    public void deleteField(int id) {
        fieldRepository.deleteById(id);
    }
}