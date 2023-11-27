package by.bsuir.marketing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Value("${page.login}")
    private String LOGIN_PAGE;

    @GetMapping("/authorize")
    public String openLoginPage() {
        return LOGIN_PAGE;
    }
}
