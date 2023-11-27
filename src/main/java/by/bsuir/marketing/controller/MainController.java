package by.bsuir.marketing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${page.main}")
    private String MAIN_PAGE;

    @GetMapping("/")
    public String openMainPage() {
        return MAIN_PAGE;
    }
}
