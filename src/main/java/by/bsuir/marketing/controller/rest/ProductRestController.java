package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductDataService productDataService;

    @Autowired
    public ProductRestController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDataService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDataService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDataService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productDataService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDataService.deleteProduct(id);
    }
}