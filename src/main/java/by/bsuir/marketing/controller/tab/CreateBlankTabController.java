package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateBlankTabController {

    @Value("${tab.blanks.create}")
    private String CREATE_BLANK_TAB;

    @GetMapping("/blanks/create")
    public String openViewBlankTab() {
        return CREATE_BLANK_TAB;
    }
}
