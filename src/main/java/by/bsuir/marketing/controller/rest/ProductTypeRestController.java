package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.ProductType;
import by.bsuir.marketing.service.ProductTypeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@RequiredArgsConstructor
public class ProductTypeRestController {

    private final ProductTypeDataService productTypeDataService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeDataService.getAllProductTypes());
    }
}