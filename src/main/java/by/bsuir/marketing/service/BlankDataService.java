package by.bsuir.marketing.service;

import by.bsuir.marketing.model.*;
import by.bsuir.marketing.repository.BlankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BlankDataService implements DataService<Blank> {

    private final BlankRepository blankRepository;
    private final BlankStatusDataService blankStatusDataService;
    private final ProductDataService productDataService;
    private final FieldDataService fieldDataService;
    private final FieldVariantDataService fieldVariantDataService;
    private final AccountDataService accountDataService;

    public List<Blank> getAllBlanks(boolean isPublic) {

        if (isPublic) {
            BlankStatus blankStatus = blankStatusDataService.getBlankStatusById(2).get();

            return blankRepository.findAllByBlankStatus(blankStatus);
        }

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

        Optional<Blank> blankOptional = blankRepository.findById(blank.getIdBlank());
        if (blankOptional.isPresent()) {
            blank.setCreationDate(blankOptional.get().getCreationDate());
        }

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

        Set<Integer> keepFieldIds = new HashSet<>();
        blank.setFields(fieldDataService.createAll(blank.getFields()));
        blank.getFields().forEach(keepField -> {
            keepFieldIds.add(keepField.getIdField());
        });

        fieldDataService.deleteAllByBlankAndNotInIdSet(createdBlank, keepFieldIds);

        blank.getFields().forEach(field -> {
            if (field.getFieldVariants() == null) {
                throw new IllegalArgumentException("Field variants cannot be null");
            }

            field.getFieldVariants().forEach(variant -> {
                variant.setField(new Field(field.getIdField()));
            });

            List<FieldVariant> fieldVariants = fieldVariantDataService.createAll(field.getFieldVariants());
            field.setFieldVariants(fieldVariants);

            Set<Integer> keepFieldVariantIds = new HashSet<>();

            fieldVariants.forEach(fieldVariant -> {
                keepFieldVariantIds.add(fieldVariant.getIdFieldVariant());
            });

            fieldVariantDataService.deleteAllByFieldAndNotInIdSet(field, keepFieldVariantIds);
        });

        return blank;
    }

    public void deleteBlank(int id) {
        blankRepository.deleteById(id);
    }
}