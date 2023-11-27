package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductTabController {

    @Value("${tab.products}")
    private String PRODUCT_TAB;

    @GetMapping("/products")
    public String openProductTab() {
        return PRODUCT_TAB;
    }
}
