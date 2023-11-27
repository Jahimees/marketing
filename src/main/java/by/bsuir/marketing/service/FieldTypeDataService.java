package by.bsuir.marketing.service;

import by.bsuir.marketing.model.FieldType;
import by.bsuir.marketing.repository.FieldTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldTypeDataService implements DataService<FieldType> {

    private final FieldTypeRepository fieldTypeRepository;

    public List<FieldType> getAllFieldTypes() {
        return fieldTypeRepository.findAll();
    }

    public Optional<FieldType> getFieldTypeById(int id) {
        return fieldTypeRepository.findById(id);
    }

    public FieldType createFieldType(FieldType fieldType) {
        return fieldTypeRepository.save(fieldType);
    }

    public FieldType updateFieldType(int id, FieldType fieldType) {
        FieldType existingFieldType = fieldTypeRepository.findById(id).orElse(null);
        if (existingFieldType != null) {
            existingFieldType.setName(fieldType.getName());
            return fieldTypeRepository.save(existingFieldType);
        } else {
            return null;
        }
    }

    public void deleteFieldType(int id) {
        fieldTypeRepository.deleteById(id);
    }
}