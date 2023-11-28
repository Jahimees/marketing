package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDataService implements DataService<Product>{

    private final ProductRepository productRepository;
    private final AccountDataService accountDataService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByIdAccount(int idAccount) {
        Optional<Account> accountOptional = accountDataService.getAccountById(idAccount);

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        return productRepository.findProductsByAccount(accountOptional.get());
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (product.getAccount() == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        Optional<Account> accountOptional = accountDataService.getAccountById(product.getAccount().getIdAccount());

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        product.setAccount(accountOptional.get());
        product.setCreationDate(new Date());

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