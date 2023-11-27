package by.bsuir.marketing.service;

import by.bsuir.marketing.model.ProductType;
import by.bsuir.marketing.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeDataService implements DataService<ProductType> {

    private final ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> getProductTypeById(int id) {
        return productTypeRepository.findById(id);
    }

    public ProductType createProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public ProductType updateProductType(int id, ProductType productType) {
        ProductType existingProductType = productTypeRepository.findById(id).orElse(null);
        if (existingProductType != null) {
            existingProductType.setName(productType.getName());
            return productTypeRepository.save(existingProductType);
        }
        return null;
    }

    public void deleteProductType(int id) {
        productTypeRepository.deleteById(id);
    }
}