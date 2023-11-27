package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateBlankTabController {

    @Value("${tab.blanks.templates}")
    private String TEMPLATES_BLANK_TAB;

    @GetMapping("/blanks/templates")
    public String openTemplatesBlankTab() {
        return TEMPLATES_BLANK_TAB;
    }
}
