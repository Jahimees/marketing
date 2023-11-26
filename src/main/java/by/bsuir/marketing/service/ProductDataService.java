package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDataService implements DataService<Product>{

    private final ProductRepository productRepository;

    @Autowired
    public ProductDataService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setCreationDate(product.getCreationDate());
            existingProduct.setAbout(product.getAbout());
            existingProduct.setProductType(product.getProductType());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}