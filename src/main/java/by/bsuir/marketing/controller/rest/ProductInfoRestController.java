package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.ProductInfo;
import by.bsuir.marketing.service.ProductInfoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-info")
public class ProductInfoRestController {

    private final ProductInfoDataService productInfoDataService;

    @Autowired
    public ProductInfoRestController(ProductInfoDataService productInfoDataService) {
        this.productInfoDataService = productInfoDataService;
    }

    @GetMapping("/{id}")
    public ProductInfo getProductInfoById(@PathVariable int id) {
        return productInfoDataService.getProductInfoById(id);
    }

    @GetMapping
    public List<ProductInfo> getAllProductInfo() {
        return productInfoDataService.getAllProductInfo();
    }

    @PostMapping
    public ProductInfo createProductInfo(@RequestBody ProductInfo productInfo) {
        return productInfoDataService.createProductInfo(productInfo);
    }

    @PutMapping("/{id}")
    public ProductInfo updateProductInfo(@PathVariable int id, @RequestBody ProductInfo productInfo) {
        return productInfoDataService.updateProductInfo(id, productInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteProductInfo(@PathVariable int id) {
        productInfoDataService.deleteProductInfo(id);
    }
}