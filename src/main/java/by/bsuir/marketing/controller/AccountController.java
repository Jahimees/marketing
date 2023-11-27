package by.bsuir.marketing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Value("${page.account}")
    private String ACCOUNT_PAGE;

    @GetMapping("/account")
    public String openAccountPage() {
        return ACCOUNT_PAGE;
    }
}
