package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlankTabController {

    @Value("${tab.blanks}")
    private String BLANKS_TAB;

    @GetMapping("/blanks")
    public String openBlankTab() {
        return BLANKS_TAB;
    }
}
