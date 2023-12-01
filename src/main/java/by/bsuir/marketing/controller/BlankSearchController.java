package by.bsuir.marketing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlankSearchController {

    @Value("${page.blank_search}")
    private String BLANK_SEARCH_PAGE;

    @GetMapping("/blank_search")
    public String openBlankSearchPage() {
        return BLANK_SEARCH_PAGE;
    }
}
