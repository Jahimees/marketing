package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.service.ProductDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductDataService productDataService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer idAccount) {
        if (idAccount == null) {
            return ResponseEntity.ok(productDataService.getAllProducts());
        } else {
            return ResponseEntity.ok(productDataService.getAllProductsByIdAccount(idAccount));
        }

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productDataService.createProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteProduct(@PathVariable int id) {
        productDataService.deleteProduct(id);

        return ResponseEntity.ok(new MyResponseEntity("Продукт удален"));
    }
}