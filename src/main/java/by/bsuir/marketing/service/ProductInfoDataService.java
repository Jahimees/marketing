package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Product;
import by.bsuir.marketing.model.ProductInfo;
import by.bsuir.marketing.repository.ProductInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInfoDataService implements DataService<ProductInfo> {

    private final ProductInfoRepository productInfoRepository;
    private final ProductDataService productDataService;

    public Optional<ProductInfo> getProductInfoById(int id) {
        return productInfoRepository.findById(id);
    }

    public List<ProductInfo> getAllProductInfo() {
        return productInfoRepository.findAll();
    }

    public List<ProductInfo> getAllProductInfoByIdProduct(int idProduct) {
        Optional<Product> productOptional = productDataService.getProductById(idProduct);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return productInfoRepository.findAllByProduct(productOptional.get());
    }

    public ProductInfo createProductInfo(ProductInfo productInfo) {
        Optional<Product> productOptional = productDataService.getProductById(productInfo.getProduct().getIdProduct());

        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        if (productInfoRepository.existsByMonthAndProduct(productInfo.getMonth(), productOptional.get())) {
            throw new AlreadyExistsException("same info already exists");
        }

        productInfo.setFillingDate(new Date());

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