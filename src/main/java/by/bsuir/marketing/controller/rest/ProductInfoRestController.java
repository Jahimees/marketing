package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.ProductInfo;
import by.bsuir.marketing.service.ProductInfoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-info")
@RequiredArgsConstructor
public class ProductInfoRestController {

    private final ProductInfoDataService productInfoDataService;

    @GetMapping
    public ResponseEntity<List<ProductInfo>> getAllProductInfo(@RequestParam(required = false) Integer idProduct) {

        if (idProduct == null) {
            return ResponseEntity.ok(productInfoDataService.getAllProductInfo());
        } else {
            return ResponseEntity.ok(productInfoDataService.getAllProductInfoByIdProduct(idProduct));
        }
    }

    @PostMapping
    public ResponseEntity<ProductInfo> createProductInfo(@RequestBody ProductInfo productInfo) {
        return ResponseEntity.ok(productInfoDataService.createProductInfo(productInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> deleteProductInfo(@PathVariable int id) {
        productInfoDataService.deleteProductInfo(id);

        return ResponseEntity.ok(new MyResponseEntity("Информация о продукте удалена"));
    }
}