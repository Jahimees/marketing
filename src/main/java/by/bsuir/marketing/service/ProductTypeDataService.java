package by.bsuir.marketing.service;

import by.bsuir.marketing.model.ProductType;
import by.bsuir.marketing.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeDataService implements DataService<ProductType> {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeDataService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public ProductType getProductTypeById(int id) {
        return productTypeRepository.findById(id).orElse(null);
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