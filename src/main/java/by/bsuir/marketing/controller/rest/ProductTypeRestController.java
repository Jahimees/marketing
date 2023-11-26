package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.ProductType;
import by.bsuir.marketing.service.ProductTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-types")
public class ProductTypeRestController {

    private final ProductTypeDataService productTypeDataService;

    @Autowired
    public ProductTypeRestController(ProductTypeDataService productTypeDataService) {
        this.productTypeDataService = productTypeDataService;
    }

    @GetMapping
    public List<ProductType> getAllProductTypes() {
        return productTypeDataService.getAllProductTypes();
    }

    @GetMapping("/{id}")
    public ProductType getProductTypeById(@PathVariable int id) {
        return productTypeDataService.getProductTypeById(id);
    }

    @PostMapping
    public ProductType createProductType(@RequestBody ProductType productType) {
        return productTypeDataService.createProductType(productType);
    }

    @PutMapping("/{id}")
    public ProductType updateProductType(@PathVariable int id, @RequestBody ProductType productType) {
        return productTypeDataService.updateProductType(id, productType);
    }

    @DeleteMapping("/{id}")
    public void deleteProductType(@PathVariable int id) {
        productTypeDataService.deleteProductType(id);
    }
}