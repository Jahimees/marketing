package by.bsuir.marketing.service;

import by.bsuir.marketing.model.FieldType;
import by.bsuir.marketing.repository.FieldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldTypeDataService implements DataService<FieldType> {

    private final FieldTypeRepository fieldTypeRepository;

    @Autowired
    public FieldTypeDataService(FieldTypeRepository fieldTypeRepository) {
        this.fieldTypeRepository = fieldTypeRepository;
    }

    public List<FieldType> getAllFieldTypes() {
        return fieldTypeRepository.findAll();
    }

    public FieldType getFieldTypeById(int id) {
        return fieldTypeRepository.findById(id).orElse(null);
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