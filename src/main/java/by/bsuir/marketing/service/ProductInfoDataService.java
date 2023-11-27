package by.bsuir.marketing.service;

import by.bsuir.marketing.model.ProductInfo;
import by.bsuir.marketing.repository.ProductInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInfoDataService implements DataService<ProductInfo> {

    private final ProductInfoRepository productInfoRepository;

    public Optional<ProductInfo> getProductInfoById(int id) {
        return productInfoRepository.findById(id);
    }

    public List<ProductInfo> getAllProductInfo() {
        return productInfoRepository.findAll();
    }

    public ProductInfo createProductInfo(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    public ProductInfo updateProductInfo(int id, ProductInfo productInfo) {
        ProductInfo existingProductInfo = productInfoRepository.findById(id).orElse(null);
        if (existingProductInfo != null) {
            existingProductInfo.setProduct(productInfo.getProduct());
            existingProductInfo.setMonth(productInfo.getMonth());
            existingProductInfo.setSellCount(productInfo.getSellCount());
            existingProductInfo.setPrice(productInfo.getPrice());
            existingProductInfo.setProductionCount(productInfo.getProductionCount());
            existingProductInfo.setSurplus(productInfo.getSurplus());
            return productInfoRepository.save(existingProductInfo);
        }
        return null;
    }

    public void deleteProductInfo(int id) {
        productInfoRepository.deleteById(id);
    }
}