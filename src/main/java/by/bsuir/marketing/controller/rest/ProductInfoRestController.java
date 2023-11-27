package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.ProductInfo;
import by.bsuir.marketing.service.ProductInfoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-info")
@RequiredArgsConstructor
public class ProductInfoRestController {

    private final ProductInfoDataService productInfoDataService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getProductInfoById(@PathVariable int id) {
        Optional<ProductInfo> productInfoOptional = productInfoDataService.getProductInfoById(id);

        if (productInfoOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Информация о продукте не найдена"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productInfoOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<ProductInfo>> getAllProductInfo() {
        return ResponseEntity.ok(productInfoDataService.getAllProductInfo());
    }

    @PostMapping
    public ResponseEntity<ProductInfo> createProductInfo(@RequestBody ProductInfo productInfo) {
        return ResponseEntity.ok(productInfoDataService.createProductInfo(productInfo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductInfo> updateProductInfo(@PathVariable int id, @RequestBody ProductInfo productInfo) {
        return ResponseEntity.ok(productInfoDataService.updateProductInfo(id, productInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteProductInfo(@PathVariable int id) {
        productInfoDataService.deleteProductInfo(id);

        return ResponseEntity.ok(new MyResponseEntity("Информация о продукте удалена"));
    }
}