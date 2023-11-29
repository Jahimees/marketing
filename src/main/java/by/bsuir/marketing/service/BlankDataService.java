package by.bsuir.marketing.service;

import by.bsuir.marketing.model.*;
import by.bsuir.marketing.repository.BlankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlankDataService implements DataService<Blank> {

    private final BlankRepository blankRepository;
    private final BlankStatusDataService blankStatusDataService;
    private final ProductDataService productDataService;
    private final FieldDataService fieldDataService;
    private final FieldVariantDataService fieldVariantDataService;
    private final AccountDataService accountDataService;

    public List<Blank> getAllBlanks() {
        return blankRepository.findAll();
    }

    public List<Blank> getBlanksByIdAccount(int idAccount) {
        Optional<Account> accountOptional = accountDataService.getAccountById(idAccount);

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        return blankRepository.findAllByAccount(accountOptional.get());
    }

    public Optional<Blank> getBlankById(int id) {
        return blankRepository.findById(id);
    }

    //batch query
    @Transactional
    public Blank createBlank(Blank blank) {
        if (blank.getAccount() == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        Optional<Account> accountOptional = accountDataService.getAccountById(blank.getAccount().getIdAccount());

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        blank.setAccount(accountOptional.get());

        if (blank.getBlankStatus() == null) {
            throw new IllegalArgumentException("Blank status cannot be null");
        }

        Optional<BlankStatus> blankStatusOptional = blankStatusDataService.getBlankStatusById(blank.getBlankStatus().getIdBlankStatus());

        if (blankStatusOptional.isEmpty()) {
            throw new NotFoundException("Blank status not found");
        }

        blank.setBlankStatus(blankStatusOptional.get());

        if (blank.getProduct() == null || blank.getProduct().getIdProduct() == 0) {
            blank.setProduct(null);
        } else {
            Optional<Product> productOptional = productDataService.getProductById(blank.getProduct().getIdProduct());

            if (productOptional.isEmpty()) {
                throw new NotFoundException("Product not found");
            }

            blank.setProduct(productOptional.get());
        }

        blank.setCreationDate(new Date());
        Blank createdBlank = blankRepository.saveAndFlush(blank);

        if (blank.getFields() == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }

        blank.getFields().forEach(field -> {
            if (field == null) {
                throw new IllegalArgumentException("Field cannot be null");
            }

            field.setBlank(new Blank(createdBlank.getIdBlank()));
        });

        List<Field> fieldList = fieldDataService.createAll(blank.getFields());
        blank.setFields(fieldList);

        fieldList.forEach(field -> {
            if (field.getFieldVariants() == null) {
                throw new IllegalArgumentException("Field variants cannot be null");
            }

            field.getFieldVariants().forEach(variant -> {
                variant.setField(new Field(field.getIdField()));
            });

            field.setFieldVariants(fieldVariantDataService.createAll(field.getFieldVariants()));
        });

        return blank;
//        return blankRepository.save(blank);
    }

    public Blank updateBlank(int id, Blank blank) {
        Blank existingBlank = blankRepository.findById(id).orElse(null);
        if (existingBlank != null) {
            existingBlank.setBlankStatus(blank.getBlankStatus());
            existingBlank.setName(blank.getName());
            existingBlank.setCreationDate(blank.getCreationDate());
            existingBlank.setProduct(blank.getProduct());
            existingBlank.setAccount(blank.getAccount());
            return blankRepository.save(existingBlank);
        }
        return null;
    }

    public void deleteBlank(int id) {
        blankRepository.deleteById(id);
    }
}