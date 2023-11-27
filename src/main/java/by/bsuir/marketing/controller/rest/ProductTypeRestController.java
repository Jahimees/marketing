package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.model.ProductType;
import by.bsuir.marketing.service.ProductTypeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-types")
@RequiredArgsConstructor
public class ProductTypeRestController {

    private final ProductTypeDataService productTypeDataService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeDataService.getAllProductTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getProductTypeById(@PathVariable int id) {
        Optional<ProductType> productTypeOptional = productTypeDataService.getProductTypeById(id);

        if (productTypeOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Тип продукта не найден"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(productTypeOptional.get());
    }

    @PostMapping
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        return ResponseEntity.ok(productTypeDataService.createProductType(productType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable int id, @RequestBody ProductType productType) {
        return ResponseEntity.ok(productTypeDataService.updateProductType(id, productType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteProductType(@PathVariable int id) {
        productTypeDataService.deleteProductType(id);

        return ResponseEntity.ok(new MyResponseEntity("Тип продукта удален"));
    }
}